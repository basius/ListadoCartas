package com.example.a19718.listadocartas;

/**
 * Created by basius on 20/12/16.
 */
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.a19718.listadocartas.databinding.LvCartaRowBinding;

public class CardCursorAdapter extends CupboardCursorAdapter<Card> {
    public CardCursorAdapter(Context context, Class<Card> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, Card model, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LvCartaRowBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.lv_carta_row, parent, false);

        return binding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, Card model) {
        LvCartaRowBinding binding = DataBindingUtil.getBinding(view);
        binding.tvName.setText(model.getName());
        binding.tvType.setText(model.getType());
        Glide.with(context).load(model.getUrlImage()).into(binding.ibPicture);
    }
}
