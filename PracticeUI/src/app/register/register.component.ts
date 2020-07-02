import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AlertService, UserService, AuthenticationService } from '../services';

@Component({
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  load = false;
  subm = false;
  registeration: FormGroup;

  constructor(
    private autService: AuthenticationService,
    private user: UserService,
    private alertService: AlertService,
    private buildForm: FormBuilder,
    private router: Router
  ) {
    if (this.autService.curUserVal) {
      this.router.navigate(['/home']);
    }
  }

  ngOnInit() {
    this.registeration = this.buildForm.group({
      email: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(10)]],
    });
  }

  get registrRegistration() {
    return this.registeration.controls;
  }

  onSubmit() {
    this.subm = true;

    if (this.registeration.invalid) {
      return;
    }

    this.load = true;
    this.user
      .register(this.registeration.value)
      .pipe(first())
      .subscribe(
        (data) => {
          this.alertService.success('Registration successful', true);
          this.router.navigate(['/login']);
        },
        (error) => {
          this.alertService.error(error);
          this.load = false;
        }
      );
  }
}
