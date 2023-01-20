import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClienteDTO } from 'src/app/model/cliente.dto';
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
  }

  logout(){
    this.authService.logOut();
  }

}
