package com.steppers.gamestate;

import java.util.ArrayList;

class Limb {
    protected boolean alive;
    protected int blood;
    ArrayList<Limb> children = new ArrayList<Limb>();

    public Limb(int blood){
        this.blood = blood;
    }

    public boolean isAlive(){
        return alive;
    }

    public int getBlood(){
        return blood;
    }

    public void addChildLimb(Limb child) {
        children.add(child);
    }

    public int getTotalBlood(){
        int total = 0;
        total += blood;
        for(Limb child : children){
            if(child.isAlive())
            total += child.getTotalBlood();
        }

        return total;
    }

}
