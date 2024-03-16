package com.example.cricketapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private final String apiURL = "https://api.cricapi.com/v1/currentMatches?apikey=35dd6a8e-3942-475f-883f-967386080602&offset=0";
    RecyclerView RVview;
    ArrayList<matchlistModel> matchlist = new ArrayList<>();
    private final long period = 5000;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RVview = findViewById(R.id.RVview);
        RVview.setHasFixedSize(true);
        RVview.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getmatchesdata();

                    }
                });
            }
        }, 0, period);
    }

    private void getmatchesdata() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, apiURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray dataarr = response.getJSONArray("data");
                            RVview.setVisibility(View.VISIBLE);
                            for (int i = 0; i < dataarr.length(); i++) {
                                JSONObject dataObj = dataarr.getJSONObject(i);
                                String matchtitle = dataObj.getString("name");
                                String matchstatus = dataObj.getString("status");
                                String matchID = dataObj.getString("id");
                                JSONArray teamsarr = dataObj.getJSONArray("teams");
                                ArrayList<String> teamlist = new ArrayList<>();
                                if(teamsarr.length()>0) {
                                    for (int j = 0; j < teamsarr.length(); j++) {
                                        String teamname1 = teamsarr.getString(j);
                                        teamlist.add(teamname1);
                                    }
                                }
                                Log.d("TAG", "Teams list is : " + teamlist);
                                ArrayList<MatchScore> scorecardlist = new ArrayList<>();
                                JSONArray scorearr = dataObj.getJSONArray("score");
                                if (scorearr.length() > 0) {
                                    for (int k = 0; k < scorearr.length(); k++) {
                                        JSONObject scoreobj = scorearr.getJSONObject(k);
                                        int runs = scoreobj.getInt("r");
                                        int overs = scoreobj.getInt("o");
                                        int wickets = scoreobj.getInt("w");
                                        String innings = scoreobj.getString("inning");
                                        scorecardlist.add(new MatchScore(runs, wickets, overs, innings));
                                    }
                                } else {
                                    continue;

                                }
                                Log.e("TAG", "Score list is : " + scorecardlist);

                                matchlist.add(new matchlistModel(matchID, matchtitle, matchstatus, teamlist, scorecardlist));
                                Log.e("TAG", "Data from API is : " + matchlist);
                                Log.e("TAG", "Size of matchlist: " + matchlist.size());

                            }
                            RVadapter adapter = new RVadapter(MainActivity.this, matchlist);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    RVview.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                            });

                        }catch (JSONException e) {
                            Log.e("TAG", "Error parsing JSON data: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener() {
                   @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Fail to get response", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(request);
    }
}










