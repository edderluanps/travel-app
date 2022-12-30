import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private authService: AuthService, private router : Router) { }

  ngOnInit(): void {
  }

  logOut(){
    this.authService.logOut();
    this.router.navigate(['/']);
  }

}
