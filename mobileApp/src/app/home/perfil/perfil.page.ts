import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.page.html',
  styleUrls: ['./perfil.page.scss'],
})
export class PerfilPage implements OnInit {

  constructor(public router: Router, private authService : AuthService) { }

  ngOnInit() {
  }

  logOut(){
    this.authService.logOut();
    this.router.navigate(['/']);
  }

}
