package com.domestic.dmobile.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.domestic.dmobile.MainApplication;
import com.domestic.dmobile.R;
import com.domestic.dmobile.dbms.model.Customer;
import com.domestic.dmobile.dbms.model.User;
import com.domestic.dmobile.fragment.ContractFragment;
import com.domestic.dmobile.fragment.SmsFragment;
import com.domestic.dmobile.manager.Config;
import com.domestic.dmobile.manager.Permissions;
import com.domestic.dmobile.manager.http.HttpManager;
import com.domestic.dmobile.manager.service.BoundService;
import com.domestic.dmobile.manager.service.BoundService.MyBinder;
import com.domestic.dmobile.util.FragmentUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutBtnSms, layoutBtnContract;
    private View vMenuSelectedSms, vMenuSelectedContract, vMenuSelectedExit;
    private View vDividerSms, vDividerContract;

    private TextView tvCustName, btnClear, tvMenuSms, tvMenuContract, tvMenuExit;
    private ImageButton btnMenuSms, btnMenuContract, btnMenuExit;

    private View.OnClickListener buttonOnClick;

    private User mUser;
    private BoundService mBoundService;
    private Permissions permissions;
    private boolean mServiceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initInstance();
        initListener();

        //mUser.setPhoneNo("09302981661");
        //mUser.setDeviceId("9be58009076ae8a4");
        identifyUser();


