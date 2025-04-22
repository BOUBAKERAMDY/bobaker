package edu.frallo.myapplication;


import android.content.Context;

/**
 * Interface pour écouter les évènements sur le nom du diplome
 */

    public interface Clickable {
        void onClickName(int position);
        Context getContext();

}
