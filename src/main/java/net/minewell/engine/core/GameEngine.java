package net.minewell.engine.core;

import net.minewell.engine.utils.TimeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameEngine implements Runnable {

    public static final Logger LOGGER = LogManager.getLogger("MineWell");

    private final Window window;
    private final IGameLogic gameLogic;

    private final Thread gameLoopThread;

    public GameEngine(String windowTitle, int width, int height, boolean vSync, IGameLogic gameLogic) {
        this.gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
        this.window = new Window(windowTitle, width, height, vSync);
        this.gameLogic = gameLogic;
    }

    public void start() {
        this.gameLoopThread.start();
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception exception) {
            exception.printStackTrace();
            LOGGER.fatal(exception.getMessage());
        } finally {
            dispose();
        }
    }

    protected void init() throws Exception {
        this.window.init();
        this.gameLogic.init();
    }

    protected void input() {
        this.gameLogic.input(this.window);
    }

    protected void update(double deltaTime) {
        this.gameLogic.update(deltaTime);
        this.window.update();
    }

    protected void render() {
        this.gameLogic.render(this.window);
    }

    protected void gameLoop() {
        boolean running = true;
        double prevTime = TimeUtils.getTime();
        while (running && !this.window.windowShouldClose()) {
            input();
            update(TimeUtils.getTime() - prevTime);
            render();
            prevTime = TimeUtils.getTime();
        }
    }

    protected void dispose() {
        this.gameLogic.dispose();
    }

}
