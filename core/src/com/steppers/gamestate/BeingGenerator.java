package com.steppers.gamestate;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class BeingGenerator {

    public static Follower generateFollower(){
        Random random = new Random();
        int lifeblood = random.nextInt(8) + 1;

        Follower follower = new Follower(lifeblood);

        String[] names = {"Terry", "Jerry", "Jim", "Charles", "Oliver", "Thomas", "Ruth", "Gabriel", "Albert", "David", "Niamh", "Melissa", "Reuben", "Edward", "Omar", "Lola", "Hayley", "Rosa", "Keith", "Rosie", "Ashanti", "Felicia", "Lauren", "Kenneth", "Toby", "Manuel", "Jonathan", "Nellie"};

        String modifier = "";
        String animal = "";

        if(lifeblood >= 7){
            if(lifeblood == 8){
                switch(random.nextInt(4)){
                    case 0: modifier = "Brawny";
                        break;
                    case 1: modifier = "Stubborn";
                        break;
                    case 2: modifier = "Strapping";
                        break;
                    case 3: modifier = "Fierce";
                        break;
                }
            }
            switch(random.nextInt(2)){
                case 0: follower.setIcon(new Texture("PixelHorse.png"));
                        animal = "Horse";
                        break;
                case 1: follower.setIcon(new Texture("PixelCow.png"));
                        animal = "Cow";
                        break;
            }
        } else if (lifeblood >= 4){

            if(lifeblood >= 5){
                switch(random.nextInt(4)){
                    case 0: modifier = "Derpy";
                        break;
                    case 1: modifier = "Dumb";
                        break;
                    case 2: modifier = "Distinctly Average";
                        break;
                    case 3: modifier = "Paranoid";
                        break;
                }
            }
            if(lifeblood == 4){
                switch(random.nextInt(4)){
                    case 0: modifier = "Acrobatic";
                        break;
                    case 1: modifier = "Worried";
                        break;
                    case 2: modifier = "Obsessive";
                        break;
                    case 3: modifier = "Socially Awkward";
                        break;
                }
            }
            switch(random.nextInt(4)){
                case 0: follower.setIcon(new Texture("PixelHorse.png"));
                        animal = "Horse";
                        break;
                case 1: follower.setIcon(new Texture("PixelCow.png"));
                        animal = "Cow";
                        break;
                case 2: follower.setIcon(new Texture("PixelSheep.png"));
                        animal = "Sheep";
                        break;
                case 3: follower.setIcon(new Texture("PixelSheep.png"));
                        animal = "Lamb";
                        break;
            }

        } else {
            if(lifeblood == 1){
                switch(random.nextInt(4)){
                    case 0: modifier = "Pathetic";
                        break;
                    case 1: modifier = "Tiny";
                        break;
                    case 2: modifier = "Crazed";
                        break;
                    case 3: modifier = "Superstitious";
                        break;
                }
            }
            if(lifeblood >= 2){
                switch(random.nextInt(4)){
                    case 0: modifier = "Two-faced";
                        break;
                    case 1: modifier = "Disgusting";
                        break;
                    case 2: modifier = "Image-Obsessed";
                        break;
                    case 3: modifier = "Pessimistic";
                        break;
                }
            }

            switch(random.nextInt(4)){
                case 0: follower.setIcon(new Texture("PixelSheep.png"));
                    animal = "Sheep";
                    break;
                case 1: follower.setIcon(new Texture("PixelSheep.png"));
                    animal = "Lamb";
                    break;
                case 2: follower.setIcon(new Texture("PixelOwl.png"));
                    animal = "Owl";
                    break;
                case 3: follower.setIcon(new Texture("PixelChicken.png"));
                    animal = "Chicken";
                    break;
        }}

        String name = names[random.nextInt(names.length)];

        follower.setName(name + " The " + modifier + " " + animal);

        return follower;
    }


}
