import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { BlogService } from 'src/app/service/blog.service';

@Component({
  selector: 'app-blog-post',
  templateUrl: './blog-post.page.html',
  styleUrls: ['./blog-post.page.scss'],
})
export class BlogPostPage implements OnInit {

  post: any;

  constructor(
    private blogService: BlogService,
    private route: ActivatedRoute,
    private alertController: AlertController) { }

  async presentAlert(header: string, subHeader: string, message: string) {
    const alert = await this.alertController.create({
      header: header,
      subHeader: subHeader,
      message: message,
      buttons: ['OK'],
    });

    await alert.present();
  }

  ngOnInit() {
    this.route.params.subscribe(params => this.getPostById(params['id']));

  }

  getPostById(id: number) {
    this.blogService.getPostById(id).subscribe(response =>
      this.post = response, error => {
        this.presentAlert('Erro', 'Oops... Ocorreu um erro', 'Erro: ' + error.message);
      }
    );
  }

}
