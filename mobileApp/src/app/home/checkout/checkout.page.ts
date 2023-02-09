import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { EnderecoDTO } from 'src/app/model/endereco.dto';
import { PedidoDTO } from 'src/app/model/pedido.dto';
import { CarrinhoService } from 'src/app/service/carrinho.service';
import { ClienteService } from 'src/app/service/cliente.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.page.html',
  styleUrls: ['./checkout.page.scss'],
})
export class CheckoutPage implements OnInit {

  items: EnderecoDTO[];
  pedido: PedidoDTO;
  parcelas: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  formGroup: FormGroup;

  constructor(public storage: StorageService,
    public clienteService: ClienteService,
    public router: Router,
    public route: ActivatedRoute,
    private carrinhoService: CarrinhoService,
    public formBuilder: FormBuilder,
    private alertController: AlertController) {

      this.formGroup = this.formBuilder.group({
        endereco: ['', Validators.required],
        numParcelas: [1, Validators.required],
        "@type": ["pgCartao", Validators.required]
      });
     }

    async presentAlert(header: string, subHeader: string, message: string) {
    const alert = await this.alertController.create({
      header: header,
      subHeader: subHeader,
      message: message,
      buttons: ['OK'],
    });

    await alert.present();
  }

  ngOnInit() : any{
    let localUser = this.storage.getLocalUser();
    if (localUser && localUser.email) {
      this.clienteService.findByEmail(localUser.email)
        .subscribe(response => {
          this.items = response['endereco'];

          let carrinho = this.carrinhoService.getCart();

          this.pedido = {
            cliente: {
              id: response['id']
            },
            pagamento: {
              numParcelas: null,
              "@type": null
            },
            itens: carrinho.items.map(x => {
              return {
                quantidade: x.quantidade, pacote: {
                  id: x.pacote.id
                }
              }
            })
          };

      }, error => {
        if (error.status == 403) {
          this.router.navigate(['/login']);
        }
      });
    } else {
      this.router.navigate(['/login']);
    }
  }

  nextStep(){
    this.pedido.pagamento = this.formGroup.value;
    this.router.navigate(['/confirmacao'], { state: { pedido : this.pedido }});
  }

  showAlertMsg(){
    this.presentAlert('Endereço',
    'Por motivos de segurança, exigimos seu endereço como forma de garantia. Mas não se preocupe, ',
    'seus dados estão seguros!');
  }

}
