package com.example.autenticazione;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.android.gms.common.api.Response;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


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
        Log.i("CREATION", "ciaooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_requests_to_me2, container, false);
        ListView listRequestsToMe;
        listRequestsToMe= (ListView)rootView.findViewById(R.id.listRequestsToMe);
        Reservation[] reservations = new Reservation[2];
        //collegamento a web server
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL("https://park-platform-dot-parziale-lcsdep.nw.r.appspot.com/api/v1/users/allMarker");

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                System.out.print(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }



        Reservation reservation = new Reservation ("11:10","Giuseppe", "AT060EK", 3, "Audi", "Rosso", "con ammaccatura");
        Reservation reservation_2 = new Reservation("18:30", "Maria", "LS328GE", 2, "Cinquecento", "Rosa", "sono in ritardo");
        reservations[0]= reservation;
        reservations[1]= reservation_2;
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), R.layout.j, reservations);
        listRequestsToMe.setAdapter(customAdapter);
        return rootView;
    }

}
