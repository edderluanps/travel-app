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

  loginForm(){
    this.router.navigate(['/login']);
  }

  signupForm(){
    this.router.navigate(['/signup']);
  }

}
