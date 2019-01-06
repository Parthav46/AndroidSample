package com.example.footballref;

public class MatchData {
    private String teamA;
    private String teamB;
    private int scoreA;
    private int scoreB;

    MatchData(String teamA, String teamB, int scoreA, int scoreB) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }

    public int getScoreA() {
        return scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public String getTeamA() {
        return teamA;
    }

    public String getTeamB() {
        return teamB;
    }
}
