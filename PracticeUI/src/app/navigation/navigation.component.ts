import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { User } from '../models/user';
@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  currentUser: User;
  constructor(private authenticationService: AuthenticationService) {
    this.currentUser = this.authenticationService.curUserVal;
   }
onlogoutcl(){
  this.authenticationService.logout();
}
  ngOnInit(): void {
  }

}
