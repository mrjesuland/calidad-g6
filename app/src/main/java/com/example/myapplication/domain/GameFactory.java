package com.example.myapplication.domain;

public class GameFactory {

    private final Game game;

    public GameFactory() {
        this.game = new Game();
    }

    public Game getCurrentGame() {
        return game;
    }

    public Game resetGame() {
        game.resetGame();
        return game;
    }

}
