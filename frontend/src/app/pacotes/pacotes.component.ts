import { Component, OnInit } from '@angular/core';
import { Pacotes } from './Pacotes';
import { PacotesService } from './pacotes.service';

@Component({
  selector: 'app-pacotes',
  templateUrl: './pacotes.component.html',
  styleUrls: ['./pacotes.component.css']
})
export class PacotesComponent implements OnInit {

  pacotes: Pacotes[];

  constructor(private pacotesService: PacotesService) { }

  ngOnInit(): void {
    this.getAllPacotes();
  }

  getAllPacotes(){
    this.pacotesService.getAllPacotes().subscribe(response => this.pacotes = (response));
  }

  getPacoteById(){

  }

}
