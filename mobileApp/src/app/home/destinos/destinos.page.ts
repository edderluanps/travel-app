import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { Cidade } from 'src/app/model/cidade';
import { DestinosService } from 'src/app/service/destinos.service';

@Component({
  selector: 'app-locais',
  templateUrl: './destinos.page.html',
  styleUrls: ['./destinos.page.scss'],
})
export class LocaisPage implements OnInit {

  cidade: any;
  nome : String;

  constructor(
    private destinosService: DestinosService,
    private alertController: AlertController,
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
   this.getLocais();
  }

  getLocais() {
    this.destinosService.getCidade().subscribe(response => this.cidade = response, error =>{
      this.presentAlert('Erro','Oops... Ocorreu um erro','Erro: '+ error.message);
    });
  }

}
