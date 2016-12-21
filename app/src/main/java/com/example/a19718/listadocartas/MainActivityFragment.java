package com.example.a19718.listadocartas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
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
import android.database.Cursor;
import com.example.a19718.listadocartas.databinding.FragmentMainBinding;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private CardCursorAdapter adapter;
    private ProgressDialog dialog;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMainBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);
        View view = binding.getRoot();
        adapter = new CardCursorAdapter(getContext(), Card.class);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading,wait plz...");
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
        getLoaderManager().initLoader(0, null, this);
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
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
    private class RefreshDataTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }
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
            DataManager.deleteCards(getContext());
            DataManager.saveCards(result, getContext());
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //concervem el refresh per tal de que quan toquem les settings s'actualitzi automaticament
        refresh();
    }


}
