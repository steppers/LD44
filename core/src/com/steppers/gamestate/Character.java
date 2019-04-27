package com.steppers.gamestate;

import java.util.ArrayList;

public class Character extends Being {

    private ArrayList<Follower> followers = new ArrayList<Follower>();

    public Character(int lifeBlood) {
        super(lifeBlood);
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

}
