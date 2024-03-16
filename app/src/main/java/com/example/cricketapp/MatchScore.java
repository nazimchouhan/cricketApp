package com.example.cricketapp;

public class MatchScore {

    private int runs;
    private int wickets;
    private int overs;
    private String innings;
    public MatchScore(int runs,int wickets,int overs,String innings){
        this.runs=runs;
        this.wickets=wickets;
        this.overs=overs;
        this.innings=innings;
    }
    public int getRuns(){
        return runs;
    }
    public void setRuns(int runs){
        this.runs=runs;
    }
    public int getWickets(){
        return wickets;
    }
    public void setWickets(int wickets){
        this.wickets=wickets;
    }
    public int getOvers(){
        return overs;
    }
    public void setOvers(int overs){
        this.overs=overs;
    }
    public String getInnings(){
        return innings;
    }
    public void setInnings(String innings){
        this.innings=innings;
    }

    public String toString() {
        return "MatchScore{" +
                "runs=" + runs +
                ", wickets=" + wickets +
                ", overs=" + overs +
                ", innings='" + innings + '\'' +
                '}';
    }
}