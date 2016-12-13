package com.example.a19718.listadocartas;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    private View view;
    private ImageView ibPicture;
    private TextView tvName;
    private TextView tvType;
    private TextView tvText;
    private TextView tvColor;
    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent i = getActivity().getIntent();

        if (i != null) {
            Card card = (Card) i.getSerializableExtra("card");
            if (card != null) {
                updateUi(card);
            }
        }
        return view;
    }

    private void updateUi(Card card) {
        Log.d("Card", card.toString());
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvType = (TextView) view.findViewById(R.id.tvType);
        tvText = (TextView) view.findViewById(R.id.tvText);
        tvColor = (TextView) view.findViewById(R.id.tvColor);
        ibPicture = (ImageView) view.findViewById(R.id.ibPicture);
        tvName.setText(card.getName());
        tvType.setText(card.getType());
        tvText.setText(card.getText());
        tvColor.setText(card.getColor());
        Glide.with(getContext()).load(card.getUrlImage()).into(ibPicture);
    }
}

