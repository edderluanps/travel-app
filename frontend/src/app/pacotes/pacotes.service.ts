import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/environments/environment';
import { Pacotes } from './Pacotes';

@Injectable({
  providedIn: 'root'
})
export class PacotesService {

  constructor(private httpClient: HttpClient) { }


  getAllPacotes() : Observable<Pacotes[]>{
    return this.httpClient.get<Pacotes[]>(`${API_URL}pacote/`);
  }
}
