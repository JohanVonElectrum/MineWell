package net.minewell;

import net.minewell.engine.core.GameEngine;
import net.minewell.game.InGameLogic;

public class Start {

    public static void main(String[] args) {
        try {
            InGameLogic gameLogic = new InGameLogic();
            GameEngine gameEngine = new GameEngine("MineWell", 854, 600, true, gameLogic);
            gameEngine.start();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(-1);
        }
    }

}
