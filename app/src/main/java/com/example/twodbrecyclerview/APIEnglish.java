package com.example.twodbrecyclerview;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIEnglish {
    @GET("api/v1/json/3/search_all_teams.php?l=English%20Premier%20League")
    Call<TeamResponse> getTeams();
}