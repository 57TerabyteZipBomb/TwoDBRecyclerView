package com.example.twodbrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private List<Team> teams = new ArrayList<>();

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_itemlayout, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        // Get the team data
        Team team = teams.get(position);

        // Set the team name and badge
        holder.teamName.setText(team.getStrTeam());
        Picasso.get().load(team.getStrBadge()).into(holder.teamBadge);  // Load image using Picasso
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
        notifyDataSetChanged();
    }

    // ViewHolder class to hold references to the views
    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView teamName;
        ImageView teamBadge;

        public TeamViewHolder(View itemView) {
            super(itemView);

            // Find the views in the item layout (team_itemlayout.xml)
            teamName = itemView.findViewById(R.id.teamName);
            teamBadge = itemView.findViewById(R.id.teamBadge);
        }
    }
}
