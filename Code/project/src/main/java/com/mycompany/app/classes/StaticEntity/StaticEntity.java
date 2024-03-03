package com.mycompany.app.classes.StaticEntity;

import java.awt.*;


//import java.util.Timer;

abstract class StaticEntity {

    protected int x;
    protected int y;
    protected boolean objectDespawns;
    //protected Timer despawnTimer;
    protected int despawnTimer;
    public Image sprite;

    // Constructor

    public StaticEntity(int x, int y, boolean objectDespawns, int despawnTimer, Image sprite)
    {
        this.x = x;
        this.y = y;
        this.objectDespawns = objectDespawns;
        this.despawnTimer = despawnTimer;
        this.sprite = sprite;
    }


    public void onCollide() {

    }

    public void destroy() {

    }

}
