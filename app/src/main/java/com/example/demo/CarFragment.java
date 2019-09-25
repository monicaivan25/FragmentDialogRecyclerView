package com.example.demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CarFragment extends Fragment {

    TextView brand;
    TextView model;
    TextView year;
    TextView carDetail1;
    TextView carDetail2;
    TextView carDetail3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.car_fragment, container, false);
        initUI(view);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        brand.setText(sharedPref.getString(getString(R.string.brand), "brand"));
        model.setText(sharedPref.getString(getString(R.string.model), "model"));
        year.setText(String.valueOf(sharedPref.getInt(getString(R.string.year), 0)));
        carDetail1.setText(sharedPref.getString(getString(R.string.detail1), "detail1"));
        carDetail2.setText(sharedPref.getString(getString(R.string.detail2), "detail2"));
        carDetail3.setText(sharedPref.getString(getString(R.string.detail3), "detail3"));
        return view;
    }

    private void initUI(View view) {
        brand = view.findViewById(R.id.car_fragment_brand);
        model = view.findViewById(R.id.car_fragment_model);
        year = view.findViewById(R.id.car_fragment_year);
        carDetail1 = view.findViewById(R.id.car_fragment_car_detail_1);
        carDetail2 = view.findViewById(R.id.car_fragment_car_detail_2);
        carDetail3 = view.findViewById(R.id.car_fragment_car_detail_3);
    }
}
