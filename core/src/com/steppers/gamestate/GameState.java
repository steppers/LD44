package com.steppers.gamestate;

public class GameState {

    Character character;
    Map dungeonMap;

    final int startLifeBlood = 100;

    public GameState(){
        character = new Character(startLifeBlood);
        dungeonMap = new Map();
        dungeonMap.generateMap(1,2);
        dungeonMap.addConnection(0,1);
    }

    public Map getDungeonMap(){
        return dungeonMap;
    }

    public Character getCharacter(){
        return character;
    }
}
