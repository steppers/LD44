package com.steppers.gamestate;

public class Room {

    private boolean visited;
    private boolean visible;
    protected int roomType;

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


}
