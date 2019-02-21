package com.domestic.dmobile.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.domestic.dmobile.R;
import com.domestic.dmobile.dbms.adapter.ContractAdapter;
import com.domestic.dmobile.dbms.model.Contract;
import com.domestic.dmobile.dbms.model.User;
import com.domestic.dmobile.manager.http.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContractFragment extends Fragment {

    private User mUser;
    private ListView listItem;
    private ContractAdapter adapter;

    public static ContractFragment newInstance() {
        ContractFragment fragment = new ContractFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contract, container, false);
        initInstance(view);
        initListener();
        return view;
    }

    //region #----- Initial ----------
    private void initInstance(View view) {
        listItem = view.findViewById(R.id.listItem);
        adapter = new ContractAdapter();
        listItem.setAdapter(adapter);
    }
    //endregion


    //region #----- Listener ----------
    private void initListener() {
        setOnListener();
        //TODO Set initListener Template
    }
    //---------------------------------
    private void setOnListener() {
        Call<Contract> call = HttpManager.getInstance(HttpManager.MODE_CUSTOMER).getService().getContract(mUser.getCustNo());
        call.enqueue(new ContractLoadCallback());
    }
    //endregion


    //region #----- MyMethod ----------
    class ContractLoadCallback implements Callback<Contract> {

        @Override
        public void onResponse(Call<Contract> call, Response<Contract> response) {
            if (response.isSuccessful()) {
                Contract dao = response.body();
                adapter.setDao(dao);
                adapter.notifyDataSetChanged();
                Log.v("ake", "ContractFragment - Successful");
            } else Log.v("ake", "ContractFragment - Not Successful");
        }

        @Override
        public void onFailure(Call<Contract> call, Throwable t) {
            Log.v("ake", "Error Sms");
        }
    }
    //endregion
}
