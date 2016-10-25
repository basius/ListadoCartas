package com.example.a19718.listadocartas;

/**
 * Created by 19718 on 21/10/16.
 */

import android.net.Uri;

import java.io.IOException;

public class CardsApi {
    private final String BASE_URL = "https://api.magicthegathering.io/v1/cards/";

    String getCards() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .build();
        String url = builtUri.toString();

        try {
            String JsonResponse = HttpUtils.get(url);
            return JsonResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
