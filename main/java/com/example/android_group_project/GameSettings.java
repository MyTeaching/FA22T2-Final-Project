package com.example.android_group_project;

import java.io.Serializable;

public class GameSettings implements Serializable {

    private int bgColor;
    private int trophy;
    private int difficultyLevel;
    private Difficulty difficulty;
    public GameSettings(){
        this(R.color.white, R.drawable.ic_baseline_sports_esports_24,Difficulty.EASY);
    }
    public GameSettings(int bgColor, int trophy, Difficulty difficulty){
        this.bgColor = bgColor;
        this.trophy = trophy;
        this.difficulty = difficulty;

        switch (difficulty){
            case EASY:
                difficultyLevel = 1;
                break;
            case NORMAL:
                difficultyLevel = 2;
                break;
            case HARD:
                difficultyLevel = 3;
                break;
            default:
                difficultyLevel = 1;
                break;
        }
    }

    public int getBgColor() {
        return bgColor;
    }

    public int getTrophy() {
        return trophy;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
