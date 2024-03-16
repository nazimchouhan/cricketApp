package com.example.cricketapp;

import java.util.ArrayList;
import java.util.List;

public class matchlistModel {
        private String matchstatus;
        private List<String> teamlist;
        private List<MatchScore> scorecardlist;
        private String matchtitle;
        private String matchID;

        public matchlistModel(String matchID,String matchtitle,String matchstatus,ArrayList<String> teamlist, ArrayList<MatchScore> scorecardlist){
            this.matchID=matchID;
            this.teamlist=teamlist;
            this.scorecardlist=scorecardlist;
            this.matchstatus=matchstatus;
            this.matchtitle=matchtitle;
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
        public String getMatchID(){
            return matchID;
        }
        public void setMatchID(String matchID){
            this.matchID=matchID;
        }
        public String toString() {
            return "matchlistModel{" +
                    "matchID='" + matchID + '\'' +
                    ", matchtitle='" + matchtitle + '\'' +
                    ", matchstatus='" + matchstatus + '\'' +
                    ", teamlist=" + teamlist +
                    ", scorecardlist=" + scorecardlist +
                    '}';

        }

}
