package com.JoelLovgrenNordell.Server;


import com.google.gson.Gson;

public class JSONHandler {
    public JSONHandler(){
    }

    public String encode(BoardGenerator board){
        Gson gson = new Gson();
        return gson.toJson(board);
    }
}
