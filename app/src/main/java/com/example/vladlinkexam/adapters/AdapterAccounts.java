/*
 * Created by Dmitry Garmyshev on 15.07.20 19:43
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 15.07.20 19:43
 *
 */

package com.example.vladlinkexam.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vladlinkexam.R;
import com.example.vladlinkexam.model.accounts.accountsList.MAccount;
import com.example.vladlinkexam.model.accounts.accountsList.MAccountsListData;

import java.util.List;

public class AdapterAccounts extends RecyclerView.Adapter<AdapterAccounts.ViewHolder> {

    //Список где будут храниться объекты для отображения
    private List<MAccount> mData;

    private LayoutInflater mInflater;

    private CallbackBtn callbackBtn;

    public AdapterAccounts(Context context, CallbackBtn callbackBtn, List<MAccount> data){

        this.mInflater = mInflater.from(context);
        this.callbackBtn = callbackBtn;
        this.mData = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.element_account, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MAccount lSingleAccount = mData.get(position);

        if(lSingleAccount != null){
            holder.tvLAccountULogin.setText(lSingleAccount.getuLogin());
            holder.tvLAccountFullName.setText(lSingleAccount.getFullName());
            holder.tvLAccountId.setText(Long.toString(lSingleAccount.getId()));
            holder.tvLAccountEmail.setText(lSingleAccount.getEmail());
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView tvLAccountId
                ,tvLAccountEmail
                ,tvLAccountFullName
                ,tvLAccountULogin;

        Button btnLAccountSelect;


        ViewHolder(final View itemView){
            super(itemView);

            this.tvLAccountId = itemView.findViewById(R.id.tvLAccountId);
            this.tvLAccountEmail = itemView.findViewById(R.id.tvLAccountEmail);
            this.tvLAccountFullName = itemView.findViewById(R.id.tvLAccountFullName);
            this.tvLAccountULogin = itemView.findViewById(R.id.tvLAccountULogin);

            this.btnLAccountSelect = itemView.findViewById(R.id.btnLAccountSelect);
            this.btnLAccountSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callbackBtn.selected(mData.get(getLayoutPosition()).getId());
                }
            });
        }
    }


    //Интерфейс для связи с основным фрагментом, где используется этот адаптер
    public interface CallbackBtn{
        void selected(long accountId);
    }
}
