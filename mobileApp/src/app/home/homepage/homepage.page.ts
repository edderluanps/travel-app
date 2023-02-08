import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController, IonSlides } from '@ionic/angular';
import { ClienteDTO } from 'src/app/model/cliente.dto';
import { ClienteService } from 'src/app/service/cliente.service';
import { DestinosService } from 'src/app/service/destinos.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.page.html',
  styleUrls: ['./homepage.page.scss'],
})
export class HomepagePage implements OnInit {

  @ViewChild('mySlider')  slides: IonSlides;

  cliente: ClienteDTO;
  cidade: any;
  nome: string;

  slideOptsOne = {
    initialSlide: 0,
    slidesPerView: 1,
    autoplay:true
   };

  constructor(
    private alertController: AlertController,
    public storageService: StorageService,
    public clienteService: ClienteService,
    public router: Router,
    private destinoService: DestinosService) { }

  ngOnInit() {
    let localUser = this.storageService.getLocalUser();
    if (localUser && localUser.email) {
      this.clienteService.findByEmail(localUser.email).subscribe(response => {
        this.cliente = response as ClienteDTO
      }, error => {
        if (error.status == 403) {
          alert('Oops... Ocorreu um erro: ' + error.message);
          this.router.navigate(['/login']);
        }
      });
    } else {
      this.router.navigate(['/login']);
    }

    this.getPacotes();
  }

  getPacotes() {
    this.destinoService.getCidade().subscribe(response => this.cidade = response, error =>{
      alert('Oops... Ocorreu um erro: ' + error.message);
    });
  }

  swipeNext(){
    this.slides.slideNext();
  }

}
