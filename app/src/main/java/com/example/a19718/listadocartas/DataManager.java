package com.example.a19718.listadocartas;

import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

import nl.littlerobots.cupboard.tools.provider.UriHelper;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by basius on 19/12/16.
 */

public class DataManager {
    private static UriHelper URI_HELPER = UriHelper.with(CardsContentProvider.AUTHORITY);
    private static Uri CARD_URI = URI_HELPER.getUri(Card.class);

    static void saveCards(List<Card> movies, Context context) {
        cupboard().withContext(context).put(CARD_URI, Card.class, movies);
    }

    static void deleteCards(Context context) {
        cupboard().withContext(context).delete(CARD_URI, "_id > ?", "1");
    }
}
