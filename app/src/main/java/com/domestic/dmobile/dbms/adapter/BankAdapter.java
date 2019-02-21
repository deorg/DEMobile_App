package com.domestic.dmobile.dbms.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.domestic.dmobile.dbms.model.Bank;
import com.domestic.dmobile.dbms.view.BankItem;

import java.util.List;

public class BankAdapter extends BaseAdapter {
    //TODO Set TemplateDao Template
    Bank dao;

    public void setDao(Bank dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {
        if (dao == null) return 0;
        if (dao.getData() == null) return 0;
        return dao.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return dao.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //TODO Set TemplateItem Template
        BankItem item;
        if (convertView != null) item = (BankItem) convertView;
        else item = new BankItem(parent.getContext());

        Bank.Data dao = (Bank.Data) getItem(position);

        //TODO Set Binding View Template
        item.setTvChannelId(dao.getChannelId());
        item.setTvChannelName(dao.getChannelName());
        item.setIbtnbank(dao.getChannelImg());

        return item;
    }
}
