import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cliente } from 'src/app/model/cliente';
import { ClienteDTO } from 'src/app/model/cliente.dto';
import { EnderecoDTO } from 'src/app/model/endereco.dto';
import { AuthService } from 'src/app/service/auth.service';
import { ClienteService } from 'src/app/service/cliente.service';
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

  constructor(
    public storage: StorageService,
    public clienteService: ClienteService,
    public router: Router,
    private authService : AuthService) { }

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
