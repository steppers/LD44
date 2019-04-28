package com.steppers.gamestate;

public class GameState {

    Character character;
    Map dungeonMap;

    final int startLifeBlood = 100;

    private static GameState ins = new GameState();
    public static GameState Get() {
        return ins;
    }
    public static GameState New() {
        ins = new GameState();
        return ins;
    }

    private GameState(){
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
