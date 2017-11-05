package Component;

import Main.Entity;

/*
 * @author Matthieu Le Boucher <matt.leboucher@gmail.com>
 */

public interface Component {
    void setEntity(Entity entity);

    Entity getEntity();
}
