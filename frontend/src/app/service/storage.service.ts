import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { LocalUser } from "../home/login/local_user";
import { STORAGE_KEYS } from "../home/login/storage_keys.config";

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor(private httpClient: HttpClient) { }

  getLocalUser() : LocalUser | null{
    let localUser = localStorage.getItem(STORAGE_KEYS.localUser);
    if (localUser == null) {
        return null;
    } else {
      return JSON.parse(localUser);
    }
  }

  setLocalUser(objeto: LocalUser){
    if (objeto == null) {
      localStorage.removeItem(STORAGE_KEYS.localUser);
    } else {
      localStorage.setItem(STORAGE_KEYS.localUser, JSON.stringify(objeto));
    }
  }

}
