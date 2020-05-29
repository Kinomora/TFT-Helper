package com.kinomora;

import com.kinomora.window.MainWindow;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
import no.stelar7.api.r4j.impl.lol.async.AsyncMatchAPI;
import no.stelar7.api.r4j.impl.lol.async.AsyncSummonerAPI;
import no.stelar7.api.r4j.impl.lol.raw.SpectatorAPI;
import no.stelar7.api.r4j.pojo.lol.spectator.SpectatorGameInfo;
import no.stelar7.api.r4j.pojo.lol.spectator.SpectatorParticipant;
import no.stelar7.api.r4j.pojo.tft.TFTMatch;
import no.stelar7.api.r4j.pojo.tft.TFTParticipant;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static File icons = new File("icons");

    public static final String APIKEY = "RGAPI-a6e35d9f-ce52-4bb2-a57c-dd79ffab7138";
    public static final String PLAYER = "JOLEES KINO";

    public static void main(String[] args) throws Exception {
        //set look and feel
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        //load the items from items.json
        FileManager.loadItems();

        //load the recipes from recipes.json - must be done after items
        FileManager.loadRecipes();

        //Set up the GUI window - must be done after loading items and recipes
        MainWindow window = new MainWindow("TFT Helper - Set 3");
        window.pack();
        window.setSize(568, 890);
        window.setResizable(false);
        window.setVisible(true);

        apiTest();
        TFTTest();
    }


    private static void apiTest() throws RiotApiException {
        ApiConfig config = new ApiConfig().setKey(APIKEY);
        RiotApi api = new RiotApi(config);

        Summoner summoner = api.getSummonerByName(Platform.NA, PLAYER);
        System.out.println("Name: " + summoner.getName());
        System.out.println("Summoner ID: " + summoner.getId());
        System.out.println("Account ID: " + summoner.getAccountId());
        System.out.println("PUUID: " + summoner.getPuuid());
        System.out.println("Summoner Level: " + summoner.getSummonerLevel());
        System.out.println("Profile Icon ID: " + summoner.getProfileIconId());
    }

    public static void TFTTest() {
        /*AsyncSummonerAPI as = AsyncSummonerAPI.getInstance();
        AsyncMatchAPI am = AsyncMatchAPI.getInstance();

        List<CompletableFuture> futures = new ArrayList<>();

        List<Summoner> summoners = new ArrayList<>();
        for (Platform p : Platform.getSpectatorPlatforms()) {
            List<SpectatorGameInfo> games = SpectatorAPI.getInstance().getFeaturedGames(p);
            SpectatorGameInfo gameInfo = games.get(0);
            List<SpectatorParticipant> participants = gameInfo.getParticipants();
            SpectatorParticipant participant = participants.get(0);

            String id = participant.getSummonerName();
            Platform pl = gameInfo.getPlatform();

            Summoner s = Summoner.byName(pl, id);
            summoners.add(s);
        }

        for (Summoner s : summoners) {
            for (int i = 0; i < 100; i++) {
                futures.add(as.getSummonerByAccount(s.getPlatform(), s.getAccountId()).thenAccept(this::handleSummonerCallback));
                futures.add(am.getMatchList(s.getPlatform(), s.getAccountId()).thenAccept(this::handleMatchCallback));
            }
        }

        CompletableFuture spinner = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        spinner.join();
    }

    private void handleMatchCallback(List<MatchReference> matchReferences) {
        System.out.println(matchReferences);
    }

    private void handleSummonerCallback(Summoner u) {
        System.out.println(u);*/
    }
}
