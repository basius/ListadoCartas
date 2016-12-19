package com.example.a19718.listadocartas;

import com.google.gson.Gson;

import nl.littlerobots.cupboard.tools.convert.GsonFieldConverter;
import nl.littlerobots.cupboard.tools.convert.ListFieldConverterFactory;
import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;
import nl.qbusict.cupboard.CupboardBuilder;
import nl.qbusict.cupboard.CupboardFactory;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;
/**
 * Created by basius on 19/12/16.
 */

public class CardsContentProvider extends CupboardContentProvider {
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        CupboardFactory.setCupboard(new CupboardBuilder().
                registerFieldConverterFactory(new ListFieldConverterFactory(new Gson())).build());
        cupboard().register(Card.class);
    }
    public CardsContentProvider() {
        super(AUTHORITY, 1);
    }
}
