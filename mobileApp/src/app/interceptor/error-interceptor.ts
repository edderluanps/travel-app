import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpErrorResponse, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { StorageService } from '../service/storage.service';
import { LocalUser } from '../model/local_user';
import { FieldMessage } from '../model/fieldmessage';
import { AlertController } from '@ionic/angular';

@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptor implements HttpInterceptor {

  constructor(
    private storageService: StorageService,
    private alertController : AlertController) { }

  async presentAlert(header: string, subHeader: string, message: string) {
    const alert = await this.alertController.create({
      header: header,
      subHeader: subHeader,
      message: message,
      buttons: ['OK'],
    });

    await alert.present();
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMsg = 'Erro aqui' + error;
        if (error.error instanceof ErrorEvent) {
          console.log('this is client side error');
          errorMsg = `Error: ${error.error.message}`;
        }
        else {
          console.log('this is server side error');
          errorMsg = `Error Code: ${error.status},  Message: ${error.message}`;
        }
        console.log(errorMsg);

        switch (error.status) {
          case 401:
            this.handle401();
            break;

          case 403:
            this.handle403();
            break;

          case 404:
            this.handle404(errorMsg);
            break;

          default:
            this.handleDefaultError(errorMsg);
        }
        return throwError(errorMsg);
      })
    )
  }

  handle401() {
    this.presentAlert('Erro', 'Oops. Ocorreu um erro', 'Login ou senha inválidos');
  }

  handle403() {
    let user: LocalUser = {
      token: '',
      email: ''
    }
    this.storageService.setLocalUser(user);
    this.presentAlert('Erro', 'Oops. Ocorreu um erro', 'Não permitido.');
  }

  handle404(errorMsg: any) {
    let alertMsg = this.listErrors(errorMsg.errors);
    this.presentAlert('Erro', 'Oops. Ocorreu um erro', 'Não permitido. cód:' + alertMsg);
  }

  handleDefaultError(errorMsg: any) {
    alert('Erro: ' + errorMsg.status + errorMsg.log);
  }

  private listErrors(messages: FieldMessage[]): string {
    let s: string = '';
    for (var i = 0; i < messages.length; i++) {
      s = s + '<p><strong>' + messages[i].fieldName + "</strong>: " + messages[i].message + '</p>';
    }
    return s;
  }

}

export const ErrorInterceptorProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: ErrorInterceptor,
  multi: true
}
