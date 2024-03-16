package com.example.cricketapp;

import java.util.List;

public class secondmodel {
    private String matchtitle;
    private String matchstatus;
    private String match;
    private String matchtype;

    private String venue;
    private String date;

    private List<String> teamlist;
    private List<MatchScore> scorecardlist;
    public secondmodel(String matchtitle,String matchstatus,String match,String matchtype,String venue,String date,List<String> teamlist,List<MatchScore> scorecardlist){

        this.matchtitle=matchtitle;
        this.matchstatus=matchstatus;
        this.match=match;
        this.matchtype=matchtype;

        this.venue=venue;
        this.date=date;
        this.teamlist=teamlist;
        this.scorecardlist=scorecardlist;
    }
    public List<String> getTeamlist(){
        return teamlist;
    }
    public void setTeamlist(List<String> teamlist){
        this.teamlist=teamlist;
    }
    public List<MatchScore> getScorecardlist(){
        return scorecardlist;
    }
    public void setScorecardlist(List<MatchScore> scorecardlist){
        this.scorecardlist=scorecardlist;
    }
    public String getMatchtitle(){
        return matchtitle;
    }
    public void setMatchtitle(String matchtitle){
        this.matchtitle=matchtitle;
    }
    public String getMatchstatus(){
        return matchstatus;
    }
    public void setMatchstatus(String matchstatus){
        this.matchstatus=matchstatus;
    }

    public String getVenue(){
        return venue;
    }
    public void setVenue(){
        this.venue=venue;
    }
    public String getDate(){
        return date;
    }
    public void setDate(){
        this.date=date;
    }
    public String getMatch(){
        return match;
    }
    public void setMatch(){
        this.match=match;
    }
    public String getMatchtype(){
        return matchtype;
    }
    public void setMatchtype(){
        this.matchtype=matchtype;
    }
}
