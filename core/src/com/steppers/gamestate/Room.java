package com.steppers.gamestate;

import com.badlogic.gdx.graphics.Texture;

public class Room {

    private boolean visited;
    private boolean visible;
    protected int roomType;
    protected Texture symbol;

    public boolean isVisited(){
        return visited;
    }

    public boolean isVisible(){
        return visible;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }

    public int getRoomType(){
        return roomType;
    }

    public void setSymbol(Texture symbol){
        this.symbol = symbol;
    }

    public Texture getSymbol(){
        return symbol;
    }


}
