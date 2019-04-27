package com.steppers.gamestate;

import java.util.ArrayList;

public class Being {

    private int lifeBlood;
    private ArrayList<Limb> limbs = new ArrayList<Limb>();

    public Being(int lifeBlood){
        this.lifeBlood = lifeBlood;
    }

}
