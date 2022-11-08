import { Component, OnInit } from '@angular/core';
import { BlogService } from './blog.service';
import { Post } from './Post';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit {

  posts: Post[];

  constructor(private blogService: BlogService) { }

  ngOnInit(): void {
    this.getAllPosts();
  }

  getAllPosts(){
    this.blogService.getAllPosts().subscribe(response => this.posts = (response));
  }

  getPostById(){

  }

}
