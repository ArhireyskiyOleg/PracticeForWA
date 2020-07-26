import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news/news.service';
import { GiphyService } from '../giphy/giphy.service';

@Component({
  selector: 'app-news-list',
  templateUrl: './news-list.component.html',
  styleUrls: ['./news-list.component.css']
})
export class NewsListComponent implements OnInit {
  news: Array<any>;

  constructor(private carService: NewsService, private giphyService: GiphyService) { }

  ngOnInit() {
    this.carService.getAll().subscribe(data => {
      this.news = data;
      for (const newss of this.news) {
        this.giphyService.get(newss.name).subscribe(url => newss.giphyUrl = url);
      }
    });
  }
}
