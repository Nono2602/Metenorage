package System;

/*
 * @author Matthieu Le Boucher <matt.leboucher@gmail.com>
 */

import Component.Component;
import Main.Entity;

import java.util.List;

public abstract class BaseSystem implements GameSystem {
    public void iterate(List<Entity> entities) {
        entities.forEach(entity -> getLocalSystemComponents(entity).forEach(this::applyComponent));
    }

    abstract List<Component> getLocalSystemComponents(Entity entity);

    abstract void applyComponent(Component component);
}