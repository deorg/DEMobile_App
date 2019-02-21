package com.domestic.dmobile.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.domestic.dmobile.R;
import com.domestic.dmobile.activity.MainActivity;
import com.domestic.dmobile.dbms.adapter.BankAdapter;
import com.domestic.dmobile.dbms.model.Bank;
import com.domestic.dmobile.dbms.model.Contract;
import com.domestic.dmobile.dbms.model.Order;
import com.domestic.dmobile.dbms.model.Request;
import com.domestic.dmobile.dbms.model.User;
import com.domestic.dmobile.manager.http.HttpManager;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentFragment extends Fragment {

    private GridView gridView;
    //TODO Set Adapter Template
    private BankAdapter adapter;
    private static Contract.Data contract;
    private TextView tvConNo, tvBalAmt, tvDiscAmt, tvPatAmt;
    private User user;

    public static PaymentFragment newInstance(Contract.Data dao) {
        contract = dao;

        PaymentFragment fragment = new PaymentFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //TODO Set FragmentTemplate
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        initInstance(view);
        initListener();
        return view;

    }

    //region #----- Initial ----------
    private void initInstance(View view) {
        tvConNo = view.findViewById(R.id.tvConNo);
        tvBalAmt = view.findViewById(R.id.tvBalAmt);
        tvDiscAmt = view.findViewById(R.id.tvDiscAmt);
        tvPatAmt = view.findViewById(R.id.tvPayAmt);

        gridView = view.findViewById(R.id.gridView);
        adapter = new BankAdapter();
        gridView.setAdapter(adapter);

        tvConNo.setText(contract.getConNo());
        tvBalAmt.setText(String.valueOf(contract.getBalAmt()));
        tvDiscAmt.setText(String.valueOf(contract.getDiscAmt()));
        tvPatAmt.setText(String.valueOf(contract.getPayAmt()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Log.v("ake", "condate : " + dateFormat.format(contract.getConDate()));
    }
    //endregion


    //region #----- Listener ----------
    private void initListener() {
        setOnListener();
        //TODO Set initListener Template
    }
    //---------------------------------
    private void setOnListener() {
        //TODO Set setOnListener Template
        final Call<Bank> call = HttpManager.getInstance(HttpManager.MODE_PAYMENT).getService().getBank();
        call.enqueue(new BankLoadCallback());



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bank.Data dao = (Bank.Data) gridView.getItemAtPosition(position);
                Log.v("ake", "setOnItemClickListener - Bank Id: " + dao.getChannelId());
                Log.v("ake", "setOnItemClickListener - Bank Name: " + dao.getChannelName());

//                User user = MainActivity.user;
//                String deviceID = Settings.Secure.getString(
//                        getActivity().getContentResolver(),
//                        Settings.Secure.ANDROID_ID);


                Request req = new Request();
                req.setCustomerId(String.valueOf(contract.getCustNo()));
                req.setContractNo(contract.getConNo());
                req.setDeviceId(user.getDeviceId());
                req.setAmount(Integer.valueOf(tvPatAmt.getText().toString() + "00"));
                req.setPhoneNumber(user.getPhoneNo());
                req.setDescription("Test : " + dao.getChannelId());
                req.setChannelCode(dao.getChannelId());

                Call<Order> call = HttpManager.getInstance(HttpManager.MODE_PAYMENT).getService().postOrder(req);
                call.enqueue(new OrderLoadCallback());
            }
        });
    }
    //endregion


    //region #----- MyMethod ----------
    //TODO Set MyMethod Template

    private class BankLoadCallback implements Callback<Bank> {

        @Override
        public void onResponse(Call<Bank> call, Response<Bank> response) {
            if (response.isSuccessful()) {
                Bank dao = response.body();
                adapter.setDao(dao);
                adapter.notifyDataSetChanged();
                Log.v("ake", "PaymentFragment onResponse Successful");
            } else Log.v("ake", "PaymentFragment onResponse Not Successful");
        }

        @Override
        public void onFailure(Call<Bank> call, Throwable t) {
            Log.v("ake", "PaymentFragment onFailure" + t.getMessage());
        }
    }

    private class OrderLoadCallback implements Callback<Order> {
        @Override
        public void onResponse(Call<Order> call, Response<Order> response) {
            if (response.isSuccessful()) {
                Order dao = response.body();

                Log.v("ake", "PaymentFragment onResponse : Success / " + dao.getData().getPaymentUrl());

                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contentContrainer, InternetBankingFragment.newInstance(dao));
                ft.addToBackStack(null);
                ft.commit();
            } else Log.v("ake", "PaymentFragment onResponse : false / " + response.message());
        }

        @Override
        public void onFailure(Call<Order> call, Throwable t) {
            Log.v("ake", "PaymentFragment onFailure" + t.getMessage());
        }
    }




    protected void clearStack() {
        int backStackEntry = getActivity().getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntry > 0) {
            for (int i = 0; i < backStackEntry; i++) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        }

        //Here we are removing all the fragment that are shown here
        if (getActivity().getSupportFragmentManager().getFragments() != null && getActivity().getSupportFragmentManager().getFragments().size() > 0) {
            for (int i = 0; i < getActivity().getSupportFragmentManager().getFragments().size(); i++) {
                Fragment mFragment = getActivity().getSupportFragmentManager().getFragments().get(i);
                if (mFragment != null) {
                    getActivity().getSupportFragmentManager().beginTransaction().remove(mFragment).commit();
                }
            }
        }
    }


    //endregion
}
