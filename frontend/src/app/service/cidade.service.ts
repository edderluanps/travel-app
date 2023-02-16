import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/environments/environment.prod';
import { CidadeDTO } from '../model/cidade.dto';

@Injectable({
  providedIn: 'root'
})
export class CidadeService {

  constructor(private httpClient: HttpClient) { }

  findCidade(estadoId: number) : Observable<CidadeDTO[]>{
    return this.httpClient.get<CidadeDTO[]>(`${API_URL}api/estado/${estadoId}/cidades`);
  }
}
