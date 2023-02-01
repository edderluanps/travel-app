import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { Cliente } from 'src/app/model/cliente';
import { ClienteDTO } from 'src/app/model/cliente.dto';
import { AuthService } from 'src/app/service/auth.service';
import { ClienteService } from 'src/app/service/cliente.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.page.html',
  styleUrls: ['./perfil.page.scss'],
})
export class PerfilPage implements OnInit {

  cliente: ClienteDTO;
  cli: Cliente;

  handlerMessage = '';

  constructor(
    private alertController: AlertController,
    public storageService: StorageService,
    public clienteService: ClienteService,
    public router: Router,
    private authService: AuthService) { }

    async presentAlert() {
      const alert = await this.alertController.create({
        header: 'Deseja desconectar?',
        buttons: [
          {
            text: 'Cancel',
            role: 'cancel',
            handler: () => {
              this.handlerMessage = 'Alert canceled';
            },
          },
          {
            text: 'OK',
            role: 'confirm',
            handler: () => {
              this.handlerMessage = 'Alert confirmed';
              this.logOut();
            },
          },
        ],
      });

      await alert.present();
    }

  ngOnInit() {
    let localUser = this.storageService.getLocalUser();
    if (localUser && localUser.email) {
      this.clienteService.findByEmail(localUser.email).subscribe(response => {
        this.cliente = response as ClienteDTO
      }, error => {
        if (error.status == 403) {
          alert('Oops... Ocorreu um erro: ' + error.message);
          this.router.navigate(['/login']);
        }
      });
    } else {
      this.router.navigate(['/login']);
    }
  }

  logOut() {
    this.authService.logOut();
    this.router.navigate(['/']);
  }
}
