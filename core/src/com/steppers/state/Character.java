package com.steppers.state;

import java.util.ArrayList;

public class Character extends Being {

    private ArrayList<Being> followers = new ArrayList<>();

    private int currentRoom;

    public Character() {
        super(100, Type.BLOOD_MAGE);

        addFollower(Being.GenerateRandomBeing(false, 2));
        addFollower(Being.GenerateRandomBeing(false, 3));
        addFollower(Being.GenerateRandomBeing(false, 2));
    }

    public boolean addFollower(Being follower){
        return followers.add(follower);
    }

    public boolean removeFollower(Being follower){
        return followers.remove(follower);
    }

    public ArrayList<Being> getFollowers(){
        return followers;
    }

    public void moveToRoomNumber(int roomNumber){
        this.currentRoom = roomNumber;
    }

    public int getRoomNumber(){
        return currentRoom;
    }
}
