import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Game} from "../models/game.model";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private httpClient: HttpClient) {
  }


  public getGame(): Observable<Game> {
    return this.httpClient.get<Game>(`${environment.baseUrl}game/`);
  }
  public score(side): Observable<Game> {
    return this.httpClient.post<Game>(`${environment.baseUrl}game/${side}`, null);
  }

}
