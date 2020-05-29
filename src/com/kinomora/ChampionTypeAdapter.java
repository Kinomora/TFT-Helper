package com.kinomora;

import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ChampionTypeAdapter extends TypeAdapter<ChampionType> {
    @Override
    public void write(JsonWriter jsonWriter, ChampionType championType) throws IOException {

    }

    @Override
    public ChampionType read(JsonReader jsonReader) throws IOException {
        return null;
    }

}