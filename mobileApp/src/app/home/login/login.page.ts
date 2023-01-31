import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { CredenciaisDTO } from 'src/app/model/credenciais.dto';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  credenciais: CredenciaisDTO = {
    email: '',
    senha: ''
  }

  constructor(
    private alertController: AlertController,
    private authService: AuthService,
    public router: Router) { }

  async presentAlert(header: string, subHeader: string, message: string) {
    const alert = await this.alertController.create({
      header: header,
      subHeader: subHeader,
      message: message,
      buttons: ['OK'],
    });

    await alert.present();
  }

  ngOnInit(): void {
  }

  login() {
    this.authService.authenticate(this.credenciais).subscribe(response => {
      this.authService.successfulLogin(response.headers.get('Authorization') || '');
      this.presentAlert('Seja bem vindo(a)', 'Olá', 'Bem vindo(a)');
      this.router.navigate(['/homepage']);
    }, error => {
      if (error.status == 401) {
        this.presentAlert('Erro', 'Oops. Ocorreu um erro', 'Login ou senha inválidos');

      } else if (error.status == 403) {
        this.presentAlert('Erro', 'Oops. Ocorreu um erro', 'Não permitido. cód: ' + error.status);

      } else {
        this.presentAlert('Seja bem vindo(a)', 'Olá', 'Bem vindo(a)');
      }
    });
  }

  signup() {
    this.router.navigate(['/signup']);
  }

}
