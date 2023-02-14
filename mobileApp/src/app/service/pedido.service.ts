import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { API_URL } from "src/environments/environment";
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

  getPedidoByUserId(id: number) : Observable<PedidoDTO>{
    return this.httpClient.get<any>(`${API_URL}api/pedido/userPedidos?id=${id}`);
  }

  getPdfReport(id: number) : Observable<Blob>{
    return this.httpClient.get(`${API_URL}api/pedido/comprovante/${id}`, {
      responseType: 'blob'
    });
  }

}
