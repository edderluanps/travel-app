import { Component, OnInit } from '@angular/core';
import { BlogService } from 'src/app/service/blog.service';
import Swal from 'sweetalert2';
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
    this.blogService.getPosts().subscribe(response =>
      this.post = response, error =>{
        Swal.fire('Oops... Ocorreu um erro: ' + error.message);
      });
  }

  getPostById(id: number){
    this.blogService.getPostById(id).subscribe(response =>
      this.post = response, error => {
        Swal.fire('Oops... Ocorreu um erro: ' + error.message);
    });
  }

  getResultadoPesquisa(){
    this.blogService.pesquisaPost(this.titulo).subscribe(response =>
      this.post = response, error => {
        Swal.fire('Oops... Ocorreu um erro: ' + error.message);
    });
  }

}
