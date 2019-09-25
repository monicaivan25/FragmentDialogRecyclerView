package com.example.demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarListFragment extends Fragment
        implements CarDialogFragment.OnDialogInputSubmittedListener {

    private static final int REQUEST_CODE = 1;
    List<Car> carList = getCars();
    private RecyclerView recyclerView;
    private CarAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences= this.getActivity().getPreferences(Context.MODE_PRIVATE);

        View rootView = inflater.inflate(R.layout.car_list, container, false);
        recyclerView = rootView.findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CarAdapter(carList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new CarAdapter.RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(int position) {
                putCarInSharedPrefs(carList.get(position));
                openCarDetailsDialog();                
            }
            
        });
        return rootView;
    }

    private void openCarDetailsDialog() {
        CarDialogFragment carDialogFragment = new CarDialogFragment();
        carDialogFragment.setTargetFragment(CarListFragment.this, REQUEST_CODE);
        carDialogFragment.show(getFragmentManager(), "CarDialogFragment");
    }

    private void putCarInSharedPrefs(Car car) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.brand), car.getBrand());
        editor.putString(getString(R.string.model), car.getModel());
        editor.putInt(getString(R.string.year), car.getYear());
        editor.apply();
    }

    @Override
    public void getDialogInput(String detail1, String detail2, String detail3) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.detail1), detail1);
        editor.putString(getString(R.string.detail2), detail2);
        editor.putString(getString(R.string.detail3), detail3);
        editor.apply();

        CarFragment carFragment = new CarFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, carFragment, "CarFragment")
                .addToBackStack(null)
                .commit();
    }

    private List<Car> getCars() {
        List<Car> carList = new ArrayList<>();

        List<String> brands = Arrays.asList("opel", "dacia", "bmw", "audi", "skoda");
        List<String> models = Arrays.asList("corsa", "logan", "ceva", "A5", "octavia");
        List<Integer> years = Arrays.asList(2007, 2010, 2005, 2015, 2016);
        for (int i = 0; i < 5; i++) {
            Car car = new Car();
            car.setBrand(brands.get(i));
            car.setModel(models.get(i));
            car.setYear(years.get(i));
            carList.add(car);
        }
        return carList;
    }
}
