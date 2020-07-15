/*
 * Created by Dmitry Garmyshev on 15.07.20 21:54
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 15.07.20 21:54
 *
 */

/*
Адаптер для отображения списка адресов

Используются все самые обычные инструменты при работе с адаптерами
 */

package com.example.vladlinkexam.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vladlinkexam.R;
import com.example.vladlinkexam.model.accounts.subclasses.MAddress;

import java.util.List;

public class AdapterAddresses extends RecyclerView.Adapter<AdapterAddresses.ViewHolder> {


    private LayoutInflater lInflater;
    private List<MAddress> mData;


    public AdapterAddresses(Context context, List<MAddress> addresses){
        this.lInflater = lInflater.from(context);
        this.mData = addresses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = lInflater.inflate(R.layout.element_address, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MAddress lAddress = mData.get(position);

        holder.tvId.setText(lAddress.getDid());
        holder.tvDescr.setText(lAddress.getDescr());
        holder.tvFloor.setText(lAddress.getFloor());
        holder.tvPorch.setText(lAddress.getPorch());
        holder.tvDomName.setText(lAddress.getDomName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvId,
                tvDescr,
                tvFloor,
                tvPorch,
                tvDomName;

        public ViewHolder(final View itemView){
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIAddressId);
            tvDescr = itemView.findViewById(R.id.tvIAddressDescr);
            tvFloor = itemView.findViewById(R.id.tvIAddressFloor);
            tvPorch = itemView.findViewById(R.id.tvIAddressPorch);
            tvDomName = itemView.findViewById(R.id.tvIAddressDomName);
        }
    }
}
