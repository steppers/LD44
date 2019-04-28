package com.steppers.gamestate;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;


public class Being {

    private int lifeBlood;
    private ArrayList<Limb> limbs = new ArrayList<Limb>();
    private Texture icon;

    String name;

    public Being(int lifeBlood){
        this.lifeBlood = lifeBlood;
    }

    public int getLifeBlood(){
        return lifeBlood;
    }

    public void setLifeBlood(int lifeBlood){
        this.lifeBlood = lifeBlood;
    }

    public void inflictDamage(int damage){
        this.lifeBlood -= damage;
    }

    public void heal(int heal){
        this.lifeBlood += heal;
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

    public void setIcon(Texture icon){
        this.icon = icon;
    }

    public Texture getIcon(){
        return icon;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }


}
