package net.minewell.engine.math;

import org.joml.*;

import java.nio.FloatBuffer;

public class Matrix4f extends org.joml.Matrix4f {

    /* Constants */

    public static final Matrix4f IDENTITY = new Matrix4f();

    /* Constructors */

    public Matrix4f() {
        super();
    }

    public Matrix4f(Matrix3fc mat) {
        super(mat);
    }

    public Matrix4f(Matrix4fc mat) {
        super(mat);
    }

    public Matrix4f(Matrix4x3fc mat) {
        super(mat);
    }

    public Matrix4f(Matrix4dc mat) {
        super(mat);
    }

    public Matrix4f(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33) {
        super(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
    }

    public Matrix4f(FloatBuffer buffer) {
        super(buffer);
    }

    public Matrix4f(Vector4fc col0, Vector4fc col1, Vector4fc col2, Vector4fc col3) {
        super(col0, col1, col2, col3);
    }
}
