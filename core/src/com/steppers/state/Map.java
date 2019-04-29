package com.steppers.state;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Map {

    private ArrayList<Room> rooms = new ArrayList<>();
    private HashMap<Integer, ArrayList<Integer>> connections = new HashMap<>();
    private Room entryRoom;
    private Room exitRoom;

    private int currentLevel = 0;
    private int currentRoom = 1;

    public Map(){
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public void addConnection(int index1, int index2){
        if(connections.get(index1) == null){
            ArrayList<Integer> path = new ArrayList<>();
            path.add(index2);
            connections.put(index1, path);
        } else {
            connections.get(index1).add(index2);
        }
    }

    public void generateMap(){
        Random random = new Random();

        this.exitRoom = new AltarRoom();
        rooms.add(exitRoom);

        this.entryRoom = new EmptyRoom();
        rooms.add(entryRoom);

        Texture[] roomTextures = new Texture[5];

        for(int i = 0; i < 5; i++){
            switch(random.nextInt(4)){
                case 0: roomTextures[i] = new Texture("Hieroglyphica.png");
                    break;
                case 1: roomTextures[i] = new Texture("Infinity.png");
                    break;
                case 2: roomTextures[i] = new Texture("Pentagram.png");
                    break;
                case 3: roomTextures[i] = new Texture("Sun.png");
                    break;
            }
        }


        for(int i = 0; i < 15; i++){
            switch(random.nextInt(5)){
                case 0: EmptyRoom room1 = new EmptyRoom();
                        room1.setSymbol(roomTextures[0]);
                        rooms.add(room1);
                        break;
                case 1: MonsterRoom room2 = new MonsterRoom((Enemy)Being.GenerateRandomBeing(true));
                        room2.setSymbol(roomTextures[1]);
                        rooms.add(room2);
                        break;
                case 2: MonsterRoom room3 = new MonsterRoom((Enemy)Being.GenerateRandomBeing(true));
                        room3.setSymbol(roomTextures[2]);
                        rooms.add(room3);
                        break;
                case 3: MonsterRoom room4 = new MonsterRoom((Enemy)Being.GenerateRandomBeing(true));
                        room4.setSymbol(roomTextures[3]);
                        rooms.add(room4);
                        break;
                case 4: FollowerRoom room5 = new FollowerRoom(Being.GenerateRandomBeing(false));
                        room5.setSymbol(roomTextures[4]);
                        rooms.add(room5);
                        break;
            }
        }

        setSymbols();
    }

    public ArrayList<Room> getNextLevelRooms(){
        ArrayList<Room> nextLevel = new ArrayList<>();

        if(currentLevel < 5) {
            int nextRoomIndex = (currentLevel * 3) + 2;
            for (int i = 0; i < 3; i++) {
                nextLevel.add(rooms.get(nextRoomIndex + i));
            }
        } else {
            nextLevel.add(exitRoom);
        }

        return nextLevel;
    }

    public void setSymbols(){
        Random random = new Random();
        for(Room r : rooms){
            switch(random.nextInt(4)){
                case 0: r.setSymbol(new Texture("Hieroglyphica.png"));
                        break;
                case 1: r.setSymbol(new Texture("Infinity.png"));
                        break;
                case 2: r.setSymbol(new Texture("Pentagram.png"));
                        break;
                case 3: r.setSymbol(new Texture("Sun.png"));
                        break;
            }
        }
    }

    public Room getCurrentRoom(){
        return rooms.get(currentRoom);
    }

    public void nextLevel(int i){

        currentRoom = currentLevel * 3 + 2 + i;
        currentLevel++;

        if(currentRoom > 16){
            currentRoom = 0;
            exitRoom.setVisited(true);
            currentLevel = 5;
        }

    }

}
