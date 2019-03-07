package kata.spring.test.tennis.controllers;

import kata.spring.test.tennis.models.Game;
import kata.spring.test.tennis.service.GameService;
import org.springframework.web.bind.annotation.*;

/**
 * The Game controller.
 */
@RestController
@RequestMapping("game")
public class GameController {
    private final GameService gameService;

    /**
     * Instantiates a new Game controller.
     *
     * @param gameService the game service
     */
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Gets the current game, epends on the session.
     *
     * @return the game of the current session
     */
    @GetMapping
    public Game getGame() {
        return this.gameService.getGame();
    }

    /**
     * adds a point to the player of the selected side
     *
     * @return the game of the current session
     */
    @PostMapping("{side}")
    public Game addPoint(@PathVariable("side") Game.GameSide side) {
        return this.gameService.addPoint(side);
    }
}