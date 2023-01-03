import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PacoteDTO } from 'src/app/model/pacote.dto';
import { Pacotes } from 'src/app/model/pacotes';
import { CarrinhoService } from 'src/app/service/carrinho.service';
import { PacotesService } from 'src/app/service/pacotes.service';

@Component({
  selector: 'app-pacote-page',
  templateUrl: './pacote-page.component.html',
  styleUrls: ['./pacote-page.component.css']
})
export class PacotePageComponent implements OnInit {

  pacotes : Pacotes | any;

  constructor(
    private pacoteService : PacotesService,
    private route : ActivatedRoute,
    private router : Router,
    private carrinhoService : CarrinhoService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => this.getPacoteById(params['id']))
  }

  getPacoteById(id: number){
    this.pacoteService.getPacoteById(id).subscribe(response => {
      this.pacotes = response;
    });
  }

  addItemCarrinho(pacotes: PacoteDTO){
    this.carrinhoService.addPacote(pacotes);
    this.router.navigate(['/carrinho']);
  }

}
