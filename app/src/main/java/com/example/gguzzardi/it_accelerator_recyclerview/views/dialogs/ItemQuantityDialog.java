package com.example.gguzzardi.it_accelerator_recyclerview.views.dialogs;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gguzzardi.it_accelerator_recyclerview.R;
import com.mercadolibre.android.ui.widgets.MeliDialog;

public class ItemQuantityDialog extends MeliDialog {

    public static final String STATE_AVAILABLE_ITEMS = "state_available_items";

    public interface QuantityDialogListener {
        void onQuantityClicked(int quantity);
    }


    @Override
    public int getContentView() {
        return R.layout.dialog_cantidad;
    }


    @Override
    public void onStart() {
        super.onStart();

        setAvailableQuantity();
        setUpListeners();
    }

    private void setAvailableQuantity() {
        Bundle args = getArguments();

        int availableQuantity = args.getInt(STATE_AVAILABLE_ITEMS, 1);
        String availableQuantString = String.format(getResources().getString(R.string.format_available_units), availableQuantity);

        final TextView quantityTextview = getView().findViewById(R.id.tv_availability);
        quantityTextview.setText(availableQuantString);;
    }

    private void setUpListeners() {
        View view = getView();
        QuantityDialogListener listener = (QuantityDialogListener) getActivity();

        TextView tvOneUnit = view.findViewById(R.id.tv_unidades_uno);
        tvOneUnit.setOnClickListener(v ->  {
            listener.onQuantityClicked(1);
            dismiss();
        });

        TextView tvTwoUnit = view.findViewById(R.id.tv_unidades_dos);
        tvTwoUnit.setOnClickListener(v ->  {
            listener.onQuantityClicked(2);
            dismiss();
        });


        TextView tvThreeUnit = view.findViewById(R.id.tv_unidades_tres);
        tvThreeUnit.setOnClickListener(v ->  {
            listener.onQuantityClicked(3);
            dismiss();
        });

        TextView tvFourUnit = view.findViewById(R.id.tv_unidades_cuatro);
        tvFourUnit.setOnClickListener(v ->  {
            listener.onQuantityClicked(4);
            dismiss();
        });


        TextView tvFiveUnit = view.findViewById(R.id.tv_unidades_cinco);
        tvFiveUnit.setOnClickListener(v ->  {
            listener.onQuantityClicked(5);
            dismiss();
        });

    }
}
