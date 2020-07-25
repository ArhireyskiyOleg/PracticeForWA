import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import {  AuthenticationService,AlertService } from '@app/services/';

@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  login: FormGroup;
  subm = false;
  retUrl: string;
  load = false;

  constructor(
    private router: Router,
    private autService: AuthenticationService,
    private activRoute: ActivatedRoute,
    private buildForm: FormBuilder,
    private alertService: AlertService
  ) {
    if (this.autService.curUserVal) {
      this.router.navigate(['/home']);
    }
  }

  get loginControls() {
    return this.login.controls;
  }

  ngOnInit() {
    this.login = this.buildForm.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
    this.retUrl = this.activRoute.snapshot.queryParams['retUrl'] || '/';
  }

  onSubmit() {
    this.subm = true;

    this.alertService.clear();

    if (this.login.invalid) {
      return;
    }

    this.load = true;
    this.autService
      .login(this.loginControls.username.value, this.loginControls.password.value)
      .pipe(first())
      .subscribe(
        (data) => {
          this.router.navigate([this.retUrl]);
        },
        (error) => {
          this.alertService.error(error);
          this.load = false;
        }
      );
  }
}
