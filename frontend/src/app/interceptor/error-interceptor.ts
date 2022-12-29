import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpErrorResponse, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { StorageService } from '../service/storage.service';
import { LocalUser } from '../model/local_user';

@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private storageService: StorageService) {}

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

          default:
            this.handleDefaultError(errorMsg);
        }
        return throwError(errorMsg);
      })
    )
  }

  handle401() {
    alert('Login e senha incorretos');
  }

  handle403() {
    let user : LocalUser = {
      token: '',
      email: ''
    }
    this.storageService.setLocalUser(user);
  }

  handleDefaultError(errorMsg: any){
    alert('404: ' + errorMsg.status);
  }

}

export const ErrorInterceptorProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: ErrorInterceptor,
  multi: true
}
