package com.example.dh_mercadoesclavo.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<ImagenFragment> fragmentList;


    public ViewPagerAdapter(@NonNull FragmentManager fm, List<ImagenFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return this.fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return this.fragmentList.size();
    }

    public List<ImagenFragment> getFragmentList() {
        return fragmentList;
    }

    public void setFragmentList(List<ImagenFragment> fragmentList) {
        this.fragmentList = fragmentList;
    }
}
