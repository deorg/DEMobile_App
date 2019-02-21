package com.domestic.dmobile.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.domestic.dmobile.R;
import com.domestic.dmobile.dbms.DBHelper;
import com.domestic.dmobile.dbms.model.Customer;
import com.domestic.dmobile.dbms.model.User;
import com.domestic.dmobile.manager.Permissions;
import com.domestic.dmobile.manager.http.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtCitizenNo, edtPinCode, edtPhoneNo;
    private Button btnRegister, btnExit;
    private User mUser;
    private Permissions permissions;
    private View.OnClickListener buttonOnClick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initInstance();
        initListener();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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

        edtCitizenNo = findViewById(R.id.edtCitizenNo);
        edtPinCode = findViewById(R.id.edtPinCode);
        edtPhoneNo = findViewById(R.id.edtPhoneNo);
        btnRegister = findViewById(R.id.btnRegister);
        btnExit = findViewById(R.id.btnExit);

        edtPhoneNo.setText(mUser.getPhoneNo());
    }
    //endregion

    //region #----- Listener ----------
    private void initListener() {
        setOnListener();
        btnRegister.setOnClickListener(buttonOnClick);
        btnExit.setOnClickListener(buttonOnClick);
    }

    private void setOnListener() {
        buttonOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnRegister:

                        mUser.setCitizenNo(edtCitizenNo.getText().toString());
                        mUser.setPhoneNo(edtPhoneNo.getText().toString());
                        mUser.setPinCode(edtPinCode.getText().toString());

                        User.Register register = new User().new Register();
                        register.setCitizenNo(mUser.getCitizenNo());
                        register.setPhoneNo(mUser.getPhoneNo());
                        register.setSimPhoneNo(mUser.getSimPhoneNo());
                        register.setDeviceId(mUser.getDeviceId());
                        register.setSimSerial(mUser.getSimSerial());
                        register.setOperator(mUser.getOperator());
                        register.setBrand(mUser.getBrand());
                        register.setModel(mUser.getModel());
                        register.setApiVersion(mUser.getApiVersion());
                        register.setPinCode(mUser.getPinCode());


                        //TODO Add Loading
                        Call<User.Register.Response> call = HttpManager.getInstance(HttpManager.MODE_AUTHEN)
                                .getService()
                                .Register(register);
                        call.enqueue(new RegisterLoadCallback());

                        break;
                    case R.id.btnExit:
                        finish();
                        break;
                    default: break;
                }
            }
        };
    }
    //endregion


    //region #----- Method ----------
    private class RegisterLoadCallback implements Callback<User.Register.Response> {

        @Override
        public void onResponse(Call<User.Register.Response> call, Response<User.Register.Response> response) {
            if (response.isSuccessful()) {
                if (response.body().getCode() == 200) {
                    mUser.setCustNo(response.body().getData().getCustNo());
                    mUser.setCustName(response.body().getData().getCustName());

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                    //Mark Status - Run App First Time
                    SharedPreferences sharedPreferences = getSharedPreferences("PREFS_NAME", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("FIRST_RUN", false);
                    editor.commit();

                } else {
                    //TODO Register Code != 200
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                //TODO Register notSuccess
                Toast.makeText(RegisterActivity.this, response.message(), Toast.LENGTH_LONG).show();;
            }
        }

        @Override
        public void onFailure(Call<User.Register.Response> call, Throwable t) {
            //TODO Register onFailure
            Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();;
        }
    }
    //endregion
}
