import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { CidadeDTO } from 'src/app/model/cidade.dto';
import { EstadoDTO } from 'src/app/model/estado.dto';
import { CidadeService } from 'src/app/service/cidade.service';
import { ClienteService } from 'src/app/service/cliente.service';
import { EstadoService } from 'src/app/service/estado.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.page.html',
  styleUrls: ['./signup.page.scss'],
})
export class SignupPage implements OnInit {

  formGroup: FormGroup;
  estados: EstadoDTO[];
  cidades: CidadeDTO[];
  data: Date;

  constructor(
    public formBuilder: FormBuilder,
    public estadoService: EstadoService,
    public cidadeService: CidadeService,
    public clienteService: ClienteService,
    private alertController: AlertController,
    public router: Router) {

    this.data = new Date();

    this.formGroup = this.formBuilder.group({
      nome: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
      email: ['', [Validators.required, Validators.email]],
      tipo: ['', [Validators.required]],
      cpfOuCnpj: ['', [Validators.required, Validators.minLength(11), Validators.maxLength(14)]],
      dataNascimento: ['', [Validators.required]],
      dataCadastro: [this.data.toLocaleDateString(), [Validators.required]],
      senha: ['', [Validators.required]],
      logradouro: ['', [Validators.required]],
      numero: ['', [Validators.required]],
      complemento: ['', []],
      bairro: ['', []],
      cep: ['', [Validators.required]],
      telefone1: ['', [Validators.required]],
      telefone2: ['', []],
      telefone3: ['', []],
      estadoId: [null, [Validators.required]],
      cidadeId: [null, [Validators.required]],
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

  ngOnInit() {
    this.estadoService.findEstado().subscribe(response => {
      this.estados = response;
      this.formGroup.controls['estadoId'].setValue(this.estados[0].id);
      this.updateCidades();
    }, error => {
      this.presentAlert('Erro', 'Oops... Ocorreu um erro', 'Erro: ' + error.message);
    })
  }

  updateCidades(){
    let estadoId = this.formGroup.value.estadoId;
    this.cidadeService.findCidade(estadoId).subscribe(response => {
      this.cidades = response;
      this.formGroup.controls['cidadeId'].setValue(null);
    }, error => {
      this.presentAlert('Erro', 'Oops... Ocorreu um erro', 'Erro: ' + error.message);
    });
  }

  cadastrar() {
    console.log(this.formGroup.value);
    this.clienteService.saveUser(this.formGroup.value).subscribe(response => {
      this.confirmacaoCadastro();
    }, error => {
      this.presentAlert('Erro', 'Oops... Ocorreu um erro', 'Erro: ' + error.message);
    });

  }

  confirmacaoCadastro() {
    this.presentAlert('Cadastro', 'cadastro', 'Cadastro realizado com sucesso');
    this.router.navigate(['/login']);
    console.log(this.formGroup.value);
  }
}
