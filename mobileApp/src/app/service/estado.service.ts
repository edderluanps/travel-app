import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/environments/environment';
import { Estado } from '../model/estado';

@Injectable()
export class EstadoService {

  constructor(private httpClient: HttpClient) { }

  findEstado() : Observable<Estado[]>{
    return this.httpClient.get<Estado[]>(`${API_URL}api/estado`);
  }

}
