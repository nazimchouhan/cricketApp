package com.example.cricketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MatchScoreActivity extends AppCompatActivity {
    private final String apiURL ="https://api.cricapi.com/v1/currentMatches?apikey=35dd6a8e-3942-475f-883f-967386080602&offset=0";
    private  String matchID;

    private final long period=5000;
    TextView matchTitle,matchStatus,Team1,Team1score,Team2,Team2Score,Match,Matchtype,Venue,Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchinfo);
        Intent intent=getIntent();
        matchTitle=findViewById(R.id.matchTitle);
        matchStatus=findViewById(R.id.matchStatus);
        Team1=findViewById(R.id.Team1);
        Team1score=findViewById(R.id.Team1score);
        Team2=findViewById(R.id.Team2);
        Team2Score=findViewById(R.id.Team2Score);
        Match=findViewById(R.id.Match);
        Matchtype=findViewById(R.id.Matchtype);
        Venue=findViewById(R.id.Venue);
        Date=findViewById(R.id.Date);

        if (intent != null) {
            matchID = intent.getStringExtra("matchID");
            if (matchID == null) {
                // Use the matchID in your activity
                // Example: textView.setText(matchID);
                Log.e("MatchScoreActivity", "matchID is null");
                finish();

            }

        }
        else{
            // Handle the case where the intent is null
            Log.e("MatchScoreActivity", "Intent is null");
            finish();
        }
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getmatchinfo();

                    }
                });
            }
        }, 0, period);
    }
    private void getmatchinfo(){

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, apiURL + "&unique_id=" + matchID, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray dataarr = response.getJSONArray("data");
                    for(int k=0;k< dataarr.length();k++){
                        JSONObject dataobj=dataarr.getJSONObject(k);
                        String id= dataobj.getString("id");
                        if(id.equals(matchID)){
                            ArrayList<String> teamlist=new ArrayList<>();
                            ArrayList<MatchScore> scorecardlist=new ArrayList<>();
                            ArrayList<secondmodel> secondmatchlist=new ArrayList<>();
                            String matchtype=dataobj.optString("matchType","N/A");
                            String matchstatus= dataobj.optString("status","N/A");
                            String venue=dataobj.optString("venue","N/A");
                            String date= dataobj.optString("date","N/A");
                            String match= dataobj.optString("name","N/A");
                            String matchtitle=dataobj.optString("name","N/A");
                            JSONArray teamarr=dataobj.getJSONArray("teams");
                            JSONArray scorearr=dataobj.getJSONArray("score");
                            if (teamarr.length() > 0 && scorearr.length() > 0) {
                                // your existing loop and code...
                                for(int i=0;i<teamarr.length();i++){
                                    teamlist.add(teamarr.getString(i));
                                }
                                for(int j=0;j< scorearr.length();j++){
                                    JSONObject scoreobj=scorearr.getJSONObject(j);
                                    int runs=scoreobj.optInt("r",0);
                                    int overs=scoreobj.optInt("o",0);
                                    int wickets=scoreobj.optInt("w",0);
                                    String innings= scoreobj.optString("inning","N/A");
                                    scorecardlist.add(new MatchScore(runs,wickets,overs,innings));

                                }
                            }
                            secondmatchlist.add(new secondmodel(matchtitle,matchstatus,match,matchtype,venue,date,teamlist,scorecardlist));
                            matchTitle.setText(matchtitle);
                            Log.e("TAG", "matchTitle is : " + matchtitle);
                            matchStatus.setText(matchstatus);

                            Match.setText(match);
                            Matchtype.setText(matchtype);
                            Venue.setText(venue);
                            Date.setText(date);
                            if (!teamlist.isEmpty()) {
                                Team1.setText(teamlist.get(0));
                                Team2.setText(teamlist.get(1));
                            }

                            if (!scorecardlist.isEmpty()) {
                                Team1score.setText(scorecardlist.get(0).getRuns() + "/" + scorecardlist.get(0).getWickets() + "(" + scorecardlist.get(0).getOvers() + ")");
                                Team2Score.setText(scorecardlist.get(1).getRuns() + "/" + scorecardlist.get(1).getWickets() + "(" + scorecardlist.get(1).getOvers() + ")");
                            }
                        }
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MatchScoreActivity.this, "Fail to get response", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}