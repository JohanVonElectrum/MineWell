package net.minewell.engine.core;

import net.minewell.engine.exceptions.InvalidTypeException;
import net.minewell.engine.exceptions.ShaderException;
import net.minewell.engine.utils.FileUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import static org.lwjgl.opengl.GL11C.GL_FALSE;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20C.*;

public class Shader {

    private final int id;

    public Shader(String path, int type) throws InvalidTypeException, ShaderException {
        if (type == GL20.GL_VERTEX_SHADER || type == GL20.GL_FRAGMENT_SHADER) {
            this.id = createShader(FileUtils.loadAsString(path), type);
        } else
            throw new InvalidTypeException();
    }

    protected int createShader(String source, int type) throws ShaderException {
        int id = glCreateShader(type);
        if (id == GL_FALSE)
            throw new ShaderException("Could not create shader!");

        glShaderSource(id, source);
        glCompileShader(id);
        if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE)
            throw new ShaderException(glGetShaderInfoLog(id));

        return id;
    }

    public void attach(int programId) {
        glAttachShader(programId, this.id);
    }

    public void detach(int programId) {
        glDetachShader(programId, this.id);
    }

    public void dispose() {
        if (this.id != GL11.GL_FALSE)
            glDeleteShader(this.id);
    }

    /* Getters & Setters */

    public int getId() {
        return this.id;
    }
}
