package com.JoelLovgrenNordell.Client;

import com.google.gson.Gson;

public class JSONHandler {
    public JSONHandler(){
    }

    public Board decode(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Board.class);
    }
}
