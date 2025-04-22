package edu.frallo.midterm;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class ListPokemonFragment extends Fragment {
    private final String TAG = "fredrallo "+getClass().getSimpleName();
    private int bonus;
    private FragmentNotifiable callbackActivity;

    public ListPokemonFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentNotifiable)
            callbackActivity = (FragmentNotifiable) context;
        else
            throw new RuntimeException("FragmentNotifiable not implemented in context");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_pokemon, container, false);
        PokemonAdapter adapter = new PokemonAdapter(new Clickable() {
            @Override
            public void onClickName(int position) {
                Log.d(TAG,Pokemon.completeList.get(position).getName() + " ("+Pokemon.completeList.get(position).getBaseValue(Stats.HP) + ") - "+Pokemon.completeList.get(position).getTypes());
            }

            @Override
            public Context getContext() {
                return getActivity().getApplicationContext();
            }
        });
        ((ListView)view.findViewById(R.id.list_pokemon)).setAdapter(adapter);
        SeekBar seekBar = view.findViewById(R.id.seekBar);
        TextView displayBonus = view.findViewById(R.id.bonus);
        final int MIDDLE = seekBar.getProgress();
        bonus = MIDDLE;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                displayBonus.setText((progress>=MIDDLE?"+":"")+(progress-MIDDLE));
                //Log.d(TAG,"  progress="+progress + "     bonus="+bonus);

                Pokemon.boost(progress-bonus);
                adapter.notifyDataSetChanged();
                callbackActivity.onFragmentNotify(ListPokemonFragment.this);
                bonus = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        return view;
    }
}