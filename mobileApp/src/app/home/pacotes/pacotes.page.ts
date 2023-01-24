import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { PacotesService } from 'src/app/service/pacotes.service';

@Component({
  selector: 'app-pacotes',
  templateUrl: './pacotes.page.html',
  styleUrls: ['./pacotes.page.scss'],
})
export class PacotesPage implements OnInit {

  pacotes: any;
  nome: string;

  constructor(
    private pacotesService: PacotesService,
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
    this.getPacotes();
  }

  getPacotes() {
    this.pacotesService.getPacotes().subscribe(response => this.pacotes = response, error => {
      this.presentAlert('Erro','Oops... Ocorreu um erro','Erro: '+ error.message);
    });
  }

  getPacoteById(id: number) {
    this.pacotesService.getPacoteById(id).subscribe(response => this.pacotes = response, error => {
      this.presentAlert('Erro','Oops... Ocorreu um erro','Erro: '+ error.message);
    });
  }

  getResultadoPesquisa(){
    this.pacotesService.pesquisaPacotes(this.nome).subscribe(response => this.pacotes = response, error => {
      this.presentAlert('Erro','Oops... Ocorreu um erro','Erro: '+ error.message);
    });
  }

}
