package com.example.twodbrecyclerview;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APISpanish {
    @GET("api/v1/json/3/search_all_teams.php?l=Spanish%20La%20Liga")
    Call<TeamResponse> getTeams();
}