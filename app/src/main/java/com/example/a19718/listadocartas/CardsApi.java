package com.example.a19718.listadocartas;

/**
 * Created by 19718 on 21/10/16.
 */

import android.graphics.Movie;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

class CardsApi {
    private final String BASE_URL = "https://api.magicthegathering.io/v1/cards";

    ArrayList<Card> getCards() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    ArrayList<Card> getColors(String color) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("color",color)
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    ArrayList<Card> getRarity(String rarity) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("rarity",rarity)
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    ArrayList<Card> getRarityAndColor(String rarity, String color) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("rarity",rarity)
                .appendQueryParameter("color",rarity)
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }
    @Nullable
    private ArrayList<Card> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Card> processJson(String jsonResponse) {
        ArrayList<Card> cards = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCards = data.getJSONArray("cards");
            for (int i = 0; i < jsonCards.length(); i++) {
                JSONObject jsonCard = jsonCards.getJSONObject(i);

                Card card = new Card();
                card.setName(jsonCard.getString("name"));
                card.setRarity(jsonCard.getString("rarity")); //Hi han cartes que no tenen raresa
                //Si contiene texto
                if(!jsonCard.isNull(("text"))) {
                    card.setText(jsonCard.getString("text"));
                //Si no contiene texto
                }else{
                    card.setText("NO_TEXT");
                }
                //Si no contiene color
                if(!jsonCard.isNull(("colors"))) {
                    card.setColor(jsonCard.getString("colors"));
                }else{
                    card.setColor("NO_COLOR");
                }
                card.setType(jsonCard.getString("type"));
                //Si no conte link d'imatge, ignorem
                if(!jsonCard.isNull("imageUrl")) {
                    card.setUrlImage(jsonCard.getString("imageUrl"));
                }

                cards.add(card);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cards;
    }

}
