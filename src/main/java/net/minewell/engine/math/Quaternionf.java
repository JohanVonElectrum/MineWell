package net.minewell.engine.math;

import org.joml.AxisAngle4d;
import org.joml.AxisAngle4f;
import org.joml.Quaterniondc;
import org.joml.Quaternionfc;

public class Quaternionf extends org.joml.Quaternionf {

    /* Constants */

    public static final Quaternionf IDENTITY = new Quaternionf(0, 0, 0, 1);

    public static final Quaternionf ZERO = new Quaternionf(0, 0, 0, 0);

    public static final Quaternionf X = new Quaternionf(1, 0, 0, 0);
    public static final Quaternionf Y = new Quaternionf(0, 1, 0, 0);
    public static final Quaternionf Z = new Quaternionf(0, 0, 1, 0);
    public static final Quaternionf W = new Quaternionf(0, 0, 0, 1);

    public static final Quaternionf ONE = new Quaternionf(1, 1, 1, 1);

    /* Constructors */

    public Quaternionf() {
        super();
    }

    public Quaternionf(double x, double y, double z, double w) {
        super(x, y, z, w);
    }

    public Quaternionf(float x, float y, float z, float w) {
        super(x, y, z, w);
    }

    public Quaternionf(Quaternionfc source) {
        super(source);
    }

    public Quaternionf(Quaterniondc source) {
        super(source);
    }

    public Quaternionf(AxisAngle4f axisAngle) {
        super(axisAngle);
    }

    public Quaternionf(AxisAngle4d axisAngle) {
        super(axisAngle);
    }
}
