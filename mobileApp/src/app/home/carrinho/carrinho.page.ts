import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarrinhoItem } from 'src/app/model/carrinho-item';
import { PacoteDTO } from 'src/app/model/pacote.dto';
import { CarrinhoService } from 'src/app/service/carrinho.service';

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.page.html',
  styleUrls: ['./carrinho.page.scss'],
})
export class CarrinhoPage implements OnInit {

  items: CarrinhoItem[];

  constructor(
    private carrinhoService : CarrinhoService,
    private router : Router) { }

  ngOnInit(): void {
    let cart = this.carrinhoService.getCart();
    this.items = cart.items;
  }

  removeItem(pacote: PacoteDTO) {
    this.items = this.carrinhoService.removeProduto(pacote).items;
  }

  increaseQuantity(pacote: PacoteDTO) {
    this.items = this.carrinhoService.increaseQuantity(pacote).items;
  }

  decreaseQuantity(pacote: PacoteDTO) {
    this.items = this.carrinhoService.decreaseQuantity(pacote).items;
  }

  total() : number {
    return this.carrinhoService.total();
  }

  goOn() {
    this.router.navigate(['/pacotes']);
  }

  checkout() {
    this.router.navigate(['/checkout']);
  }

  testeCarrinho(){
    alert('OK');
  }

}
