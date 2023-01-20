import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from 'src/environments/environment';
import { PedidoDTO } from '../model/pedido.dto';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  constructor(private httpClient: HttpClient) { }

  registrarPedido(pedido: PedidoDTO) {
    return this.httpClient.post(
      `${API_URL}api/pedido`, pedido, {
      observe: 'response',
      responseType: 'text'
    }
    );
  }
}