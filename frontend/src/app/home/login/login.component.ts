import { Component, OnInit, TemplateRef } from '@angular/core';
import { Route, Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { CredenciaisDTO } from './credenciais.dto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credenciais : CredenciaisDTO = {
    email: "",
    senha: ""
  };

  constructor(public authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  login(){
    this.authService.authenticate(this.credenciais).subscribe(response => {
     this.authService.successfulLogin(response.headers.get('Authorization') || '{}');
     this.router.navigate(['']);
    }, error => { });

  }

}
