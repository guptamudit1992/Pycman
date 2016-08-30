package com.example.parsler.pycman.Adapters;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.parsler.pycman.Commons.StringConstants;

public class ObjectHolder<X,Y> extends RecyclerView.ViewHolder {

    public ObjectHolder(ViewDataBinding itemView) {
        super(itemView.getRoot());
    }

    public void bindConnection(X object, Y _binding, String case_constant) {
        switch (case_constant) {

            case StringConstants.TAB_DASHBOARD_STEP_1:
                /*PickupCardBinding pickupCardBinding =
                        (PickupCardBinding) _binding;
                pickupCardBinding
                        .setPickupObject((PickupSummaryObject) object);*/
                break;

            case StringConstants.TAB_DASHBOARD_STEP_2:
                /*MessageCardBinding messageCardBinding =
                        (MessageCardBinding) _binding;
                messageCardBinding
                        .setMessageObject((PickupSummaryObject) object);*/
                break;

            default:
                Log.e(StringConstants.RESPONSE_ERROR,
                        StringConstants.OBJECT_HANDLER_NOT_FOUND);
                break;
        }
    }
}
