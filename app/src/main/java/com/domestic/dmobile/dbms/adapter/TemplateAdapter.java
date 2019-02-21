package com.domestic.dmobile.dbms.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.domestic.dmobile.dbms.dao.TemplateDao;
import com.domestic.dmobile.dbms.view.TemplateItem;

import java.util.List;

public class TemplateAdapter extends BaseAdapter {
    //TODO Set TemplateDao Template
    List<TemplateDao> dao;

    public void setDao(List<TemplateDao> dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {
        if (dao == null) return 0;
        return dao.size();
    }

    @Override
    public Object getItem(int position) {
        return dao.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //TODO Set TemplateItem Template
        TemplateItem item;
        if (convertView != null) item = (TemplateItem) convertView;
        else item = new TemplateItem(parent.getContext());

        TemplateDao dao = (TemplateDao) getItem(position);

        //TODO Set Binding View Template
        item.setTempNo(dao.getTempNo());

        return item;
    }
}
