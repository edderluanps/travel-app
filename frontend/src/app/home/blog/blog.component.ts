import { Component, OnInit } from '@angular/core';
import { BlogService } from 'src/app/service/blog.service';
import { Post } from '../../model/post';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit {

  post: any;
  titulo: string;

  constructor(private blogService: BlogService) { }

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts(){
    this.blogService.getPosts().subscribe(response => {this.post = (response)});
  }

  getPostById(id: number){
    this.blogService.getPostById(id).subscribe(response => this.post = (response));
  }

  getResultadoPesquisa(){
    this.blogService.pesquisaPost(this.titulo).subscribe(response => this.post = (response));
  }

}
