package net.minewell.game;

import net.minewell.engine.core.IGameLogic;
import net.minewell.engine.core.Window;
import net.minewell.engine.graphics.Renderer;

public class InGameLogic implements IGameLogic {

    Renderer renderer;

    public InGameLogic() {
        this.renderer = new Renderer();
    }

    @Override
    public void init() throws Exception {
        renderer.init();
    }

    @Override
    public void input(Window window) {

    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(Window window) {
        this.renderer.render(window);
    }

    @Override
    public void dispose() {
        this.renderer.dispose();
    }
}
