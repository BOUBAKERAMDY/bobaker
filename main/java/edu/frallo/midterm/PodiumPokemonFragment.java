package edu.frallo.midterm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PodiumPokemonFragment extends Fragment implements Observer {
    private final String TAG = "fredrallo "+getClass().getSimpleName();
    private ImageView podiumImage1;
    private TextView displayWinner1;
    private ImageView podiumImage2;
    private TextView displayWinner2;
    private ImageView podiumImage3;
    private TextView displayWinner3;

    //private List<Pokemon> podium;
    public PodiumPokemonFragment() {
        if (Pokemon.completeList==null) new Throwable("Pokemon's completeList is null");
    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_podium_pokemon, container, false);
        podiumImage1 = view.findViewById(R.id.winner_1);
        displayWinner1 = view.findViewById(R.id.txt_win1);
        podiumImage2 = view.findViewById(R.id.winner_2);
        displayWinner2 = view.findViewById(R.id.txt_win2);
        podiumImage3 = view.findViewById(R.id.winner_3);
        displayWinner3 = view.findViewById(R.id.txt_win3);

        return view;
    }


    private void displayChampions(List<Pokemon> champions){
        Picasso.get().load(champions.get(0).getPictureURL()).into(podiumImage1);
        Picasso.get().load(champions.get(1).getPictureURL()).into(podiumImage2);
        Picasso.get().load(champions.get(2).getPictureURL()).into(podiumImage3);
        displayWinner1.setText(champions.get(0).getName()+" ("+champions.get(0).getRank()+")");
        displayWinner2.setText(champions.get(1).getName()+" ("+champions.get(1).getRank()+")");
        displayWinner3.setText(champions.get(2).getName()+" ("+champions.get(2).getRank()+")");

    }

    @Override
    public void update(Observable observable, Object args) {
        Podium podium = (Podium)args;
        Log.d(TAG,"podium data = "+podium);
        displayChampions(((Podium)args).getChampions()); //change display
    }
}