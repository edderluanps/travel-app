import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Post } from 'src/app/model/post';
import { BlogService } from 'src/app/service/blog.service';

@Component({
  selector: 'app-blog-post',
  templateUrl: './blog-post.component.html',
  styleUrls: ['./blog-post.component.css']
})
export class BlogPostComponent implements OnInit {

  post : Post | any;

  constructor(private postService : BlogService, private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => this.getPostById(params['id']));

  }

  getPostById(id: number){
    this.postService.getPostById(id).subscribe(response => {
      this.post = response;
    });
  }

}
