package com.azissulaiman.eder;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView, recyclerViewNewest;
    private Adapter adapter;
    private ArrayList<Buku> bukuArrayList;

    LinearLayoutManager HorizontalLayout, HorizontalLayoutNewest;

    public ExploreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExploreFragment newInstance(String param1, String param2) {
        ExploreFragment fragment = new ExploreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

//    @Override
    public void onClick(View v, int position) {
        Intent i = new Intent(v.getContext(), Map.class);
        startActivity(i);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_explore, container, false);

        addData();
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        recyclerViewNewest = (RecyclerView) v.findViewById(R.id.recyclerviewNewest);

        adapter = new Adapter(bukuArrayList);

        // Set Horizontal Layout Manager
        // for Recycler view
        HorizontalLayout
                = new LinearLayoutManager(
                this.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);

        HorizontalLayoutNewest
                = new LinearLayoutManager(
                this.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);

        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerViewNewest.setLayoutManager(HorizontalLayoutNewest);

        recyclerView.setAdapter(adapter);
        recyclerViewNewest.setAdapter(adapter);
        adapter.setClickListener(this);
        return v;
    }

    void addData() {
        bukuArrayList = new ArrayList<>();
        bukuArrayList.add(new Buku(R.drawable.superjitu_cover,"Superjitu Lolos Tes CPNS", "Tim B First", "4.3"));
        bukuArrayList.add(new Buku(R.drawable.cover_pai,"Pendidikan Agama Islam", "Dr. Mardani", "4.2"));
        bukuArrayList.add(new Buku(R.drawable.cover_parasit,"Parasit Jatuh Cinta", "Khaira Salsabila", "4.5"));
        bukuArrayList.add(new Buku(R.drawable.cover_anxiety,"Anxiety", "Wayne Reese", "4.8"));
    }



}