package com.steppers.gamestate;

public class FollowerRoom extends Room {

        private Follower follower;

        public FollowerRoom(Follower follower){
            this.follower = follower;
            this.roomType = 3;
        }

        public Follower getFollower(){
            return follower;
        }

}
