import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Pacotes } from 'src/app/model/pacotes';
import { AuthService } from 'src/app/service/auth.service';
import { BlogService } from 'src/app/service/blog.service';
import { PacotesService } from 'src/app/service/pacotes.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  pacotes: any;
  posts: any

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
    this.pacotesService.getLastPacotes().subscribe(response => this.pacotes = (response));
  }

  listLastThreePosts(){
    this.blogService.getLastPosts().subscribe(response => this.posts = (response));
  }

}