//
//        dbHelper = new DBHelper(this);
//        dbHelper.getWritableDatabase();
//
//
//        if (dbHelper.isRegister()) {
//            permissions.check(this, this);
//            //user = dbHelper.getUser();
//
//            //region Permit Enque
//            Call<Customer> call = HttpManager.getInstance(HttpManager.MODE_REGISTER)
//                    .getService().getAuthen(user.getPhoneNo(), user.getDeviceId());
//            call.enqueue(new Callback<Customer>() {
//                @Override
//                public void onResponse(Call<Customer> call, Response<Customer> response) {
//                    if (response.isSuccessful())
//                        if (response.body().getData() != null && response.body().getData().getPermit() != null) {
//                            user.setPermit(response.body().getData().getPermit());
//                            if (response.body().getData().getPermit().equals("SMS")) {
//                                layoutBtnContract.setVisibility(View.GONE);
//                            } else if (response.body().getData().getPermit().equals("PAYMENT")) {
//                                layoutBtnSms.setVisibility(View.GONE);
//                            } else if (response.body().getData().getPermit().equals("SUSPEND")) {
//                                layoutBtnSms.setVisibility(View.GONE);
//                                layoutBtnContract.setVisibility(View.GONE);
//                            } else {
//                                layoutBtnSms.setVisibility(View.GONE);
//                                layoutBtnContract.setVisibility(View.GONE);
//                            }
//                        } else {
//                            layoutBtnSms.setVisibility(View.GONE);
//                            layoutBtnContract.setVisibility(View.GONE);
//                        }
//                }
//
//                @Override
//                public void onFailure(Call<Customer> call, Throwable t) {
//
//                }
//            });
//            //endregion
//
//
//
//            tvCustNo.setText(String.valueOf(user.getCustNo()));
//            tvCustName.setText(user.getCustName());
//
//            //authentication.checkAuthen(this, user);
//
//            //if (user.getPermit().equals("SMS")) btnMenuContract.setVisibility(View.GONE);
//            Log.v("ake", "Permit : " + user.getPermit());
//
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.contentContrainer, SmsFragment.newInstance(user))
//                    .commit();
//        } else {
//            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContrainer, SmsFragment.newInstance())
                .commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.permissions.onResult(requestCode, permissions, grantResults);
    }

    //region #----- Initial ----------
    private void initInstance() {
        permissions = new Permissions();
        permissions.check(this, this);

        layoutBtnSms = findViewById(R.id.layoutBtnSms);
        layoutBtnContract = findViewById(R.id.layoutBtnContract);
        vMenuSelectedSms = findViewById(R.id.vMenuSelectedSms);
        vMenuSelectedContract = findViewById(R.id.vMenuSelectedContract);
        vMenuSelectedExit = findViewById(R.id.vMenuSelectedExit);
        vDividerSms = findViewById(R.id.vDividerSms);
        vDividerContract = findViewById(R.id.vDividerContract);
        tvCustName = findViewById(R.id.tvCustName);
        tvMenuSms = findViewById(R.id.tvMenuSms);
        tvMenuContract = findViewById(R.id.tvMenuContract);
        tvMenuExit = findViewById(R.id.tvMenuExit);

        //TODO Remove Button Clear
        btnClear = findViewById(R.id.btnClear);
        btnMenuSms = findViewById(R.id.btnMenuSms);
        btnMenuContract = findViewById(R.id.btnMenuContract);
        btnMenuExit = findViewById(R.id.btnMenuExit);
    }
    //endregion

    //region #----- Listener ----------
    private void initListener() {
        setOnListener();

        btnMenuSms.setOnClickListener(buttonOnClick);
        btnMenuContract.setOnClickListener(buttonOnClick);
        btnMenuExit.setOnClickListener(buttonOnClick);
        tvMenuSms.setOnClickListener(buttonOnClick);
        tvMenuContract.setOnClickListener(buttonOnClick);
        tvMenuExit.setOnClickListener(buttonOnClick);
        btnClear.setOnClickListener(buttonOnClick);
    }
    //---------------------------------
    private void setOnListener() {

        //TODO setonListener
        buttonOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tvMenuSms:
                    case R.id.btnMenuSms:
                        if (mServiceBound) {
                            unbindService(mServiceConnection);
                            mServiceBound = false;
                        }
                        Intent intent = new Intent(MainActivity.this, BoundService.class);
                        stopService(intent);

                        setMenuSelected("SMS");
                        FragmentUtil.newInstance().removeBackStack(getSupportFragmentManager());
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contentContrainer, SmsFragment.newInstance())
                                .commit();
                        break;
                    case R.id.tvMenuContract:
                    case R.id.btnMenuContract:
                        setMenuSelected("CONTRACT");
                        FragmentUtil.newInstance().removeBackStack(getSupportFragmentManager());
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contentContrainer, ContractFragment.newInstance())
                                .commit();
                        break;
                    case R.id.tvMenuExit:
                    case R.id.btnMenuExit:
                        finish();
                        break;
                    case R.id.btnClear:
                        SharedPreferences sharedPreferences = getSharedPreferences("PREFS_NAME", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("FIRST_RUN", true);
                        editor.commit();
                        Log.v("ake", "clear appFirstRun : " + MainApplication.appFirstRun);
                        finish();
                        break;
                    default: break;
                }
            }
        };
    }
    //endregion



    //region #----- Run Background Service ----------

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, BoundService.class);
        startService(intent);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mServiceBound) {
            unbindService(mServiceConnection);
            mServiceBound = false;
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBinder myBinder = (MyBinder) service;
            mBoundService = myBinder.getService();
            mServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound = false;
        }
    };

    //endregion



    //region #----- Method ----------

    public void identifyUser() {

        this.mUser.setCustName("");
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS_NAME", 0);
        MainApplication.appFirstRun = sharedPreferences.getBoolean("FIRST_RUN", true);
        Log.v("ake", "appFirstRun : " + MainApplication.appFirstRun);
        if (MainApplication.appFirstRun) {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        } else {
            Call<Customer> call = HttpManager.getInstance(HttpManager.MODE_AUTHEN)
                    .getService()
                    .Identify(mUser.getSimSerial(), mUser.getDeviceId());
            call.enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Call<Customer> call, Response<Customer> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            Customer.Data customer = response.body().getData();
                            mUser.setCustNo(customer.getCustNo());
                            mUser.setCustName(customer.getCustName());
                            mUser.setCitizenNo(customer.getCitizenNo());
                            mUser.setPhoneNo(customer.getPhoneNo());
                            mUser.setPermit(customer.getPermit());
                            mUser.setDeviceStatus(customer.getDeviceStatus());
                            tvCustName.setText(mUser.getCustName());

                            switch (mUser.getPermit()) {
                                case "SMS":
                                    layoutBtnSms.setVisibility(View.VISIBLE);
                                    layoutBtnContract.setVisibility(View.GONE);
                                    vDividerContract.setVisibility(View.GONE);
                                    break;
                                case "BOTH":
                                    layoutBtnSms.setVisibility(View.VISIBLE);
                                    layoutBtnContract.setVisibility(View.VISIBLE);
                                    vDividerContract.setVisibility(View.VISIBLE);
                                    break;
                                default:
                                    layoutBtnSms.setVisibility(View.GONE);
                                    layoutBtnContract.setVisibility(View.GONE);
                                    vDividerSms.setVisibility(View.GONE);
                                    vDividerContract.setVisibility(View.GONE);
                                    break;
                            }

                            setMenuSelected("SMS");
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.contentContrainer, SmsFragment.newInstance())
                                    .commit();
                        } else {
                            //TODO Add Message Confirm Register newDevice Replace oldDevice
                            Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Customer> call, Throwable t) {
                    //TODO Add Message Alert
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void setMenuSelected(String menu) {
        switch (menu) {
            case "SMS":
                vMenuSelectedSms.setVisibility(View.VISIBLE);
                vMenuSelectedContract.setVisibility(View.GONE);
                vMenuSelectedExit.setVisibility(View.GONE);
                break;
            case "CONTRACT":
                vMenuSelectedSms.setVisibility(View.GONE);
                vMenuSelectedContract.setVisibility(View.VISIBLE);
                vMenuSelectedExit.setVisibility(View.GONE);
                break;
            default: break;
        }
    }

    //endregion

}
