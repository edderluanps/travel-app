import { Component, OnInit } from '@angular/core';
import { DestinosService } from 'src/app/service/destinos.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-destinos',
  templateUrl: './destinos.component.html',
  styleUrls: ['./destinos.component.css']
})
export class DestinosComponent implements OnInit {

  cidade: any;
  nome: string;

  constructor(private destinoService : DestinosService) { }

  ngOnInit(): void {
    this.getPacotes();
  }

  getPacotes() {
    this.destinoService.getCidade().subscribe(response => this.cidade = response, error =>{
      Swal.fire('Oops... Ocorreu um erro: ' + error.message);
    });
  }

}
