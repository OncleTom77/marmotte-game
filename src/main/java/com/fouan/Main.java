package com.fouan;

import com.fouan.game.Game;
import com.fouan.player.Player;
import com.fouan.player.strategy.BotStrategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Player toto = new Player("Toto", new BotStrategy());
        Player lili = new Player("Lili", new BotStrategy());

        Game game = new Game(Arrays.asList(toto, lili));
        game.run();
    }
}
