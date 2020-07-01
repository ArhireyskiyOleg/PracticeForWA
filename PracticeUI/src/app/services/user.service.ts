import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';

@Injectable({ providedIn: 'root' })
export class UserService {
  baseUrl = 'localhost:8080';
  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<User[]>(`${this.baseUrl}/users`);
  }

  register(user: User) {
    return this.http.get(`${this.baseUrl}/add/${user.username}/${user.password}/${user.lastName}/${user.firstName}`);
  }

  delete(username: string) {
    return this.http.delete(`${this.baseUrl}/delete/${username}`);
  }
}
