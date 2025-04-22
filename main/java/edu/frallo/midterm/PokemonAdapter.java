package edu.frallo.midterm;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PokemonAdapter extends BaseAdapter {
    private final String TAG = "frallo "+getClass().getSimpleName();
    private Clickable activity;
    private LayoutInflater mInflater;



    public PokemonAdapter(Clickable activity){
        this.activity = activity;
        mInflater = LayoutInflater.from(activity.getContext());
    }

    @Override
    public int getCount() {
        return Pokemon.completeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //(1) : Réutilisation des layouts
        View layoutItem = convertView == null ? mInflater.inflate(R.layout.pokemon_layout, parent, false) : convertView;

        //(2) : Récupération des TextView de notre layout
        TextView displayName = layoutItem.findViewById(R.id.pokemonName);
        TextView displayHPValue = layoutItem.findViewById(R.id.pokemonHPValue);
        TextView displaySpeedValue = layoutItem.findViewById(R.id.pokemonSpeedValue);
        TextView displayAttackValue = layoutItem.findViewById(R.id.pokemonAttackValue);
        TextView displayDefenseValue = layoutItem.findViewById(R.id.pokemonDefenseValue);
        ImageView displayPicture = layoutItem.findViewById(R.id.pokemonPicture);

        //(3) : Renseignement des valeurs
        displayName.setText(Pokemon.completeList.get(position).getName());
        displayName.setTextSize(24);
        displayName.setText(Pokemon.completeList.get(position).getName());
        displayName.setTypeface(null, Typeface.BOLD);
        displayName.setTextColor(Color.BLACK);


        displaySpeedValue.setText( "speed: "+Pokemon.completeList.get(position).getBaseValue(Stats.Speed) );
        displaySpeedValue.setTextSize(14);
        displaySpeedValue.setTextColor(Color.WHITE);
        displaySpeedValue.setTypeface(null, Typeface.BOLD);

        displayAttackValue.setText( "attack: "+ Pokemon.completeList.get(position).getBaseValue(Stats.Attack) );
        displayAttackValue.setTextSize(14);
        displayAttackValue.setTextColor(Color.WHITE);
        displayAttackValue.setTypeface(null, Typeface.BOLD);

        displayDefenseValue.setText( "defense: "+Pokemon.completeList.get(position).getBaseValue(Stats.Defense) );
        displayDefenseValue.setTextSize(14);
        displayDefenseValue.setTextColor(Color.WHITE);
        displayDefenseValue.setTypeface(null, Typeface.BOLD);

        displayHPValue.setText( "life: "+ Pokemon.completeList.get(position).getBaseValue(Stats.HP) );
        displayHPValue.setTextSize(14);
        displayHPValue.setTextColor(Color.WHITE);
        displayHPValue.setTypeface(null, Typeface.BOLD);

        displayPicture.setImageResource(Pokemon.completeList.get(position).getId());
        Picasso.get().load(Pokemon.completeList.get(position).getPictureURL()).into(displayPicture);

        layoutItem.setOnClickListener( view -> {
            activity.onClickName(position);
        });

        return layoutItem; //On retourne l'item créé.
    }



}
