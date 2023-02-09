import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController, IonSlides } from '@ionic/angular';
import { Cliente } from 'src/app/model/cliente';
import { ClienteDTO } from 'src/app/model/cliente.dto';
import { EnderecoDTO } from 'src/app/model/endereco.dto';
import { PedidoDTO } from 'src/app/model/pedido.dto';
import { AuthService } from 'src/app/service/auth.service';
import { ClienteService } from 'src/app/service/cliente.service';
import { PedidoService } from 'src/app/service/pedido.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.page.html',
  styleUrls: ['./perfil.page.scss'],
})
export class PerfilPage implements OnInit {
  @ViewChild(IonSlides) slides: IonSlides;

  cliente: ClienteDTO;
  cli: Cliente;
  items: EnderecoDTO[];
  pedido: PedidoDTO | any;
  handlerMessage = '';

  constructor(
    private alertController: AlertController,
    public storageService: StorageService,
    public clienteService: ClienteService,
    public router: Router,
    private authService: AuthService,
    private pedidoService: PedidoService) { }

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

    async presentAlert2(header: string, subHeader: string, message: string) {
      const alert = await this.alertController.create({
        header: header,
        subHeader: subHeader,
        message: message,
        buttons: ['OK'],
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
          this.presentAlert2('Erro', 'Oops... Ocorreu um erro: ' + error.message, 'Erro');
          this.router.navigate(['/login']);
        }
      });
    } else {
      this.router.navigate(['/login']);
    }
    this.getUserAllData();
    this.getEnderecos();
    this.getPedidos();
  }

  getUserAllData(){
    let localUser = this.storageService.getLocalUser();
    this.clienteService.findByEmail(localUser.email).subscribe(response => {
      this.cli = response as Cliente;
    });
  }

  getEnderecos(){
    let localUser = this.storageService.getLocalUser();
    if (localUser && localUser.email) {
      this.clienteService.findByEmail(localUser.email)
        .subscribe(response => {
          this.items = response['endereco'];});
    } else {

    }
  }

  getPedidos(){

    let localUser = this.storageService.getLocalUser();
    this.clienteService.findByEmail(localUser.email).subscribe(response => {
      this.cli = response as Cliente;
      this.pedidoService.getPedidoByUserId(this.cli.id).subscribe(response => {
        this.pedido = response, error => {
          this.presentAlert2('Erro', 'Oops... Ocorreu um erro: ' + error.message, 'Erro');
        }
      });
    });

  }

  logOut() {
    this.authService.logOut();
    this.router.navigate(['/']);
  }

  public slideMenu(): void {
    this.slides.slideTo(0, 0);
  }

  public slideDadosUsuario(): void {
    this.slides.slideTo(1, 0);
  }

  public slidePedidos(): void {
    this.slides.slideTo(2, 0);
  }

}
