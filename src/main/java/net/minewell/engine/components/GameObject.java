package net.minewell.engine.components;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    private final List<Component> components;

    public GameObject() {
        this(new ArrayList<>());
    }

    public GameObject(List<Component> components) {
        this.components = components == null ? new ArrayList<>() : components;

        this.components.add(new Transform());
    }

    public void addComponent(Component component) {
        this.components.add(component);
    }

    public <T extends Component> Component getComponent(Class<T> type) {
        for (Component component: this.components) {
            if (type.isInstance(component))
                return component;
        }
        return null;
    }
}
