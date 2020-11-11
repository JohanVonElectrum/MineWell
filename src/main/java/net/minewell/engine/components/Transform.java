package net.minewell.engine.components;

import net.minewell.engine.math.Matrix4f;
import net.minewell.engine.math.Quaternionf;
import net.minewell.engine.math.Vector3f;

public class Transform extends Component {

    private Vector3f position;
    private Quaternionf rotation;
    private Vector3f scale;

    public Transform() {
        this(Vector3f.ZERO, Quaternionf.IDENTITY, Vector3f.ONE);
    }

    public Transform(Vector3f position, Quaternionf rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Transform translate(Vector3f dPos) {
        this.position.add(dPos);
        return this;
    }

    public Transform rotate(float angle, Vector3f axis) {
        this.rotation.rotateAxis(angle, axis);
        return this;
    }

    public Matrix4f getWorldMatrix() {
        return (Matrix4f) new Matrix4f().translate(this.position).rotate(rotation).scale(scale);
    }

    /*Getters & Setters*/

    public Vector3f getPosition() {
        return position;
    }

    public Transform setPosition(Vector3f position) {
        this.position = position;
        return this;
    }

    public Quaternionf getRotation() {
        return rotation;
    }

    public Transform setRotation(Quaternionf rotation) {
        this.rotation = rotation;
        return this;
    }

    public Vector3f getScale() {
        return scale;
    }

    public Transform setScale(Vector3f scale) {
        this.scale = scale;
        return this;
    }
}
