import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarrinhoItem } from 'src/app/model/carrinho-item';
import { ClienteDTO } from 'src/app/model/cliente.dto';
import { EnderecoDTO } from 'src/app/model/endereco.dto';
import { PedidoDTO } from 'src/app/model/pedido.dto';
import { CarrinhoService } from 'src/app/service/carrinho.service';
import { ClienteService } from 'src/app/service/cliente.service';
import { PacotesService } from 'src/app/service/pacotes.service';
import { PedidoService } from 'src/app/service/pedido.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-confirmacao',
  templateUrl: './confirmacao.component.html',
  styleUrls: ['./confirmacao.component.css']
})
export class ConfirmacaoComponent implements OnInit {

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
    private pedidoService: PedidoService) { }

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
          Swal.fire('Oops... Ocorreu um erro: ' + error.message);
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
        this.router.navigate(['/']);
        Swal.fire('Pedido confirmado');
      },
        error => {
          if (error.status == 403) {
            Swal.fire('Oops... Ocorreu um erro: ' + error.message);
            this.router.navigate(['/login']);
          }
        });
  }

  private extractId(location: string): string {
    let position = location.lastIndexOf('');
    return location.substring(position + 1, location.length);
  }

  confirmar(){
    Swal.fire({
      title: 'Deseja confirmar seu pedido?',
      showDenyButton: false,
      showCancelButton: true,
      confirmButtonText: 'Confirmar',
      denyButtonText: `cancelar`,
    }).then((result) => {

      if (result.isConfirmed) {
        Swal.fire('Pedido confirmado!', '', 'success')
        this.confirmarPedido();
        this.router.navigate(['/homepage']);

      } else if (result.isDenied) {

      }
    })
  }

}
