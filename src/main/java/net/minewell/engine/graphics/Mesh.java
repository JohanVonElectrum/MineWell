package net.minewell.engine.graphics;

import net.minewell.engine.exceptions.MeshException;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {

    private final int vaoId;
    private final int vboId;
//    private final int tboId;
    private final int cboId;

    private final int vertexCount;
//    private final Texture texture;

    public Mesh(float[] positions, float[] colors) throws MeshException {
        if (positions.length % 3 != 0)
            throw new MeshException("Positions length must be a multiple of 3");
//        if (uv.length % 2 != 0)
//            throw new MeshException("uv length must be a multiple of 2");

        this.vertexCount = positions.length / 3;
//        this.texture = texture;

        FloatBuffer vbo = null;
//        FloatBuffer tbo = null;
        FloatBuffer cbo = null;
        try {
            this.vaoId = glGenVertexArrays();
            glBindVertexArray(this.vaoId);

            // Position VBO
            this.vboId = glGenBuffers();
            vbo = MemoryUtil.memAllocFloat(positions.length);
            vbo.put(positions).flip();
            glBindBuffer(GL_ARRAY_BUFFER, this.vboId);
            glBufferData(GL_ARRAY_BUFFER, vbo, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            // Texture Buffer Object
//            this.tboId = glGenBuffers();
//            tbo = MemoryUtil.memAllocFloat(uv.length);
//            tbo.put(uv).flip();
//            glBindBuffer(GL_ARRAY_BUFFER, this.tboId);
//            glBufferData(GL_ARRAY_BUFFER, tbo, GL_STATIC_DRAW);
//            glEnableVertexAttribArray(1);
//            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

            // Color Buffer Object
            this.cboId = glGenBuffers();
            cbo = MemoryUtil.memAllocFloat(colors.length);
            cbo.put(colors).flip();
            glBindBuffer(GL_ARRAY_BUFFER, this.cboId);
            glBufferData(GL_ARRAY_BUFFER, cbo, GL_STATIC_DRAW);
            MemoryUtil.memFree(cbo);
            glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        } finally {
            if (vbo != null)
                MemoryUtil.memFree(vbo);
//            if (tbo != null)
//                MemoryUtil.memFree(tbo);
            if (cbo != null)
                MemoryUtil.memFree(cbo);
        }
    }

    public void render() {
//        glActiveTexture(GL_TEXTURE0);
//        glBindTexture(GL_TEXTURE_2D, this.texture.getId());

        glBindVertexArray(this.vaoId);

        glDrawArrays(GL_TRIANGLES, 0, 3);

        glBindVertexArray(GL_FALSE);
    }

    public void dispose() {
        glDisableVertexAttribArray(GL_FALSE);

        glBindBuffer(GL_ARRAY_BUFFER, GL_FALSE);
        glDeleteBuffers(this.vboId);
        glDeleteBuffers(this.cboId);

        glBindVertexArray(GL_FALSE);
        glDeleteVertexArrays(this.vaoId);
    }

    /* Getters & Setters*/

    public int getVaoId() {
        return this.vaoId;
    }

    public int getVertexCount() {
        return this.vertexCount;
    }
}
