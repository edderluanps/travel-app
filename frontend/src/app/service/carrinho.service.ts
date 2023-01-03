import { Injectable } from '@angular/core';
import { Carrinho } from '../model/carrinho';
import { PacoteDTO } from '../model/pacote.dto';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class CarrinhoService {

  constructor(public storage: StorageService) { }

  createOrClearCart(): Carrinho {
    let cart: Carrinho = { items: [] };
    this.storage.setCarrinho(cart);
    return cart;
  }

  getCart(): Carrinho {
    let cart: Carrinho = this.storage.getCarrinho();
    if (cart == null) {
      cart = this.createOrClearCart();
    }
    return cart;
  }

  addPacote(pacote: PacoteDTO): Carrinho {
    let cart = this.getCart();
    let position = cart.items.findIndex(x => x.pacote.id == pacote.id);
    if (position == -1) {
      cart.items.push({ quantidade: 1, pacote: pacote });
    }
    this.storage.setCarrinho(cart);
    return cart;
  }

  removeProduto(pacote: PacoteDTO): Carrinho {
    let cart = this.getCart();
    let position = cart.items.findIndex(x => x.pacote.id == pacote.id);
    if (position != -1) {
      cart.items.splice(position, 1);
    }
    this.storage.setCarrinho(cart);
    return cart;
  }

  increaseQuantity(pacote: PacoteDTO): Carrinho {
    let cart = this.getCart();
    let position = cart.items.findIndex(x => x.pacote.id == pacote.id);
    if (position != -1) {
      cart.items[position].quantidade++;
    }
    this.storage.setCarrinho(cart);
    return cart;
  }

  decreaseQuantity(pacote: PacoteDTO): Carrinho {
    let cart = this.getCart();
    let position = cart.items.findIndex(x => x.pacote.id == pacote.id);
    if (position != -1) {
      cart.items[position].quantidade--;
      if (cart.items[position].quantidade < 1) {
        cart = this.removeProduto(pacote);
      }
    }
    this.storage.setCarrinho(cart);
    return cart;
  }

  total(): number {
    let cart = this.getCart();
    let sum = 0;
    for (var i = 0; i < cart.items.length; i++) {
      sum += cart.items[i].pacote.preco * cart.items[i].quantidade;
    }
    return sum;
  }

}
