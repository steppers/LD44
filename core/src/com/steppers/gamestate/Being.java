package com.steppers.gamestate;

import java.util.ArrayList;

public class Being {

    private int lifeBlood;
    private ArrayList<Limb> limbs = new ArrayList<Limb>();

    public Being(int lifeBlood){
        this.lifeBlood = lifeBlood;
    }

    public int getLifeBlood(){
        return lifeBlood;
    }

    public void setLifeBlood(int lifeBlood){
        this.lifeBlood = lifeBlood;
    }

    public boolean addLimb(Limb limb){
        return limbs.add(limb);
    }

    public boolean removeLimb(Limb limb){
        for(Limb part: limbs){
            part.removeChildLimb(limb);
        }
        return limbs.remove(limb);
    }


}
