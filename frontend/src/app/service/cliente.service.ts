import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from 'src/environments/environment';
import { ClienteDTO } from '../model/cliente.dto';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private httpClient: HttpClient, public storageService: StorageService) { }

  findById(id: string) {
    return this.httpClient.get(`${API_URL}api/cliente/${id}`);
  }

  findByEmail(email: string) {
    return this.httpClient.get(`${API_URL}api/cliente/email?value=${email}`);
  }

  saveUser(cliente: ClienteDTO){
    return this.httpClient.post(`${API_URL}api/cliente`, cliente, {
      observe: 'response',
      responseType: 'text'
    });
  }
}
