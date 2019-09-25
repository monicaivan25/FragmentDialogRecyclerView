package com.example.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {

    private List<Car> carList;
    private RecyclerViewClickListener mListener;

    public interface RecyclerViewClickListener {
        public void recyclerViewListClicked(int position);
    }

    public void setOnItemClickListener(RecyclerViewClickListener listener) {
        mListener = listener;
    }

    CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_view, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.brand.setText(carList.get(position).getBrand());
        holder.model.setText(carList.get(position).getModel());
        holder.year.setText(String.valueOf(carList.get(position).getYear()));


    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    Car getCar(int position) {
        return carList.get(position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView brand;
        TextView model;
        TextView year;


        MyViewHolder(@NonNull View itemView, final RecyclerViewClickListener listener) {
            super(itemView);
            brand = itemView.findViewById(R.id.brand);
            model = itemView.findViewById(R.id.model);
            year = itemView.findViewById(R.id.year);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.recyclerViewListClicked(position);
                        }
                    }
                }
            });
        }
    }
}