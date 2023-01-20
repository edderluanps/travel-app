import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CredenciaisDTO } from 'src/app/model/credenciais.dto';
import { AuthService } from 'src/app/service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credenciais: CredenciaisDTO = {
    email: '',
    senha: ''
  }

  constructor(public authService: AuthService, public router: Router) { }

  ngOnInit(): void {

  }

  login(){
    this.authService.authenticate(this.credenciais).subscribe(response => {
      this.authService.successfulLogin(response.headers.get('Authorization') || '');
      Swal.fire('Seja bem vindo(a)');
      this.router.navigate(['/']);
    });
  }

  refreshToken(){
    this.authService.refreshToken().subscribe(response => {
      this.authService.successfulLogin(response.headers.get('Authorization') || '');
      this.router.navigate(['/']);
    });
  }

  signup(){
    this.router.navigate(['/signup']);
  }

}
