import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/environments/environment';
import { ClienteDTO } from '../model/cliente.dto';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private httpCliente: HttpClient, public storageService: StorageService) { }

  findByEmail(email: string) : Observable<ClienteDTO>{
    return this.httpCliente.get<ClienteDTO>(`${API_URL}api/cliente/email?value=${email}`);
  }
}
