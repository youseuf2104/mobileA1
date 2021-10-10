package com.example.emicalculator;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Math;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.emicalculator.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    //Create TextView elements for user inputted values (used for EMI calculations)
    TextView calculateEMI;
    TextView years;
    TextView interestRate;
    TextView mortgage;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_first,container,false);
        //Retrieve the user inputted text for the TextView elements
        calculateEMI = fragmentFirstLayout.findViewById(R.id.calculatedValue);
        years= fragmentFirstLayout.findViewById(R.id.numYears);
        interestRate= fragmentFirstLayout.findViewById(R.id.interestRate);
        mortgage = fragmentFirstLayout.findViewById(R.id.mortgageAmount);
        return fragmentFirstLayout;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //wait for the user to click the calculateButton button
        view.findViewById(R.id.calculateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call the calculateEMI function, to calculate and display the emi value to the user
                calculateEMI(view);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //Calculate and display the EMI
    private void calculateEMI(View view){
        //Convert the TextView text int double values, to be able to use for calculations
        //Must change some of the values, since calculating monthly EMI
        double mortgageNumber = Integer.parseInt(mortgage.getText().toString());
        double rateNumber = Double.parseDouble(interestRate.getText().toString())/(12*100.0);
        double yearsNumber = Integer.parseInt(years.getText().toString())*12;
        //Use formula to calculate EMI
        double calculatedEMI= (mortgageNumber *rateNumber*Math.pow(1+rateNumber,yearsNumber))/(Math.pow(1+rateNumber,yearsNumber)-1);
        //Round off to two decimal places (since using money)
        calculatedEMI = Math.round(calculatedEMI * 100.0) / 100.0;
        //Create the message to display on the GUI
        String emiMessage = ("Calculated Monthly EMI: $"+ calculatedEMI);
        //Set the message to the CalculateEMI EditText element (which is initially empty)
        calculateEMI.setText((emiMessage));
    }

}