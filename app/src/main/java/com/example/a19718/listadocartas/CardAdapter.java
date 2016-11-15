package com.example.a19718.listadocartas;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;


/**
 * Created by 19718 on 15/11/16.
 */

public class CardAdapter extends ArrayAdapter<Card>{

    public CardAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, resource, objects);
    }
}
