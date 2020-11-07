package net.minewell.engine.graphics;

import net.minewell.engine.core.Shader;
import net.minewell.engine.core.ShaderProgram;
import net.minewell.engine.core.Window;
import net.minewell.engine.exceptions.InvalidTypeException;
import net.minewell.engine.exceptions.ShaderException;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    private ShaderProgram shaderProgram;

    private int vboId;
    private int vaoId;

    public void init() throws ShaderException, InvalidTypeException {
        Shader vertexShader = new Shader("/assets/shaders/vertex.glsl", GL_VERTEX_SHADER);
        Shader fragmentShader = new Shader("/assets/shaders/fragment.glsl", GL_FRAGMENT_SHADER);
        this.shaderProgram = new ShaderProgram(vertexShader, fragmentShader);
        this.shaderProgram.link();

        float[] vertices = new float[]{
                0.0f,  0.5f, 0.0f,  //center-top
                -0.5f, -0.5f, 0.0f, //left-bottom
                0.5f, -0.5f, 0.0f   //right-bottom
        };

        FloatBuffer vbo = null;
        try {
            vbo = MemoryUtil.memAllocFloat(vertices.length);
            vbo.put(vertices).flip();

            this.vaoId = glGenVertexArrays();
            glBindVertexArray(this.vaoId);

            this.vboId = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, this.vboId);
            glBufferData(GL_ARRAY_BUFFER, vbo, GL_STATIC_DRAW);

            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            glBindBuffer(GL_ARRAY_BUFFER, GL_FALSE);
            glBindVertexArray(GL_FALSE);
        } finally {
            if (vbo != null)
                MemoryUtil.memFree(vbo);
        }
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Window window) {
        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        this.shaderProgram.bind();

        glBindVertexArray(this.vaoId);
        glDrawArrays(GL_TRIANGLES, 0, 3);

        glBindVertexArray(GL_FALSE);
        this.shaderProgram.unbind();
    }

    public void dispose() {
        if (this.shaderProgram != null)
            this.shaderProgram.dispose();

        glDisableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, GL_FALSE);
        glDeleteBuffers(this.vboId);

        glBindVertexArray(GL_FALSE);
        glDeleteVertexArrays(this.vaoId);
    }

}
