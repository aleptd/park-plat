package com.example.autenticazione;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyAvailabilities extends Fragment {


    public FragmentMyAvailabilities() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_my_availabilities, container, false);
        ListView listMyAvailabilites;
        listMyAvailabilites= (ListView)rootView.findViewById(R.id.listMyAvailabilities);
        Availability[] availabilities = new Availability[2];
        Availability availability = new Availability ("13:10","ES453TO", "Giulietta", "Metallizzato");
        Availability availability1 = new Availability("21:30", "Sa204ET", "Giulietta", "Metallizzato");
        availabilities[0]= availability;
        availabilities[1]= availability1;
        CustomAdapterAvailability customAdapter = new CustomAdapterAvailability(getActivity(), R.layout.row_layout_availabilities, availabilities);
        listMyAvailabilites.setAdapter(customAdapter);
        return rootView;
    }



}
