import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/environments/environment.prod';
import { Post } from '../model/post';

@Injectable({
  providedIn: 'root'
})
export class BlogService {

  constructor(private httpClient: HttpClient) { }

  getPosts() : Observable<Post[]>{
    return this.httpClient.get<Post[]>(`${API_URL}api/posts/`);
  }

  getPostById(id: number) : Observable<Post>{
    return this.httpClient.get<any>(`${API_URL}api/posts/${id}`);
  }

  pesquisaPost(titulo: string) : Observable<Post>{
    return this.httpClient.get<any>(`${API_URL}api/posts/resultados-pesquisa?titulo=${titulo}`);
  }

  getLastPosts() : Observable<Post>{
    return this.httpClient.get<any>(`${API_URL}api/posts/ultimos-posts`);
  }

}
