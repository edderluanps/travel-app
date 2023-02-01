import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { ClienteDTO } from 'src/app/model/cliente.dto';
import { AuthService } from 'src/app/service/auth.service';
import { ClienteService } from 'src/app/service/cliente.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.page.html',
  styleUrls: ['./homepage.page.scss'],
})
export class HomepagePage implements OnInit {

  cliente: ClienteDTO;

  constructor(
    private alertController: AlertController,
    public storageService: StorageService,
    public clienteService: ClienteService,
    public router: Router,
    private authService: AuthService) { }

  ngOnInit() {
    let localUser = this.storageService.getLocalUser();
    if (localUser && localUser.email) {
      this.clienteService.findByEmail(localUser.email).subscribe(response => {
        this.cliente = response as ClienteDTO
      }, error => {});
    }
  }

}
