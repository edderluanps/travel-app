import { Component, OnInit } from '@angular/core';
import { BlogPageService } from './blog-page.service';
import { Post } from './Post';

@Component({
  selector: 'app-blog-page',
  templateUrl: './blog-page.component.html',
  styleUrls: ['./blog-page.component.css']
})
export class BlogPageComponent implements OnInit {

  posts: Post[];

  constructor(private blogPageServisce: BlogPageService) { }

  ngOnInit(): void {
    this.getAllPost();
  }

  getAllPost(): void {
    this.blogPageServisce.getAllPosts().subscribe(response => this.posts = (response));
  }

}
