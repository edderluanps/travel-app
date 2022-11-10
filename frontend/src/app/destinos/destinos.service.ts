import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_URL } from 'src/environments/environment';
import { Cidade } from './Cidade';

@Injectable({
  providedIn: 'root'
})
export class DestinosService {

  constructor(private httpClient: HttpClient) { }

  getAllCidades() : Observable<Cidade[]>{
    return this.httpClient.get<Cidade[]>(`${API_URL}cidade/`);
  }

}
