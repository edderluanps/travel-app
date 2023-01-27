import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { Pacotes } from 'src/app/model/pacotes';
import { PacotesService } from 'src/app/service/pacotes.service';

@Component({
  selector: 'app-pacote-page',
  templateUrl: './pacote-page.page.html',
  styleUrls: ['./pacote-page.page.scss'],
})
export class PacotePagePage implements OnInit {

  pacotes: any;

  constructor(
    private pacoteService: PacotesService,
    private route: ActivatedRoute,
    private alertController: AlertController) { }

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
    this.route.params.subscribe(params => this.getPacoteById(params['id']));
  }

  getPacoteById(id: number) {
    this.pacoteService.getPacoteById(id).subscribe(response =>
      this.pacotes = response, error => {
        this.presentAlert('Erro', 'Oops... Ocorreu um erro', 'Erro: ' + error.message);
      }
    );
  }
}
