import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class UserService {
  baseUrl = 'localhost:4200';
  constructor(private http: HttpClient) {}

  register(user: User) {
    return this.http.get(`${this.baseUrl}/add/${user.email}/${user.username}/${user.password}`);
  }

  getAll() {
    return this.http.get<User[]>(`${this.baseUrl}/users`);
  }

}
