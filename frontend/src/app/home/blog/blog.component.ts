import { Component, OnInit } from '@angular/core';
import { BlogService } from 'src/app/service/blog.service';
import { Post } from './post';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit {

  post: Post[];

  constructor(private blogService: BlogService) { }

  ngOnInit(): void {
    this.getPosts();
    console.log(this.getPosts());
  }

  getPosts(){
    this.blogService.getPosts().subscribe(response => this.post = (response));
    console.log();
  }

  getPostById(){

  }

}
