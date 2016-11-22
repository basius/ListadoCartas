package com.example.a19718.listadocartas;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by 19718 on 15/11/16.
 */

public class CardAdapter extends ArrayAdapter<Card>{

    public CardAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtenim l'objecte en la possició corresponent
        Card card = getItem(position);
        Log.w("XXXX", card.toString());

        // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
        // https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView#row-view-recycling
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_carta_row, parent, false);
        }

        // Unim el codi en les Views del Layout
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvType = (TextView) convertView.findViewById(R.id.tvType);
        ImageView ibImage = (ImageView) convertView.findViewById(R.id.ibPicture);

        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        tvName.setText(card.getName());
        tvType.setText(card.getType());

        // Retornem la View replena per a mostrarla
        return convertView;
    }
}
