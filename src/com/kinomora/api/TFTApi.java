package com.kinomora.api;

import java.io.*;
import java.net.URI;
import java.net.URL;

public class TFTApi {

    private final String key;
    private final String region;
    private final String route;

    public TFTApi(String region) {
        this.region = region;
        this.route = region + ".api.riotgames.com";
        String key = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("keys", "tft-api")));
            key = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.key = key;

        //https://na1.api.riotgames.com/tft/summoner/v1/summoners/by-name/Quarris?api_key=RGAPI-040ae605-872c-481f-bda4-f075a31db928
        //        na1.api.riotgames.com/tft/summoner/v1/summoners/by-name/Quarris?api_key=RGAPI-040ae605-872c-481f-bda4-f075a31db928
        String command = "https://"+this.route + "/tft/summoner/v1/summoners/by-name/Jolees_Kino?api_key="+this.key;
        try {
            URL url = new URL(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
