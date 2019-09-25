package com.example.demo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CarDialogFragment extends DialogFragment {

    private EditText carDetail1;
    private EditText carDetail2;
    private EditText carDetail3;
    private Button submitButton;
    private Button cancelButton;
    private OnDialogInputSubmittedListener mOnDialogInputSubmittedListener;

    public interface OnDialogInputSubmittedListener {
        void getDialogInput(String detail1, String detail2, String detail3);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.car_dialog_fragment, container, false);

        initUI(view);
        return view;
    }

    private void initUI(View view) {
        carDetail1 = view.findViewById(R.id.car_detail_1);
        carDetail2 = view.findViewById(R.id.car_detail_2);
        carDetail3 = view.findViewById(R.id.car_detail_3);
        submitButton = view.findViewById(R.id.submit_button);
        cancelButton = view.findViewById(R.id.cancel_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String detail1 = carDetail1.getText().toString();
                String detail2 = carDetail2.getText().toString();
                String detail3 = carDetail3.getText().toString();
                if(!detail1.equals("") && !detail2.equals("") && !detail3.equals("")){
                    mOnDialogInputSubmittedListener.getDialogInput(detail1, detail2, detail3);
                    getDialog().dismiss();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnDialogInputSubmittedListener = (OnDialogInputSubmittedListener) getTargetFragment();
        }catch (ClassCastException e){}
    }
}
