import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { User } from '../models/user';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  currentUser: User;
  constructor(private authenticationService: AuthenticationService) {
    this.currentUser = this.authenticationService.curUserVal;
   }

  ngOnInit(): void {
  }

}
