package com.example.recyclerviewprac1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements placeToGoRecyclerViewAdapter.OnRowClickListener {
    private TextView mTvTitle1, mTvTitle2;

    RecyclerView topDestinationRecyclerView;
    topDestinationRecyclerViewAdapter topDestinationRecyclerViewAdapter;
    List<topDestination> topDestinationList = new ArrayList<>();

    RecyclerView placeToGoRecyclerView;
    com.example.recyclerviewprac1.placeToGoRecyclerViewAdapter placeToGoRecyclerViewAdapter;
    List<placeToGo> placeToGoList = new ArrayList<>();

    Integer[] imageList = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};

    Integer[] imageListPlaceToGo = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};
    String[] nameList = {"Big Ben", "London Eye"};
    String[] descriptionList = {"Big Ben is the nickname for the Great Bell of the striking clock at the north end of the Palace of Westminster;[1] the name is frequently extended to refer to both the clock and the clock tower.[2] The official name of the tower in which Big Ben is located was originally the Clock Tower; it was renamed Elizabeth Tower in 2012 to mark the Diamond Jubilee of Elizabeth II, Queen of the United Kingdom.",
            "The London Eye, or the Millennium Wheel, is a cantilevered observation wheel on the South Bank of the River Thames in London. It is Europe's tallest cantilevered observation wheel,[14] and is the most popular paid tourist attraction in the United Kingdom with over 3 million visitors annually.[15] It has made many appearances in popular culture."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvTitle1 = findViewById(R.id.tv_title1);
        mTvTitle2 = findViewById(R.id.tv_title2);

        // Top destination part
        topDestinationRecyclerView = findViewById(R.id.rv_horizontal);
        topDestinationRecyclerViewAdapter = new topDestinationRecyclerViewAdapter(topDestinationList, MainActivity.this);
        topDestinationRecyclerView.setAdapter(topDestinationRecyclerViewAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        topDestinationRecyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < imageList.length; i++) {
            topDestination topDestination = new topDestination(i, imageList[i]);
            topDestinationList.add(topDestination);
        }

        placeToGoRecyclerView = findViewById(R.id.rv_vertical);
        placeToGoRecyclerViewAdapter = new placeToGoRecyclerViewAdapter(placeToGoList, MainActivity.this, this);
        placeToGoRecyclerView.setAdapter(placeToGoRecyclerViewAdapter);

        RecyclerView.LayoutManager layoutManagerPlaceToGo = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        placeToGoRecyclerView.setLayoutManager(layoutManagerPlaceToGo);

        for (int i = 0; i < imageListPlaceToGo.length; i++) {
            placeToGo placeToGo = new placeToGo(i, imageListPlaceToGo[i], nameList[i], descriptionList[i]);
            placeToGoList.add(placeToGo);
        }
    }

    @Override
    public void onItemClick(int position) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragment = PlaceFragment.newInstance(nameList[position],descriptionList[position],imageListPlaceToGo[position]);
        fragmentTransaction.add(R.id.firstFrameLayout1, fragment).commitAllowingStateLoss();

        placeToGoRecyclerView.setVisibility(View.INVISIBLE);
        topDestinationRecyclerView.setVisibility(View.INVISIBLE);
        mTvTitle1.setVisibility(View.INVISIBLE);
        mTvTitle2.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (placeToGoRecyclerView.getVisibility() == View.INVISIBLE) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragmentManager.getFragments().get(0));
            fragmentTransaction.commit();
            placeToGoRecyclerView.setVisibility(View.VISIBLE);
            topDestinationRecyclerView.setVisibility(View.VISIBLE);
            mTvTitle1.setVisibility(View.VISIBLE);
            mTvTitle2.setVisibility(View.VISIBLE);
        }
    }
}