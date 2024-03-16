package com.example.cricketapp;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RVadapter extends RecyclerView.Adapter<RVadapter.Viewholder>{
    private final Context context;
    ArrayList<matchlistModel> matchlist;
    RVadapter(Context context,ArrayList<matchlistModel> matchlist){
        this.context=context;
        this.matchlist=matchlist;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.match_rv_item,parent,false);
        return new Viewholder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder,int position) {
        if(matchlist!=null && position>=0 && position < matchlist.size()){
            matchlistModel match=matchlist.get(position);
            holder.title.setText(match.getMatchtitle());
            holder.status.setText(match.getMatchstatus());
            List<String> teamslist=match.getTeamlist();

            holder.team1.setText(teamslist.get(0));
            holder.team2.setText(teamslist.get(1));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MatchScoreActivity.class);
                    intent.putExtra("matchID",matchlist.get(holder.getAdapterPosition()).getMatchID());
                    context.startActivity(intent);
                }
            });
            List<MatchScore> scoreList = match.getScorecardlist();
            if (!scoreList.isEmpty()) {
                holder.team1Score.setText(scoreList.get(0).getRuns() + "/" + scoreList.get(0).getWickets() + "(" + scoreList.get(0).getOvers() + ")");
                if(scoreList.size()>1){
                    holder.team2Score.setText(scoreList.get(1).getRuns() + "/" + scoreList.get(1).getWickets() + "(" + scoreList.get(1).getOvers() + ")");

                }
                else{
                    holder.team2Score.setText("");
                }
            }
            else{
                holder.team1Score.setText("");
                holder.team2Score.setText("");
            }
        }
    }

    @Override
    public int getItemCount() {

        return matchlist.size();

    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        TextView title,status,team1,team2,team1Score,team2Score;
        CardView cardview;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            cardview=itemView.findViewById(R.id.cardview);
            title=itemView.findViewById(R.id.title);
            status=itemView.findViewById(R.id.status);
            team1=itemView.findViewById(R.id.team1);
            team2= itemView.findViewById(R.id.team2);
            team1Score=itemView.findViewById(R.id.team1Score);
            team2Score= itemView.findViewById(R.id.team2Score);

        }
    }
}