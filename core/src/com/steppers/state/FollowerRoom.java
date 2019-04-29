package com.steppers.state;

public class FollowerRoom extends Room {

        private Being follower;

        public FollowerRoom(Being follower){
            this.follower = follower;
            this.roomType = 3;
        }

        public Being getFollower(){
            return follower;
        }

}
