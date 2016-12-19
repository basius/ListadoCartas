package com.example.a19718.listadocartas;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;
/**
 * Created by basius on 19/12/16.
 */

public class CardsContentProvider extends CupboardContentProvider {
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        cupboard().register(Card.class);
    }
    public CardsContentProvider() {
        super(AUTHORITY, 1);
    }
}
