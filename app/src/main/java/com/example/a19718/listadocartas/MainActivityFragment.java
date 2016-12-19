package com.example.a19718.listadocartas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.util.Log;
import nl.littlerobots.cupboard.tools.provider.UriHelper;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

import com.example.a19718.listadocartas.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ArrayList<Card> items;
    private CardAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMainBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);
        View view = binding.getRoot();
        items = new ArrayList<>();
        adapter = new CardAdapter(
                getContext(),
                R.layout.lv_carta_row,
                items
        );

        binding.lvCartas.setAdapter(adapter);
        binding.lvCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Card card = (Card) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("card",card);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cartes_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.refreshCards) {
            refresh();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh(){
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            //Common, Uncommon, Rare, Mythic Rare, Special, Basic Land] y el color de la carta.
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String rarity = preferences.getString("rarity", "All");
            String color = preferences.getString("color", "All");


            List<Card> result = null;
            if(!rarity.equals("All") && !color.equals("All")) {
                //Basic Land no tenen color. Filtrem perque nomes retorni les que es demana un color especific
                if ((!(rarity.equals("Basic Land")))) {
                    result = CardsApi.getRarityAndColor(rarity, color);
                } else {
                    result = CardsApi.getRarity(rarity);
                }
            }else if (!rarity.equals("All")){
                result = CardsApi.getRarity(rarity);
            }else if (!color.equals("All")){
                result = CardsApi.getColors(color);
            }else {
                result = CardsApi.getCards();
            }
            Log.d("DEBUG","++++++++++"+rarity+":"+color);
            // Log.d("DEBUG", result != null ? result.toString() : null);
            UriHelper helper = UriHelper.with(CardsContentProvider.AUTHORITY);
            Uri cardUri = helper.getUri(Card.class);
            cupboard().withContext(getContext()).put(cardUri, Card.class, result);

            return null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }


}
