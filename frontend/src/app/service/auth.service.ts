import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CredenciaisDTO } from '../model/credenciais.dto';
import { API_URL } from 'src/environments/environment';
import { LocalUser } from '../model/local_user';
import { StorageService } from './storage.service';
import { JwtHelperService } from '@auth0/angular-jwt';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(
    private httpClient: HttpClient,
    public storageService: StorageService) { }

  authenticate(credenciais: CredenciaisDTO){
    return this.httpClient.post(`${API_URL}login`, credenciais, {
      observe: 'response', responseType: 'text'
    });
  }

  successfulLogin(authorizationValue: string){
    let tok = authorizationValue.substring(7);
    let user : LocalUser = {
      token: tok,
      email: this.jwtHelper.decodeToken(tok).sub
    }
    this.storageService.setLocalUser(user);
  }

  logOut(){
    let user : LocalUser = {
      token: '',
      email: ''
    }
    this.storageService.setLocalUser(user);
  }

  refreshToken(){
    return this.httpClient.post(`${API_URL}auth/refresh_token`,{},{
      observe: 'response', responseType: 'text'
    });
  }

}
