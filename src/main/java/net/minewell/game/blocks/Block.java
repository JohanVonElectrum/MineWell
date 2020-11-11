package net.minewell.game.blocks;

import de.matthiasmann.twl.utils.PNGDecoder;
import net.minewell.engine.core.GameEngine;
import net.minewell.engine.exceptions.MeshException;
import net.minewell.engine.graphics.Mesh;
import org.lwjgl.openvr.Texture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Block {

    private static final float[] POSITIONS = new float[] {
            // Face X
            -0.5f, -0.5f, -0.5f,    // 0: 0 0 0
            0.5f, -0.5f, -0.5f,     // 1: 1 0 0
            0.5f, 0.5f, -0.5f,      // 2: 1 1 0
            -0.5f, 0.5f, -0.5f,     // 3: 0 1 0
            // Face -X
            -0.5f, -0.5f, 0.5f,     // 4: 0 0 1
            0.5f, -0.5f, 0.5f,      // 5: 1 0 1
            0.5f, 0.5f, 0.5f,       // 6: 1 1 1
            -0.5f, 0.5f, 0.5f,      // 7: 0 1 1
    };
    private static final int[] INDICES = new int[] {
            0, 3, 7, 7, 4, 0,       // x-
            1, 2, 6, 6, 5, 1,       // x+
            0, 1, 5, 5, 4, 0,       // y-
            3, 2, 6, 6, 7, 3,       // y+
            0, 1, 2, 2, 3, 0,       // z-
            4, 5, 6, 6, 7, 4        // z+
    };
    private static final float[] COLORS = new float[] {
            0.5f, 0.0f, 0.0f,
            0.0f, 0.5f, 0.0f,
            0.0f, 0.0f, 0.5f,
            0.0f, 0.5f, 0.5f,

            0.5f, 0.0f, 0.0f,
            0.0f, 0.5f, 0.0f,
            0.0f, 0.0f, 0.5f,
            0.0f, 0.5f, 0.5f
    };

    protected static final byte FACE_LEFT =     0x01; // 00000001
    protected static final byte FACE_RIGHT =    0x02; // 00000010
    protected static final byte FACE_BOTTOM =   0x04; // 00000100
    protected static final byte FACE_TOP =      0x08; // 00001000
    protected static final byte FACE_FORWARD =  0x10; // 00010000
    protected static final byte FACE_BACKWARD = 0x20; // 00100000

    protected static final byte[] FACES = new byte[] {
            FACE_LEFT,
            FACE_RIGHT,
            FACE_BOTTOM,
            FACE_TOP,
            FACE_FORWARD,
            FACE_BACKWARD
    };

    public static Mesh createMesh(byte faces) throws MeshException {
        byte fs = faces;
        int faceCount = 0;
        for (int i = 0; i < 6; i++) {
            if (fs % 2 == 1)
                faceCount++;
            fs = (byte) (fs >> 1);
        }

        float[] positions = new float[faceCount * 6 * 3];
        float[] colors = new float[faceCount * 6 * 3];

        for (int c = 0, i = 0; i < FACES.length; i++) {
            if ((faces & FACES[i]) != 0) {
                for (int j = 6 * i; j < 6 * (i + 1); j++) {
                    for (int k = 3 * INDICES[j]; k < 3 * (INDICES[j] + 1); k++) {
                        positions[c] = POSITIONS[k];
                        colors[c] = COLORS[k];
                    }
                }
            }
        }

        return new Mesh(positions, colors);
    }

}
