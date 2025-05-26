package com.example.twodbrecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnglishTeams extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TeamAdapter teamAdapter;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_english_teams);

        // Set up RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.rvTeams);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        teamAdapter = new TeamAdapter();
        recyclerView.setAdapter(teamAdapter);

        // Set up Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/")  // Base URL for SportDB API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create API instance
        APIEnglish api = retrofit.create(APIEnglish.class);

        // Make the API request
        api.getTeams().enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful()) {
                    // Update the RecyclerView with the list of teams
                    teamAdapter.setTeams(response.body().getTeams());
                } else {
                    Log.e("MainActivity", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                // Handle failure
                Toast.makeText(EnglishTeams.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        buttonBack = (Button) findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnglishTeams.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}