import { Component, OnInit } from '@angular/core';
import {GameService} from "../../core/services/game.service";
import {Game} from "../../core/models/game.model";

@Component({
  selector: 'app-score',
  templateUrl: './score.component.html',
  styleUrls: ['./score.component.css']
})
export class ScoreComponent implements OnInit {

  game: Game;

  constructor(private gameService: GameService) { }

  ngOnInit() {
    this.gameService.getGame().subscribe(value => this.game = value);
  }
  score(side) {
    this.gameService.score(side).subscribe(value => {
      console.log(value);
      return this.game = value;
    });
  }
  reset() {
    this.gameService.reset().subscribe(value => {
      console.log(value);
      return this.game = value;
    });
  }

}
