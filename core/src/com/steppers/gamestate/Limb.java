package com.steppers.gamestate;

import java.util.ArrayList;

class Limb {

    private String name;
    protected boolean alive;
    protected int blood;
    private ArrayList<Limb> children = new ArrayList<Limb>();

    public Limb(String name, int blood){
        this.name = name;
        this.blood = blood;
    }

    public boolean isAlive(){
        return alive;
    }

    public void setAlive(boolean alive){
        this.alive = alive;
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

    public boolean removeChildLimb(Limb limb){
        boolean success = false;

        for(Limb child : children){
            if(child == limb){
                children.remove(limb);
                success = true;
            }
            if(child.removeChildLimb(limb)){
                success = true;
            }
        }
        return success;
    }

}
