package com.domestic.dmobile.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.domestic.dmobile.MainApplication;
import com.domestic.dmobile.R;
import com.domestic.dmobile.dbms.adapter.SmsAdapter;
import com.domestic.dmobile.dbms.model.Sms;
import com.domestic.dmobile.dbms.model.User;
import com.domestic.dmobile.manager.http.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmsFragment extends Fragment {

    private ProgressBar loadingSpinner;
    private EditText edtMessageBox;
    private ImageButton btnSendSms;

    public static ListView listItem;
    public static SmsAdapter adapter;

    private AdapterView.OnItemClickListener listItemClick;
    private View.OnClickListener buttonOnClick;

    private User mUser;


    public static SmsFragment newInstance() {
        SmsFragment fragment = new SmsFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sms, container, false);
        initInstance(view);
        initListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainApplication.fragmentSmsResumed();
    }

    @Override
    public void onPause() {
        super.onPause();
        MainApplication.fragmentSmsPaused();
    }



    //region #----- Initial ----------
    private void initInstance(View view) {
        loadingSpinner = view.findViewById(R.id.loadingSpinner);
        loadingSpinner.setVisibility(View.GONE);

        edtMessageBox = view.findViewById(R.id.edtMessageBox);
        btnSendSms = view.findViewById(R.id.btnSendSms);
        adapter = new SmsAdapter();
        listItem = view.findViewById(R.id.listItem);
        listItem.setAdapter(adapter);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    //endregion


    //region #----- Listener ----------
    private void initListener() {
        setOnListener();

        //TODO initListener
        listItem.setOnItemClickListener(listItemClick);
        btnSendSms.setOnClickListener(buttonOnClick);
    }

    //---------------------------------
    private void setOnListener() {
        loadingSpinner.setVisibility(View.VISIBLE);
        Call<Sms> call = HttpManager.getInstance(HttpManager.MODE_CUSTOMER).getService().getSms(mUser.getCustNo());
        call.enqueue(new SmsLoadCallback());

        listItemClick = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("ake", "position : " + position);

                Sms.Data dao = (Sms.Data) listItem.getItemAtPosition(position);
            }
        };

        buttonOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = edtMessageBox.getText().toString();
                if (!message.replace(" ", "").equals("")) {
                    Sms.SendSms sendSms = new Sms().new SendSms();
                    sendSms.setCustNo(mUser.getCustNo());
                    sendSms.setMessage(edtMessageBox.getText().toString());

                    Call<Sms.ReceiveSms> call = HttpManager.getInstance(HttpManager.MODE_CUSTOMER)
                            .getService()
                            .postSendMessage(sendSms);
                    call.enqueue(new SendMessageLoadCallback());
                }
            }
        };
    }
    //endregion


    //region #----- MyMethod ----------
    private class SmsLoadCallback implements Callback<Sms> {

        @Override
        public void onResponse(Call<Sms> call, Response<Sms> response) {
            if (response.isSuccessful()) {
                loadingSpinner.setVisibility(View.GONE);
                Sms dao = response.body();
                adapter.setDao(dao);
                adapter.notifyDataSetChanged();
                listItem.setSelection(adapter.getCount());
            } else Log.v("ake", "Not Successful");
        }

        @Override
        public void onFailure(Call<Sms> call, Throwable t) {
            Log.v("ake", "Error Sms");
        }
    }

    private class SendMessageLoadCallback implements Callback<Sms.ReceiveSms> {

        @Override
        public void onResponse(Call<Sms.ReceiveSms> call, Response<Sms.ReceiveSms> response) {
            if (response.isSuccessful()) {
                Log.v("ake", "SendMessage onResponse : true");
                Sms.ReceiveSms dao = response.body();
                adapter.addDao(dao.getData());
                adapter.getMaxPK();
                adapter.notifyDataSetChanged();
                listItem.setSelection(adapter.getCount());
                edtMessageBox.setText("");
            } else Log.v("ake", "SendMessage onResponse : false");
        }

        @Override
        public void onFailure(Call<Sms.ReceiveSms> call, Throwable t) {
            Log.v("ake", "SendMessage onFailure : " + t.getMessage());
        }
    }
    //endregion
}
