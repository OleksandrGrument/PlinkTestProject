package com.grument.plinktestproject.dto;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WorldNewsResultsDTO {

    public WorldNewsResultsDTO (){}

    @SerializedName("results")
    private ArrayList<NewsDTO> newsDTOArrayList;

    public ArrayList<NewsDTO> getNewsDTOArrayList() {
        return newsDTOArrayList;
    }

    public void setNewsDTOArrayList(ArrayList<NewsDTO> newsDTOArrayList) {
        this.newsDTOArrayList = newsDTOArrayList;
    }
}
