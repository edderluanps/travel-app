import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { JwtHelperService } from "@auth0/angular-jwt";
import { API_URL } from "src/environments/environment";
import { CredenciaisDTO } from "../home/login/credenciais.dto";
import { LocalUser } from "../home/login/local_user";
import { StorageService } from "./storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  jwtHelper : JwtHelperService = new JwtHelperService();

  constructor(private httpClient: HttpClient, public storage : StorageService) { }

  authenticate(credenciais: CredenciaisDTO){
    return this.httpClient.post(`${API_URL}login`, credenciais, {
      observe: 'response',
      responseType: 'text'
    });
  }

  successfulLogin(authorizationValue : string){
    let tok = authorizationValue.substring(7);
    let user : LocalUser = {
      token: tok, email: this.jwtHelper.decodeToken(tok).sub
    };
    this.storage.setLocalUser(user);
  }

  /**
  logout(){
    this.storage.setLocalUser(null);
  }
  **/
}
