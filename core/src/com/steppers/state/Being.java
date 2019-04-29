package com.steppers.state;

import com.badlogic.gdx.graphics.Texture;
import com.steppers.ld44.Renderer;

import java.util.Random;

public class Being {

    public static Random rand = new Random();

    public enum Type {
        UNKNOWN("being_unknown", "Unknown"),
        BLOOD_MAGE("being_blood_mage", "Blood Mage"),
        MAGE("being_mage", "Mage"),
        KRAKEN("being_kraken", "Kraken"),
        SKELETON("being_skeleton", "Skeleton"),
        HORSE("being_horse", "Horse"),
        COW("being_cow", "Cow"),
        SHEEP("being_sheep", "Sheep"),
        LAMB("being_sheep", "Lamb"),
        OWL("being_owl", "Owl"),
        CHICKEN("being_chicken", "Chicken");


        private String name;
        private Texture icon;
        Type(String textureId, String name) {
            this.name = name;
            icon = Renderer.Get().GetTexture(textureId);
        }

        public Texture getIcon() {
            return icon;
        }

        public String getName() {
            return name;
        }
    }

    static String[] names = {
            "Terry",
            "Jerry",
            "Jim",
            "Charles",
            "Oliver",
            "Thomas",
            "Ruth",
            "Gabriel",
            "Albert",
            "David",
            "Niamh",
            "Melissa",
            "Reuben",
            "Edward",
            "Omar",
            "Lola",
            "Hayley",
            "Rosa",
            "Keith",
            "Rosie",
            "Ashanti",
            "Felicia",
            "Lauren",
            "Kenneth",
            "Toby",
            "Manuel",
            "Jonathan",
            "Nellie",
            "Sally",
            "Harry",
            "Phil"
    };

    private int lifeblood;
    private Type type;
    private String name;

    public Being(int lifeblood, Type type) {
        this.lifeblood = lifeblood;
        this.type = type;
        name = "[Unnamed]";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name + " " + type.getName();
    }

    public void inflictDamage(int damage) {
        lifeblood -= damage;
    }

    public void heal(int lifeblood) {
        this.lifeblood += lifeblood;
    }

    public int getLifeblood() {
        return lifeblood;
    }

    public Type getType() {
        return type;
    }

    // Generator
    public static Being GenerateRandomBeing(boolean enemy) {
        return GenerateRandomBeing(enemy, 0);
    }

    public static Being GenerateRandomBeing(boolean enemy, int lifeblood) {
        rand.setSeed(System.currentTimeMillis());

        String name = names[rand.nextInt(names.length)];
        String modifier = "The";
        Type type = Type.UNKNOWN;

        if(enemy == false) {
            lifeblood = (lifeblood == 0) ? (rand.nextInt(8) + 1) : lifeblood;

            if (lifeblood >= 7) {
                if (lifeblood == 8) {
                    switch (rand.nextInt(4)) {
                        case 0:
                            modifier = "Brawny";
                            break;
                        case 1:
                            modifier = "Stubborn";
                            break;
                        case 2:
                            modifier = "Strapping";
                            break;
                        case 3:
                            modifier = "Fierce";
                            break;
                    }
                }
                switch (rand.nextInt(2)) {
                    case 0:
                        type = Type.HORSE;
                        break;
                    case 1:
                        type = Type.COW;
                        break;
                }
            } else if (lifeblood >= 4) {

                if (lifeblood >= 5) {
                    switch (rand.nextInt(4)) {
                        case 0:
                            modifier = "Derpy";
                            break;
                        case 1:
                            modifier = "Dumb";
                            break;
                        case 2:
                            modifier = "Distinctly Average";
                            break;
                        case 3:
                            modifier = "Paranoid";
                            break;
                    }
                }
                if (lifeblood == 4) {
                    switch (rand.nextInt(4)) {
                        case 0:
                            modifier = "Acrobatic";
                            break;
                        case 1:
                            modifier = "Worried";
                            break;
                        case 2:
                            modifier = "Obsessive";
                            break;
                        case 3:
                            modifier = "Socially Awkward";
                            break;
                    }
                }
                switch (rand.nextInt(4)) {
                    case 0:
                        type = Type.HORSE;
                        break;
                    case 1:
                        type = Type.COW;
                        break;
                    case 2:
                        type = Type.SHEEP;
                        break;
                    case 3:
                        type = Type.LAMB;
                        break;
                }

            } else {
                if (lifeblood == 1) {
                    switch (rand.nextInt(4)) {
                        case 0:
                            modifier = "Pathetic";
                            break;
                        case 1:
                            modifier = "Tiny";
                            break;
                        case 2:
                            modifier = "Crazed";
                            break;
                        case 3:
                            modifier = "Superstitious";
                            break;
                    }
                }
                if (lifeblood >= 2) {
                    switch (rand.nextInt(4)) {
                        case 0:
                            modifier = "Two-faced";
                            break;
                        case 1:
                            modifier = "Disgusting";
                            break;
                        case 2:
                            modifier = "Image-Obsessed";
                            break;
                        case 3:
                            modifier = "Pessimistic";
                            break;
                    }
                }

                switch (rand.nextInt(4)) {
                    case 0:
                        type = Type.SHEEP;
                        break;
                    case 1:
                        type = Type.LAMB;
                        break;
                    case 2:
                        type = Type.OWL;
                        break;
                    case 3:
                        type = Type.CHICKEN;
                        break;
                }
            }

            Being being = new Being(lifeblood, type);
            being.setName(name + " The " + modifier);

            return being;
        } else {
            lifeblood = (lifeblood == 0) ? (rand.nextInt(15) + 1) : lifeblood;

            int enemyType = rand.nextInt(10);
            if(enemyType >= 7){
                type = Type.MAGE;
            } else if (enemyType >= 4){
                type = Type.KRAKEN;
            } else if (enemyType >= 0){
                type = Type.SKELETON;
            }

            Enemy being = new Enemy(lifeblood, type);
            being.setName(name + " The " + modifier);

            return being;
        }
    }

}
