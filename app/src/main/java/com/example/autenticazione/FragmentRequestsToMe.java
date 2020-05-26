package com.example.autenticazione;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRequestsToMe extends Fragment {


    public FragmentRequestsToMe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_requests_to_me2, container, false);
        ListView listRequestsToMe;
        listRequestsToMe= (ListView)rootView.findViewById(R.id.listRequestsToMe);
        Reservation[] reservations = new Reservation[2];
        Reservation reservation = new Reservation ("11:10","Giuseppe", "AT060EK", 3, "Audi", "Rosso", "con ammaccatura");
        Reservation reservation_2 = new Reservation("18:30", "Maria", "LS328GE", 2, "Cinquecento", "Rosa", "sono in ritardo");
        reservations[0]= reservation;
        reservations[1]= reservation_2;
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), R.layout.j, reservations);
        listRequestsToMe.setAdapter(customAdapter);
        return rootView;
    }
}
