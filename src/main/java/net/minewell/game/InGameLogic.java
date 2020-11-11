package net.minewell.game;

import net.minewell.engine.components.Component;
import net.minewell.engine.components.GameObject;
import net.minewell.engine.components.MeshRenderer;
import net.minewell.engine.components.Transform;
import net.minewell.engine.core.IGameLogic;
import net.minewell.engine.core.Window;
import net.minewell.engine.exceptions.MeshException;
import net.minewell.engine.graphics.Mesh;
import net.minewell.engine.graphics.Renderer;
import net.minewell.engine.math.Vector3f;
import net.minewell.engine.utils.TimeUtils;
import net.minewell.game.blocks.Block;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

public class InGameLogic implements IGameLogic {

    Renderer renderer;
    Mesh mesh;

    private int direction = 0;
    private float color = 0.0f;

    public InGameLogic() {
        this.renderer = new Renderer();
    }

    GameObject go;

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);

        this.mesh = Block.createMesh((byte) 0x15);

        ArrayList<Component> gcs = new ArrayList<>();
        gcs.add(new MeshRenderer(this.mesh));
        this.go = new GameObject(gcs);

        ((Transform) go.getComponent(Transform.class)).translate((Vector3f) Vector3f.BACKWARD.mul(5));
    }

    @Override
    public void input(Window window) {
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            direction = 1;
        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            direction = -1;
        } else {
            direction = 0;
        }
    }

    @Override
    public void update(double deltaTime) {
        color += direction * 0.01f;
        if (color > 1) {
            color = 1.0f;
        } else if (color < 0) {
            color = 0.0f;
        }

        ((Transform) go.getComponent(Transform.class)).rotate(0.01f, Vector3f.UP);
        ((Transform) go.getComponent(Transform.class)).getPosition().y = (float) Math.sin(TimeUtils.getTime());
    }

    @Override
    public void render(Window window) {
        window.setClearColor(color, color, color, 0.0f);

        this.renderer.render(window, new GameObject[] {
                go
        });
    }

    @Override
    public void dispose() {
        this.renderer.dispose();
    }
}
