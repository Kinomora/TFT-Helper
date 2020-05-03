package com.kinomora.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.cache.CacheLifetimeHint;
import no.stelar7.api.r4j.basic.cache.impl.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.Platform;
import no.stelar7.api.r4j.basic.constants.api.ServicePlatform;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.match.MatchListBuilder;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.impl.lol.lcu.LCUApi;
import no.stelar7.api.r4j.impl.tft.TFTMatchAPI;
import no.stelar7.api.r4j.pojo.lol.match.Match;
import no.stelar7.api.r4j.pojo.lol.match.MatchReference;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ApiTestMain {

    public static void main(String[] args) throws Exception {
        String key = getKey();

        R4J r4J = new R4J(new APICredentials(key));
        R4J.TFTAPI api = r4J.getTFTAPI();
        TFTMatchAPI matchAPI = api.getMatchAPI();

        Summoner summoner = api.getSummonerAPI().getSummonerByName(Platform.NA1, "Quarris");

        List<String> matches = matchAPI.getMatchList(ServicePlatform.AMERICAS, summoner.getPUUID(), 10);

        System.out.println(summoner);
        System.out.println(matches);
        System.out.println(matchAPI.getMatch(ServicePlatform.AMERICAS, matches.get(0)));

        getWebsocketEvents().stream().filter(s -> s.contains("tft")).forEach(System.out::println);

        Thread thread = new Thread(() -> {

        });

        thread.start();
        thread.join();
    }

    public static Set<String> getWebsocketEvents() {
        String      obj2   = (String) LCUApi.customUrl("help", "", "GET");
        JsonElement parsed = JsonParser.parseString(obj2);
        JsonObject events = parsed.getAsJsonObject().getAsJsonObject("events");
        return events.keySet();
    }

    public static String getKey() {
        String key = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("keys", "tft-api")));
            key = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }
}
