package com.example.appmedicina.Controlador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerController extends FragmentPagerAdapter {
    int numTabs;

    public PagerController(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numTabs = behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Medicamentos();
            case 1:
                return new Sintomas();
            case 2:
                return new Farmacias();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
