import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse,} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";


@Injectable()
export class GameIdInterceptor implements HttpInterceptor {
  constructor() {
  }

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (localStorage.getItem("game-id") && localStorage.getItem("game-id") != 'null') {
      const updatedRequest = request.clone({
        headers: request.headers.set("game-id", localStorage.getItem("game-id"))
      });
      return next.handle(updatedRequest);
    }
    return next.handle(request).pipe(
      tap(
        event => {
          if (event instanceof HttpResponse) {
            localStorage.setItem("game-id", event.body.uuid);
          }
        }
      )
    );
  }
}
