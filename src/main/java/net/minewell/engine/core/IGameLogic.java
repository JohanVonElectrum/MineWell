package net.minewell.engine.core;

public interface IGameLogic {

    void init() throws Exception;

    void input(Window window);

    void update(double deltaTime);

    void render(Window window);

    void dispose();

}
