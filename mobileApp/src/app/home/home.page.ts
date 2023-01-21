import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  constructor(
    private alertController: AlertController,
    public router : Router) {}

  async presentAlert(header: string, subHeader: string, message: string) {
    const alert = await this.alertController.create({
      header: header,
      subHeader: subHeader,
      message: message,
      buttons: ['OK'],
    });

    await alert.present();
  }

  loginForm(){
    //this.presentAlert('Header here','Subheader here','message here');
    this.router.navigate(['/login']);
  }

  signupForm(){
    //this.presentAlert('Header here','Subheader here','message here');
    this.router.navigate(['/signup']);
  }

}
