package net.minewell.engine.graphics;

import net.minewell.engine.components.GameObject;
import net.minewell.engine.components.MeshRenderer;
import net.minewell.engine.components.Transform;
import net.minewell.engine.core.Shader;
import net.minewell.engine.core.ShaderProgram;
import net.minewell.engine.core.Window;
import net.minewell.engine.exceptions.InvalidTypeException;
import net.minewell.engine.exceptions.ShaderException;
import net.minewell.engine.math.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Renderer {

    private static final float FOV = (float) Math.toRadians(60.0f);
    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000.f;
    private Matrix4f projectionMatrix;


    private ShaderProgram shaderProgram;

    public void init(Window window) throws ShaderException, InvalidTypeException {
        Shader vertexShader = new Shader("/assets/shaders/vertex.glsl", GL_VERTEX_SHADER);
        Shader fragmentShader = new Shader("/assets/shaders/fragment.glsl", GL_FRAGMENT_SHADER);
        this.shaderProgram = new ShaderProgram(vertexShader, fragmentShader);
        this.shaderProgram.link();

        setProjectionMatrix((float) window.getWidth() / window.getHeight());

        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("worldMatrix");
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Window window, GameObject[] gameObjects) {
        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            setProjectionMatrix((float) window.getWidth() / window.getHeight());
            window.setResized(false);
        }

        this.shaderProgram.bind();
        this.shaderProgram.setUniform("projectionMatrix", this.projectionMatrix);

        for(GameObject gameObject : gameObjects) {
            // Set world matrix for this item
            Matrix4f worldMatrix = ((Transform) gameObject.getComponent(Transform.class)).getWorldMatrix();
            shaderProgram.setUniform("worldMatrix", worldMatrix);
            // Render the mes for this game item
            ((MeshRenderer) gameObject.getComponent(MeshRenderer.class)).render();
        }

        this.shaderProgram.unbind();
    }

    public void dispose() {
        if (this.shaderProgram != null)
            this.shaderProgram.dispose();
    }

    protected void setProjectionMatrix(float aspectRatio) {
        this.projectionMatrix = (Matrix4f) new Matrix4f().setPerspective(Renderer.FOV, aspectRatio, Renderer.Z_NEAR, Renderer.Z_FAR);
    }

}
