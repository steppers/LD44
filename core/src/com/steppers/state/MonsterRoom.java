package com.steppers.state;

public class MonsterRoom extends Room {

    private Enemy enemy;

    public MonsterRoom(Enemy enemy){
        this.enemy = enemy;
        this.roomType = 2;
    }

    public Enemy getEnemy(){
        return enemy;
    }
}
