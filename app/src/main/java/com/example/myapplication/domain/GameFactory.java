package com.example.myapplication.domain;

public class GameFactory {

    private static final Game game = new Game();

    public static Game currentGame() {
        return game;
    }

    public static Game resetGame() {
        game.resetGame();
        return game;
    }

}
