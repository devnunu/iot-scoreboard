package com.example.anew.score;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by new on 2016. 9. 10..
 */
public class GameInfo implements Serializable {

    private ArrayList HomeScore = new ArrayList();
    private ArrayList AwayScore = new ArrayList();
    private int Round = 0;
    private int Goal_Round = 0;

    public GameInfo(){
        for(int i=0; i<4; i++){
            HomeScore.add(i,0);
            AwayScore.add(i,0);
        }
    }

    public int getGoal_Round(){return Goal_Round;}

    public int getRound(){ return Round;}

    public ArrayList getHomeScore(){
        return HomeScore;
    }

    public ArrayList getAwayScore(){
        return AwayScore;
    }

    public void setGoal_Round(int goal_round){ this.Goal_Round = goal_round;};

    public void setRound(int round){ this.Round = round; }

    public void setHomeScore(int i, int score){
        HomeScore.set(i,score);
    }

    public void setAwayScore(int i, int score){
        AwayScore.set(i,score);
    }
}
