package net.minewell.engine.core;

public class GameEngine implements Runnable {

    public static final int TARGET_FPS = 75;
    public static final int TARGET_UPS = 30;

    private final Window window;
    //private final Timer timer;
    private final IGameLogic gameLogic;

    private final Thread gameLoopThread;

    public GameEngine(String windowTitle, int width, int height, boolean vSync, IGameLogic gameLogic) {
        this.gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
        this.window = new Window(windowTitle, width, height, vSync);
        this.gameLogic = gameLogic;
        //this.timer = new Timer();
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    protected void init() throws Exception {
        window.init();
        //timer.init()
        gameLogic.init();
    }

    protected void gameLoop() {
        boolean running = true;
        while (running && !window.windowShouldClose()) {

        }
    }

}
