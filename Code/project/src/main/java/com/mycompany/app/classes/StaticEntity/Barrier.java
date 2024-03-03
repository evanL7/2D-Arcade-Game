package com.mycompany.app.classes.StaticEntity;

import java.awt.*;
import com.mycompany.app.classes.Helpers.Position;

public class Barrier extends StaticEntity
{
    protected Position position;
    
    public Barrier(Position position, Image sprite)
    {
        super(position, false, sprite);
    }
}