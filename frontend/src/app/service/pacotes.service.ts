import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/environments/environment';
import { Pacotes } from '../home/pacotes/pacotes';

@Injectable({
  providedIn: 'root'
})
export class PacotesService {

  constructor(private httpClient: HttpClient) { }

  getPacotes() : Observable<Pacotes[]>{
    return this.httpClient.get<Pacotes[]>(`${API_URL}pacote/`);
  }

  getPacoteById(id: number) : Observable<Pacotes>{
    return this.httpClient.get<any>(`${API_URL}pacote/${id}`);
  }

}
