import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { NewsService } from '../news/news.service';
import { GiphyService } from '../giphy/giphy.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-new-edit',
  templateUrl: './new-edit.component.html',
  styleUrls: ['./new-edit.component.css']
})
export class NewEditComponent implements OnInit, OnDestroy {
  news: any = {};

  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private newsService: NewsService,
              private giphyService: GiphyService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params.id;
      if (id) {
        this.newsService.get(id).subscribe((news: any) => {
          if (news) {
            this.news = news;
            this.news.href = news._links.self.href;
            this.giphyService.get(news.name).subscribe(url => news.giphyUrl = url);
          } else {
            console.log(`news with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/news-list']);
  }

  save(form: NgForm) {
    this.newsService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  remove(href) {
    this.newsService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }
}
