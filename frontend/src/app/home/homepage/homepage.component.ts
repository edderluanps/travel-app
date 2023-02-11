import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Pacotes } from 'src/app/model/pacotes';
import { AuthService } from 'src/app/service/auth.service';
import { BlogService } from 'src/app/service/blog.service';
import { PacotesService } from 'src/app/service/pacotes.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  pacotes: any;
  posts: any
  nome: string;
  data: Date;

  constructor(private authService: AuthService,
    private router : Router,
    private pacotesService : PacotesService,
    private blogService : BlogService) { }

  ngOnInit(): void {
    this.listLastThreePacotes();
    this.listLastThreePosts();
  }

  logOut(){
    this.authService.logOut();
    this.router.navigate(['/']);
  }

  listLastThreePacotes(){
    this.pacotesService.getLastPacotes().subscribe(response => this.pacotes = response, error =>{
      Swal.fire('Oops... Ocorreu um erro: ' + error.message);
    });
  }

  listLastThreePosts(){
    this.blogService.getLastPosts().subscribe(response => this.posts = response, error => {
      Swal.fire('Oops... Ocorreu um erro: ' + error.message);
    });
  }

  buscarPacotes(){
    this.router.navigate(['/pacotes'], { state: { nome : this.nome, data : this.data }});
  }

}
