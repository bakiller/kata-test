export class Game {
  firstPlayerScore: Score;
  secondPlayerScore: Score;
  winner: 'FIRST_PLAYER' | 'SECOND_PLAYER';
}
export class Score {
  winningScore: boolean;
  designation: string;
}
