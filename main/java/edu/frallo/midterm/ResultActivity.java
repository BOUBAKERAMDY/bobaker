package edu.frallo.midterm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class ResultActivity extends AppCompatActivity implements FragmentNotifiable {
    private final String TAG = "fredrallo " + getClass().getSimpleName();
    private Podium podium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        PodiumPokemonFragment podiumPokemonFragment = (PodiumPokemonFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_podium);
        podium = new Podium();
        podium.addObserver(podiumPokemonFragment);
        podium.updateWinners();
    }


    @Override
    public void onFragmentNotify(Fragment fragment) {
        //Pokemon.createPodiumList();
        podium.updateWinners();
    }
}