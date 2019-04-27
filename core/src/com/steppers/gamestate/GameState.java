package com.steppers.gamestate;

public class GameState {

    Character character;
    Map dungeonMap;

    final int startLifeBlood = 100;

    public GameState(){
        character = new Character(startLifeBlood);
        dungeonMap = new Map();
        dungeonMap.generateMap();
    }

    public Map getDungeonMap(){
        return dungeonMap;
    }

    public Character getCharacter(){
        return character;
    }
}
