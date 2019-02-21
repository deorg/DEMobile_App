package com.domestic.dmobile.dbms.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.domestic.dmobile.dbms.model.Sms;
import com.domestic.dmobile.dbms.model.Sms.MarkRead;
import com.domestic.dmobile.dbms.model.SmsNotif;
import com.domestic.dmobile.dbms.view.SmsItem;
import com.domestic.dmobile.dbms.view.SmsSenderItem;
import com.domestic.dmobile.manager.http.HttpManager;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;

public class SmsAdapter extends BaseAdapter {

    private Sms dao;
    private static int maxPK = 0;

    public void setDao(Sms dao) {
        this.dao = dao;
        getMaxPK();
    }

    public void getMaxPK() {
        maxPK = 0;
        for (Sms.Data d: this.dao.getData()) {
            if (!d.getSenderType().equals("CUST") && d.getReadStatus().equals("UNREAD")) maxPK = d.getSms010PK();
        }
    }

    public void addDao(Sms.ReceiveSms.Response newData) {
        Sms.Data data = new Sms().new Data();
        data.setSms010PK(newData.getSms010PK());
        data.setCustNo(newData.getCustNo());
        data.setConNo(newData.getConNo());
        data.setSmsNote(newData.getSmsNote());
        data.setSmsTime(newData.getSmsTime());
        data.setSender(newData.getSender());
        data.setSenderType(newData.getSenderType());
        data.setReadStatus(newData.getReadStatus());
        dao.getData().add(data);
        getMaxPK();
    }

    public void addDao(SmsNotif newData) {
        Sms.Data data = new Sms().new Data();
        data.setSms010PK(newData.getSms010PK());
        data.setCustNo(newData.getCustNo());
        data.setConNo(newData.getConNo());
        data.setSmsNote(newData.getSmsNote());
        data.setSmsTime(newData.getSmsTime());
        data.setSender(newData.getSender());
        data.setSenderType(newData.getSenderType());
        data.setReadStatus(newData.getReadStatus());
        dao.getData().add(data);
        getMaxPK();
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SmsItem itemSms;
        SmsSenderItem itemSender;

        final Sms d = this.dao;
        final Sms.Data dao = (Sms.Data) getItem(position);
//        Log.v("ake", "SenderType : " + dao.getSenderType());
//        Log.v("ake", "Check Read : position = " +  position + " getCount:" + getCount());
//        Log.v("ake", "maxPK:" + maxPK + "  sms010PK:" + dao.getSms010PK());





        //if (position == getCount()-1 && dao.getReadStatus().equals("UNREAD")) {
        if (maxPK == dao.getSms010PK()) {

            Sms.MarkRead markRead = new Sms().new MarkRead();
            markRead.setCustNo(dao.getCustNo());
            markRead.setSms010PK(dao.getSms010PK());

            Call<Sms.MarkRead> call = HttpManager.getInstance(HttpManager.MODE_CUSTOMER)
                    .getService()
                    .postMarkRead(markRead);
            call.enqueue(new Callback<MarkRead>() {
                @Override
                public void onResponse(Call<MarkRead> call, retrofit2.Response<MarkRead> response) {

                }

                @Override
                public void onFailure(Call<MarkRead> call, Throwable t) {

                }
            });
//            call.enqueue(new Callback<Sms.MarkRead>() {
//                @Override
//                public void onResponse(Call<Sms.MarkRead> call, Response<Sms.MarkRead> response) {
//                    if (response.isSuccessful()) {
//                        //Log.v("ake", "Update Read Success");
//                        dao.setReadStatus("READ");
//                        for (Sms.Data d: d.getData()) {
//                            if (!d.getSenderType().equals("CUST") && d.getReadStatus().equals("UNREAD")) d.setReadStatus("READ");
//                        }
//
//                        getMaxPK();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Sms.MarkRead> call, Throwable t) {
//
//                }
//            });
        }// else Log.v("ake", "Readed");

        if (dao.getSenderType().equals("CUST")) {
            //Log.v("ake", "SenderType In : " + dao.getSenderType());
            if (convertView != null && convertView instanceof SmsSenderItem) itemSender = (SmsSenderItem) convertView;
            else itemSender = new SmsSenderItem(parent.getContext());

            //TODO Set Binding View
            itemSender.setTvSmsNote(dao.getSmsNote());
            itemSender.setTvSmsTime(dao.getSmsTime());

            return itemSender;

        } else {
            if (convertView != null && convertView instanceof SmsItem) itemSms = (SmsItem) convertView;
            else itemSms = new SmsItem(parent.getContext());

            //TODO Set Binding View
            itemSms.setTvConNo(dao.getConNo());
            itemSms.setTvSmsNote(dao.getSmsNote());
            itemSms.setTvSmsTime(dao.getSmsTime());
            itemSms.setTvSms010PK(dao.getSms010PK());

            String dd, ds;
            if (position != 0) {
                dd = new SimpleDateFormat("d MMM yy").format(this.dao.getData().get(position-1).getSmsTime());
                ds = new SimpleDateFormat("d MMM yy").format(dao.getSmsTime());

//                Log.v("ake", "dd = " + dd);
//                Log.v("ake", "ds = " + ds);
                if (!dd.equals(ds)) itemSms.setTvDayGroup(dao.getSmsTime());
                else itemSms.setTvDayGroupVisible();
            } else itemSms.setTvDayGroup(dao.getSmsTime());

            return itemSms;
        }
    }
}
