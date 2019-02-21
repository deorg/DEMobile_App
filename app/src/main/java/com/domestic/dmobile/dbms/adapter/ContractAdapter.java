package com.domestic.dmobile.dbms.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.domestic.dmobile.dbms.model.Contract;
import com.domestic.dmobile.dbms.view.ContractItem;

public class ContractAdapter extends BaseAdapter {
    //TODO Set TemplateDao Template
    Contract dao;

    public void setDao(Contract dao) {
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
        ContractItem item;
        if (convertView != null) item = (ContractItem) convertView;
        else item = new ContractItem(parent.getContext());

        Contract.Data dao = (Contract.Data) getItem(position);

        //TODO Set Binding View Template
        item.setTvConNo(dao.getConNo());
        item.setTvCustNo(dao.getCustNo());
        item.setTvConDate(dao.getConDate());
        item.setTvTotAmt(dao.getTotAmt());
        item.setTvPayAmt(dao.getPayAmt());
        item.setTvPeriod(dao.getPeriod());
        item.setTvBalAmt(dao.getBalAmt());
        item.setTvDiscAmt(dao.getDiscAmt());

        return item;
    }
}
