import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { CarrinhoItem } from 'src/app/model/carrinho-item';
import { ClienteDTO } from 'src/app/model/cliente.dto';
import { PedidoDTO } from 'src/app/model/pedido.dto';
import { CarrinhoService } from 'src/app/service/carrinho.service';
import { ClienteService } from 'src/app/service/cliente.service';
import { PacotesService } from 'src/app/service/pacotes.service';
import { PedidoService } from 'src/app/service/pedido.service';

@Component({
  selector: 'app-confirmacao',
  templateUrl: './confirmacao.page.html',
  styleUrls: ['./confirmacao.page.scss'],
})
export class ConfirmacaoPage implements OnInit {

  pedido: PedidoDTO;
  carrinhoItem: CarrinhoItem[];
  cliente: ClienteDTO;
  codpedido: string;
  pgType: any;

  constructor(
    public clienteService: ClienteService,
    public carrinhoService: CarrinhoService,
    public pacoteService: PacotesService,
    public router: Router,
    private pedidoService: PedidoService,
    private alertController : AlertController) { }

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
    this.carrinhoItem = this.carrinhoService.getCart().items;
    this.total();
    let param = window.history.state;
    this.pgType = param.pedido.pagamento;

    this.clienteService.findById(param.pedido.cliente.id)
      .subscribe(response => {
        this.cliente = response as ClienteDTO;
      },
        error => {
          this.presentAlert('Erro', 'Oops... Ocorreu um erro: ', error.message);
          this.router.navigate(['/login']);
        });

  }

  total(): number {
    return this.carrinhoService.total();
  }

  voltar() {
    this.router.navigate(['/checkout']);
  }

  confirmarPedido() {
    let obj = window.history.state

    this.pedidoService.registrarPedido(obj.pedido)
      .subscribe(response => {
        this.carrinhoService.createOrClearCart();
        this.codpedido = this.extractId(response.headers.get('location') || '');
        this.router.navigate(['/homepage']);
        this.presentAlert('Confirmado', 'Seu pedido foi confirmado', 'Confirmação');
      },
        error => {
          if (error.status == 403) {
            this.presentAlert('Erro', 'Oops... Ocorreu um erro: ', error.message);
            this.router.navigate(['/login']);
          }
        });
  }

  private extractId(location: string): string {
    let position = location.lastIndexOf('');
    return location.substring(position + 1, location.length);
  }
}
