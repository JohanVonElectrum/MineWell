package net.minewell.engine.core;

import net.minewell.engine.exceptions.ShaderException;
import org.lwjgl.opengl.GL20;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {

    private int id;

    private Shader vertexShader;
    private Shader fragmentShader;

    public ShaderProgram(Shader vertexShader, Shader fragmentShader) throws ShaderException {
        this.vertexShader = vertexShader;
        this.fragmentShader = fragmentShader;

        this.id = createProgram();
    }

    public int createProgram() throws ShaderException {
        int id = glCreateProgram();
        if (id == GL_FALSE)
            throw new ShaderException("Could not create ShaderProgram!");

        this.vertexShader.attach(id);
        this.fragmentShader.attach(id);

        return id;
    }

    public void link() throws ShaderException {
        glLinkProgram(this.id);
        if (glGetProgrami(this.id, GL_LINK_STATUS) == GL_FALSE)
            throw new ShaderException(glGetProgramInfoLog(this.id));

        if (this.vertexShader.getId() != GL_FALSE)
            this.vertexShader.detach(this.id);
        if (this.fragmentShader.getId() != GL_FALSE)
            this.fragmentShader.detach(this.id);

        glValidateProgram(this.id);
        if (glGetProgrami(this.id, GL_VALIDATE_STATUS) == GL_FALSE)
            throw new ShaderException(glGetProgramInfoLog(this.id));
    }

    public void bind() {
        glUseProgram(this.id);
    }

    public void unbind() {
        glUseProgram(GL_FALSE);
    }

    public void dispose() {
        unbind();
        if (this.id != GL_FALSE)
            glDeleteProgram(this.id);
    }

}
