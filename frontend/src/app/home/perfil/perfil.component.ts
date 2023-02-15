import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cliente } from 'src/app/model/cliente';
import { ClienteDTO } from 'src/app/model/cliente.dto';
import { EnderecoDTO } from 'src/app/model/endereco.dto';
import { PedidoDTO } from 'src/app/model/pedido.dto';
import { AuthService } from 'src/app/service/auth.service';
import { ClienteService } from 'src/app/service/cliente.service';
import { PedidoService } from 'src/app/service/pedido.service';
import { StorageService } from 'src/app/service/storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  cliente: ClienteDTO;
  cli: Cliente;
  items: EnderecoDTO[];
  pedido: PedidoDTO | any;

  constructor(
    public storage: StorageService,
    public clienteService: ClienteService,
    public router: Router,
    private authService : AuthService,
    private storageService: StorageService,
    private pedidoService: PedidoService) { }

  ngOnInit() {
    let localUser = this.storage.getLocalUser();
    if (localUser && localUser.email) {
      this.clienteService.findByEmail(localUser.email).subscribe(response => {
        this.cliente = response as ClienteDTO
      }, error => {
        if (error.status == 403) {
          Swal.fire('Oops... Ocorreu um erro: ' + error.message);
          this.router.navigate(['/login']);
        }
      });
    } else {
      this.router.navigate(['/login']);
    }
    this. getUserAllData();
    this.getEnderecos();
    this.getPedidos();
  }

  logout(){
    this.authService.logOut();
  }

  getUserAllData(){
    let localUser = this.storage.getLocalUser();
    this.clienteService.findByEmail(localUser.email).subscribe(response => {
      this.cli = response as Cliente;
    });
  }

  getEnderecos(){
    let localUser = this.storage.getLocalUser();
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
          Swal.fire('Oops... Ocorreu um erro: ' + error.message);
        }
      });
    });
  }

  getPdf(id: number) {
    this.pedidoService.getPdfReport(id).subscribe(response => {
      const blob = new Blob([response], {type: 'application/pdf'});

      const data = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = data;
      link.download = 'report.pdf';
      link.dispatchEvent(new MouseEvent('click', {bubbles: true, cancelable: true, view: window}));

      setTimeout(function(){
        window.URL.revokeObjectURL(data);
        link.remove();
      }, 100);
      Swal.fire('Comprovante emitido com sucesso');

    }, error => {
      Swal.fire('Oops... Ocorreu um erro: ' + error.message);
    })
  }

  desconectar(){
    Swal.fire({
      title: 'Deseja desconectar?',
      showDenyButton: false,
      showCancelButton: true,
      confirmButtonText: 'Sair',
      denyButtonText: `Permanecer`,
    }).then((result) => {

      if (result.isConfirmed) {
        Swal.fire('Desconectado!', '', 'success')
        this.logout();
        this.router.navigate(['/login']);

      } else if (result.isDenied) {

      }
    })
  }

}
