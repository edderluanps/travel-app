import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { API_URL } from "src/environments/environment";
import { Pedido } from "../model/pedido";
import { PedidoDTO } from "../model/pedido.dto";

@Injectable()
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

  getPedidos() : Observable<Pedido[]>{
    return this.httpClient.get<Pedido[]>(`${API_URL}api/pedido/`);
  }
}
