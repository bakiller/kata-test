import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse,} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';


@Injectable()
export class GameIdInterceptor implements HttpInterceptor {
  constructor() {
  }

  private addHeader(request: HttpRequest<any>, name: string): HttpRequest<any> {
    let updatedRequest = request;
    if (localStorage.getItem(name) && localStorage.getItem(name) !== 'null') {
      updatedRequest = request.clone({
        headers: request.headers.set(name, localStorage.getItem(name))
      });
      return updatedRequest;
    }
    return updatedRequest;
  }

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    return next.handle(this.addHeader(this.addHeader(request, 'set-id'), 'game-id'))
      .pipe(
        tap(
          event => {
            if (event instanceof HttpResponse) {
              if (event.body.uuid) {
                localStorage.setItem('game-id', event.body.uuid);
                localStorage.setItem('set-id', event.body.uuid);
              }
            }
          }
        )
      );
  }
}
