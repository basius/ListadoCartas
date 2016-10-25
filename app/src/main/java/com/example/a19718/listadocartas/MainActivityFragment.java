package com.example.a19718.listadocartas;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvCartas = (ListView) view.findViewById(R.id.lvCartas);

        String[] data = {
                "Los 400 golpes",
                "El odio",
                "El padrino",
                "El padrino. Parte II",
                "Ocurrió cerca de su casa",
                "Infiltrados",
                "Umberto D.",
                "El odio",
                "El padrino",
                "El padrino. Parte II",
                "Ocurrió cerca de su casa",
                "Infiltrados",
                "Umberto D.",
                "El odio",
                "El padrino",
                "El padrino. Parte II",
                "Ocurrió cerca de su casa",
                "Infiltrados",
                "Umberto D.",
                "El odio",
                "El padrino",
                "El padrino. Parte II",
                "Ocurrió cerca de su casa",
                "Infiltrados",
                "Umberto D.",
                "El odio",
                "El padrino",
                "El padrino. Parte II",
                "Ocurrió cerca de su casa",
                "Infiltrados",
                "Umberto D."
        };

        items = new ArrayList<>(Arrays.asList(data));
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.layout_carta,
                R.id.tvCarta,
                items
        );
        lvCartas.setAdapter(adapter);
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

        if (id == R.id.action_settings) {
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
            CardsApi api = new CardsApi();
            String result = api.getCards();

            Log.d("DEBUG", result);
            return null;
        }
    }
}
