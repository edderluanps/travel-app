import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/environments/environment.prod';
import { Cidade } from '../model/cidade';

@Injectable({
  providedIn: 'root'
})
export class DestinosService {

  constructor(private httpClient: HttpClient) { }

  getCidade() : Observable<Cidade[]>{
    return this.httpClient.get<Cidade[]>(`${API_URL}api/cidade/`);
  }

  getCidadeById(id: number) : Observable<Cidade>{
    return this.httpClient.get<any>(`${API_URL}api/cidade/${id}`);
  }

}
