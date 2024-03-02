package com.mycompany.app.classes.StaticEntity;

import java.awt.*;
import java.util.Timer;

abstract class StaticEntity {

    protected int x;
    protected int y;
    protected boolean objectDespawns;
    protected Timer despawnTimer;
    public Image sprite;

    public void onCollide() {

    }

    public void destroy() {

    }

}
