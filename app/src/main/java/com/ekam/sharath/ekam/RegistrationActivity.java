package com.ekam.sharath.ekam;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import util.UiHelper;
import util.ValidationHelper;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.edtFirstName)
    EditText edtFirstName;
    @BindView(R.id.txtInpLayFirstName)
    TextInputLayout txtInpLayFirstName;
    @BindView(R.id.edtLastName)
    EditText edtLastName;
    @BindView(R.id.txtInpLayLastName)
    TextInputLayout txtInpLayLastName;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.txtInpLayEmail)
    TextInputLayout txtInpLayEmail;
    @BindView(R.id.edtCity)
    EditText edtCity;
    @BindView(R.id.txtInpLayCity)
    TextInputLayout txtInpLayCity;
    @BindView(R.id.edtCountry)
    EditText edtCountry;
    @BindView(R.id.txtInpLayCountry)
    TextInputLayout txtInpLayCountry;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    private final String TAG = RegistrationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

    }


    @OnClick(R.id.btnRegister)
    public void onViewClicked() {
        boolean isValid = true;

        String firstName = edtFirstName.getText().toString().trim();
        String lastName = edtLastName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String city = edtCity.getText().toString().trim();
        String country = edtCountry.getText().toString().trim();

        if (TextUtils.isEmpty(firstName)) {
            isValid = false;
            UiHelper.requestFocus(txtInpLayFirstName,this);
            txtInpLayFirstName.setError(getString(R.string.err_msg_first_name));
        }else{
            txtInpLayFirstName.setErrorEnabled(false);
        }

        if (ValidationHelper.isValidEmail(email)) {
            txtInpLayEmail.setErrorEnabled(false);
        }else{
            isValid = false;
            UiHelper.requestFocus(txtInpLayEmail,this);
            txtInpLayEmail.setError(getString(R.string.err_msg_email));
        }

        if (TextUtils.isEmpty(city)) {
            isValid = false;
            UiHelper.requestFocus(txtInpLayCity,this);
            txtInpLayCity.setError(getString(R.string.err_msg_city));
        }else{
            txtInpLayCity.setErrorEnabled(false);
        }

        if (TextUtils.isEmpty(country)) {
            isValid = false;
            UiHelper.requestFocus(txtInpLayCountry,this);
            txtInpLayCountry.setError(getString(R.string.err_msg_country));
        }else{
            txtInpLayCountry.setErrorEnabled(false);
        }

        if(isValid){
            Toast.makeText(this, "Valid form", Toast.LENGTH_SHORT).show();
        }
    }

}
