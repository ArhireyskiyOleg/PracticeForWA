import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { User } from '../models/user';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  baseUrl = 'localhost:4200';
  private curUserSub: BehaviorSubject<User>;
  public curUser: Observable<User>;
  constructor(private http: HttpClient) {
    this.curUserSub = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currUser')));
    this.curUser = this.curUserSub.asObservable();
  }
  public get curUserVal(): User {
    return this.curUserSub.value;
  }

  login(username: string, password: string) {
    return this.http.get<any>(`${this.baseUrl}/find/${username}/${password}`).pipe(
      map((user) => {
        if (user && user.token) {
          localStorage.setItem('currUser',JSON.stringify(user));
          this.curUserSub.next(user);
        }
        return user;
      })
    );
  }

  logout() {
    localStorage.removeItem('currUser');
    this.curUserSub.next(null);
  }
}
