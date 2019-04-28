package com.steppers.gamestate;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Character extends Being {

    private ArrayList<Follower> followers = new ArrayList<Follower>();

    private int currentRoom;

    public Character(int lifeBlood) {
        super(lifeBlood);
        setIcon(new Texture("PixelBloodMage.png"));
        setName("The \"Farmer\"");
    }

    public boolean addFollower(Follower follower){
        return followers.add(follower);
    }

    public boolean removeFollower(Follower follower){
        return followers.remove(follower);
    }

    public ArrayList<Follower> getFollowers(){
        return followers;
    }

    public void moveToRoomNumber(int roomNumber){
        this.currentRoom = roomNumber;
    }

    public int getRoomNumber(){
        return currentRoom;
    }

}
