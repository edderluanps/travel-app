import { Component, OnInit } from '@angular/core';
import { Cidade } from './Cidade';
import { DestinosService } from './destinos.service';

@Component({
  selector: 'app-destinos',
  templateUrl: './destinos.component.html',
  styleUrls: ['./destinos.component.css']
})
export class DestinosComponent implements OnInit {

  cidade: Cidade[];

  constructor(private destinosService: DestinosService) { }

  ngOnInit(): void {
    this.getAllCidades();

  }

  getAllCidades(){
    this.destinosService.getAllCidades().subscribe(response => this.cidade = (response));
  }

  getCidadeById(){

  }


}
