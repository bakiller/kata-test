import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Set} from '../models/game.model';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SetService {
  constructor(private httpClient: HttpClient) {
  }


  public getSet(): Observable<Set> {
    return this.httpClient.get<Set>(`${environment.baseUrl}set/`);
  }

  public score(side): Observable<Set> {
    return this.httpClient.post<Set>(`${environment.baseUrl}set/${side}`, null);
  }

  public reset(): Observable<Set> {
    return this.httpClient.post<Set>(`${environment.baseUrl}set/reset`, null);
  }
}
