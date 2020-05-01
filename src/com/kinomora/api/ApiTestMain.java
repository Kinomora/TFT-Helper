package com.kinomora.api;

import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.cache.CacheLifetimeHint;
import no.stelar7.api.r4j.basic.cache.impl.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.Platform;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.match.MatchListBuilder;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.pojo.lol.match.Match;
import no.stelar7.api.r4j.pojo.lol.match.MatchReference;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ApiTestMain {

    public static void main(String[] args) throws Exception {
        String key = getKey();

        R4J r4J = new R4J(new APICredentials(key));
        R4J.TFTAPI api = r4J.getTFTAPI();
        Summoner quarris = Summoner.byName(Platform.NA1, "Quarris");
        Summoner kino = Summoner.byName(Platform.NA1, "Jolees Kino");

        Supplier<MySQLCacheProvider> sqlCache = () -> new MySQLCacheProvider("localhost", 3306, "root", "");
        Supplier<MongoDBCacheProvider> mongoCache = () -> new MongoDBCacheProvider("mongodb://localhost:27017");
        Supplier<FileSystemCacheProvider> fileCache = () -> new FileSystemCacheProvider(5);
        Supplier<MemoryCacheProvider> memCache = () -> new MemoryCacheProvider(5);
        Supplier<TieredCacheProvider> tieredCache = () -> new TieredCacheProvider(memCache.get(), fileCache.get(), sqlCache.get());

        DataCall.setCacheProvider(memCache.get());

        doStuff();
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

    public static void doStuff() throws InterruptedException {
        System.out.println("Fetching a random summoner and their match list");

        String               id      = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner             s       = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        List<MatchReference> recents = new MatchListBuilder().withPlatform(Platform.EUW1).withAccountId(s.getAccountId()).get();

        if (recents.isEmpty())
        {
            return;
        }

        MatchReference ref = recents.get(0);


        Match url   = ref.getFullMatch();

        Match cached = ref.getFullMatch();

        if (!url.equals(cached))
        {
            throw new RuntimeException("CACHE IS BROKEN!!!!");
        }

        for (int i = 0; i < 10; i++)
        {
            System.out.println("Match " + i + "\n" + ref.getFullMatch());
        }
        System.out.println(ref.getFullMatch());

        System.out.println("clearing cache");
        DataCall.getCacheProvider().clear(URLEndpoint.V4_MATCH, Collections.emptyMap());

        ref.getFullMatch();

        for (int i = 0; i < 10; i++)
        {
            ref.getFullMatch();
        }
        System.out.println();

        System.out.println("Fetching 3 aditional matches");
        recents.get(1).getFullMatch();
        recents.get(2).getFullMatch();
        recents.get(3).getFullMatch();


        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize(URLEndpoint.V4_MATCH, Collections.emptyMap()));

        System.out.println("Waiting for cache timeout");
        TimeUnit.SECONDS.sleep(6);

        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize(URLEndpoint.V4_MATCH, Collections.emptyMap()));

        System.out.println("Re-fetching cached items");
        recents.get(0).getFullMatch();
        recents.get(1).getFullMatch();
        recents.get(2).getFullMatch();
        recents.get(3).getFullMatch();

        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize(URLEndpoint.V4_MATCH, Collections.emptyMap()));
        System.out.println();
    }

}
