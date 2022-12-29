import { Injectable } from '@angular/core';
import { STORAGE_KEYS } from '../home/login/storage_keys.config';
import { LocalUser } from '../model/local_user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  getLocalUser() : LocalUser | any{
    let userLocal = localStorage.getItem(STORAGE_KEYS.localUser);
    if(userLocal == null){
      return null;
    }
    else{
      return JSON.parse(userLocal);
    }
  }

  setLocalUser(local : LocalUser){
    if(local == null){
      localStorage.removeItem(STORAGE_KEYS.localUser);
    }else{
      localStorage.setItem(STORAGE_KEYS.localUser, JSON.stringify(local));
    }
  }
}
