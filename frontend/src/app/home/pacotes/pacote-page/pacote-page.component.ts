import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pacotes } from 'src/app/model/pacotes';
import { PacotesService } from 'src/app/service/pacotes.service';

@Component({
  selector: 'app-pacote-page',
  templateUrl: './pacote-page.component.html',
  styleUrls: ['./pacote-page.component.css']
})
export class PacotePageComponent implements OnInit {

  pacotes : Pacotes | any;

  constructor(private pacoteService : PacotesService, private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => this.getPacoteById(params['id']))
  }

  getPacoteById(id: number){
    this.pacoteService.getPacoteById(id).subscribe(response => {
      this.pacotes = response;
    });
  }

}
