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

  constructor(private pacotesService: PacotesService) { }

  ngOnInit(): void {
    this.getPacotes();
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

}
