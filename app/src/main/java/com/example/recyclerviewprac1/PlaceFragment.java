package com.example.recyclerviewprac1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class PlaceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    // 默认的构造方法
    public PlaceFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PlaceFragment newInstance(String name, String description, Integer imageID) {
        PlaceFragment firstPlaceFragment = new PlaceFragment();
        Bundle args = new Bundle();
        args.putString("name",name);
        args.putString("description",description);
        args.putInt("image",imageID);
        firstPlaceFragment.setArguments(args);
        return firstPlaceFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView mTvTitle1 = view.findViewById(R.id.firstPlaceNameTextView);
        TextView mTvTitle2 = view.findViewById(R.id.firstPlaceDesTextView);
        ImageView mIvImage = view.findViewById(R.id.firstPlaceImageView);
        if(getArguments()!=null){
            mIvImage.setImageResource(getArguments().getInt("image"));
            mTvTitle1.setText(getArguments().getString("name"));
            mTvTitle2.setText(getArguments().getString("description"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_place_fragment, container, false);
    }
}