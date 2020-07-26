import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class NewsService {
  public API = '//localhost:8081';
  public NEW_API = this.API + '/cars';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get('//localhost:8081/news-feed');
  }
  get(id: string) {
    return this.http.get(this.NEW_API + '/' + id);
  }

  save(newss: any): Observable<any> {
    let result: Observable<any>;
    if (newss.href) {
      result = this.http.put(newss.href, newss);
    } else {
      result = this.http.post(this.NEW_API, newss);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
