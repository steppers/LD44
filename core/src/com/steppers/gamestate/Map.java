package com.steppers.gamestate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Map {

    private ArrayList<Room> rooms = new ArrayList<>();
    private HashMap<Integer, ArrayList<Integer>> connections = new HashMap<>();
    private Room entryRoom;
    private Room exitRoom;

    private int currentLevel = 0;

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

        for(int i = 0; i < 15; i++){
            switch(random.nextInt(2)){
                case 0: rooms.add(new EmptyRoom());
                        break;
                case 1: rooms.add(new MonsterRoom(null));
                        break;
            }
        }
    }

    public ArrayList<Room> getNextLevelRooms(){
        ArrayList<Room> nextLevel = new ArrayList<>();

        if(currentLevel > 4) {
            int nextRoomIndex = (currentLevel * 3) + 2;
            for (int i = 0; i < 3; i++) {
                nextLevel.add(rooms.get(nextRoomIndex + i));
            }
        } else {
            nextLevel.add(exitRoom);
        }

        return nextLevel;
    }

}
