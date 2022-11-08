import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/environments/environment';
import { Post } from './Post';

@Injectable({
  providedIn: 'root'
})
export class BlogService {

  constructor(private httpClient: HttpClient) { }


  getAllPosts() : Observable<Post[]>{
    return this.httpClient.get<Post[]>(`${API_URL}posts/`);
  }

}
