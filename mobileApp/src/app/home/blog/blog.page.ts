import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { Post } from 'src/app/model/post';
import { BlogService } from 'src/app/service/blog.service';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.page.html',
  styleUrls: ['./blog.page.scss'],
})
export class BlogPage implements OnInit {

  post: any;
  titulo: string;

  constructor(
    private blogService: BlogService,
    private alertController: AlertController,
    public router: Router) { }

  async presentAlert(header: string, subHeader: string, message: string) {
    const alert = await this.alertController.create({
      header: header,
      subHeader: subHeader,
      message: message,
      buttons: ['OK'],
    });

    await alert.present();
  }

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts(){
    this.blogService.getPosts().subscribe(response =>
      this.post = response, error =>{
        this.presentAlert('Erro','Oops... Ocorreu um erro','Erro: '+ error.message);
      });
  }

  getPostById(id: number){
    this.blogService.getPostById(id).subscribe(response =>
      this.post = response, error => {
        this.presentAlert('Erro','Oops... Ocorreu um erro','Erro: '+ error.message);
    });
  }

  getResultadoPesquisa(){
    this.blogService.pesquisaPost(this.titulo).subscribe(response =>
      this.post = response, error => {
        this.presentAlert('Erro','Oops... Ocorreu um erro','Erro: '+ error.message);
    });
  }
}
