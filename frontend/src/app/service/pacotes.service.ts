import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/environments/environment';
import { Pacotes } from '../model/pacotes';

@Injectable({
  providedIn: 'root'
})
export class PacotesService {

  constructor(private httpClient: HttpClient) { }

  getPacotes() : Observable<Pacotes[]>{
    return this.httpClient.get<Pacotes[]>(`${API_URL}api/pacote/`);
  }

  getPacoteById(id: number) : Observable<Pacotes>{
    return this.httpClient.get<any>(`${API_URL}api/pacote/${id}`);
  }

  pesquisaPacotes(nome: string) : Observable<Pacotes>{
    return this.httpClient.get<any>(`${API_URL}api/pacote/resultados-pesquisa?nome=${nome}`);
  }

  getLastPacotes() : Observable<Pacotes>{
    return this.httpClient.get<any>(`${API_URL}api/pacote/ultimos-pacotes`);
  }

}
