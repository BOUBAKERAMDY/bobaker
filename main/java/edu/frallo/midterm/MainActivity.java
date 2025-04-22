package edu.frallo.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PostExecuteActivity<Pokemon> {
    private final String TAG = "fredrallo "+getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] languages = getResources().getStringArray(R.array.languages);
        Pokemon.language = languages[0];

        Spinner spiner = findViewById(R.id.languageChoice);
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Pokemon.language = languages[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Vous n'avez rien sélectionné", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.go).setOnClickListener( clic -> {
            String url = "https://raw.githubusercontent.com/fanzeyi/pokemon.json/17d33dc111ffcc12b016d6485152aa3b1939c214/pokedex.json";
            new HttpAsyncGet<>(url, Pokemon.class, this, new ProgressDialog(clic.getContext()) );
        });
    }


    @Override
    public void onPostExecutePokemons(List<Pokemon> itemList) {
        Pokemon pokemonFirst = itemList.get(0);
        Log.d(TAG,"First pokemon = " + pokemonFirst.getName());
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        startActivity(intent);
    }

}