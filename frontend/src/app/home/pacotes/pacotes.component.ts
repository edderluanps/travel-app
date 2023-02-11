import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PacotesService } from 'src/app/service/pacotes.service';
import Swal from 'sweetalert2';
import { Pacotes } from '../../model/pacotes';

@Component({
  selector: 'app-pacotes',
  templateUrl: './pacotes.component.html',
  styleUrls: ['./pacotes.component.css']
})
export class PacotesComponent implements OnInit {

  pacotes: any;
  nome: string;
  data: Date;
  dataF: string;

  constructor(private pacotesService: PacotesService) { }

  ngOnInit(): void {
    this.getPesquisa();
  }

  getPacotes() {
    this.pacotesService.getPacotes().subscribe(response => this.pacotes = response, error => {
      Swal.fire('Oops... Ocorreu um erro: ' + error.message);
    });
  }

  getPacoteById(id: number) {
    this.pacotesService.getPacoteById(id).subscribe(response => this.pacotes = response, error => {
      Swal.fire('Oops... Ocorreu um erro: ' + error.message);
    });
  }

  getResultadoPesquisa(){
    this.pacotesService.pesquisaPacotes(this.nome).subscribe(response => this.pacotes = response, error => {
      Swal.fire('Oops... Ocorreu um erro: ' + error.message);
    });
  }

  getPacotesByNomeAndDate(){
    let format = 'dd/MM/yyyy';
    let myDate = this.data;
    let formattedDate = formatDate(myDate, format, 'pt-BR');

    this.pacotesService.getPacotesByNomeAndDate(this.nome, formattedDate).subscribe(response => this.pacotes = response, error => {
      Swal.fire('Oops... Ocorreu um erro: ' + error.message);
    });
  }

  getPacotesByHomepageState(){
    let nomeParam = window.history.state.nome;
    let dataParam = window.history.state.data;

    this.dataF = dataParam
      let format = 'dd/MM/yyyy';
      let myDate = this.dataF;
      let formattedDate = formatDate(myDate, format, 'pt-BR');

      this.pacotesService.getPacotesByNomeAndDate(nomeParam, formattedDate).subscribe(response => this.pacotes = response, error => {
        Swal.fire('Oops... Ocorreu um erro: ' + error.message);
      });
  }

  getPesquisa(){
    let nomeParam = window.history.state.nome;
    let dataParam = window.history.state.data;

    if(nomeParam == undefined || dataParam == undefined){
      this.getPacotes();
    }else{
    this.getPacotesByHomepageState();

    }

  }

}
