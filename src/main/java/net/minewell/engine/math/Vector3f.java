package net.minewell.engine.math;

import org.joml.Vector2fc;
import org.joml.Vector2ic;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class Vector3f extends org.joml.Vector3f {

    /* Constants */

    public static final Vector3f ZERO = new Vector3f(0, 0, 0);

    public static final Vector3f LEFT = new Vector3f(-1, 0, 0);
    public static final Vector3f RIGHT = new Vector3f(1, 0, 0);

    public static final Vector3f DOWN = new Vector3f(0, -1, 0);
    public static final Vector3f UP = new Vector3f(0, 1, 0);

    public static final Vector3f BACKWARD = new Vector3f(0, 0, -1);
    public static final Vector3f FORWARD = new Vector3f(0, 0, 1);

    public static final Vector3f ONE = new Vector3f(1, 1, 1);

    /* Constructors */

    public Vector3f() {
        super();
    }

    public Vector3f(float d) {
        super(d);
    }

    public Vector3f(float x, float y, float z) {
        super(x, y, z);
    }

    public Vector3f(Vector3fc v) {
        super(v);
    }

    public Vector3f(Vector3ic v) {
        super(v);
    }

    public Vector3f(Vector2fc v, float z) {
        super(v, z);
    }

    public Vector3f(Vector2ic v, float z) {
        super(v, z);
    }

    public Vector3f(float[] xyz) {
        super(xyz);
    }

    public Vector3f(ByteBuffer buffer) {
        super(buffer);
    }

    public Vector3f(int index, ByteBuffer buffer) {
        super(index, buffer);
    }

    public Vector3f(FloatBuffer buffer) {
        super(buffer);
    }

    public Vector3f(int index, FloatBuffer buffer) {
        super(index, buffer);
    }
}
