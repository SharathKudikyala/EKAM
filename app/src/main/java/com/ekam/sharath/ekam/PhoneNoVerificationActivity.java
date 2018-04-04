package com.ekam.sharath.ekam;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import util.AppConstants;
import util.SharedPrefHelper;
import util.UiHelper;

public class PhoneNoVerificationActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edtMobileNo)
    EditText edtMobileNo;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    private String mVerificationId;
    private FirebaseAuth mAuth;
    private Activity activity = PhoneNoVerificationActivity.this;
    private static String TAG = PhoneNoVerificationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_no_verification);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        btnRegister = findViewById(R.id.btnRegister);
        edtMobileNo = findViewById(R.id.edtMobileNo);

        btnRegister.setOnClickListener(this);
        if (mAuth.getCurrentUser() != null)
            UiHelper.showSnackbarMsg(activity, mAuth.getCurrentUser().getPhoneNumber());
    }

    private void showOTPPopUp() {
        final Dialog dialog = new Dialog(PhoneNoVerificationActivity.this);
        dialog.setContentView(R.layout.custom_dialog_otp);
        Button btnProceed = dialog.findViewById(R.id.btnProceed);
        final EditText edtOTP = dialog.findViewById(R.id.edtOTP);
        dialog.setTitle("OTP");
        dialog.setCancelable(false);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtOTP.setError(null);
                String otp = edtOTP.getText().toString().trim();
                if (TextUtils.isEmpty(otp) || otp.length() != 6) {
                    edtOTP.setError("Invalid OTP");
                    UiHelper.showSnackbarMsg(activity, "Invalid OTP");
                    return;
                }
                verifyPhoneNumberWithCode(mVerificationId, otp);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        mAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            UiHelper.showSnackbarMsg(activity, "Verification success!! You are in..");
                            FirebaseUser user = task.getResult().getUser();
                            SharedPrefHelper.setSharedPreferenceString(activity, AppConstants.PREF_MOBILE_NO, user.getPhoneNumber());
                            SharedPrefHelper.setSharedPreferenceString(activity, AppConstants.PREF_UID, user.getUid());
                            startActivity(new Intent(activity, RegistrationActivity.class));
                            finish();
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                UiHelper.showSnackbarMsg(activity, "The verification code entered was invalid");
                            }
                        }
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        UiHelper.hideSoftKeyboard(PhoneNoVerificationActivity.this);
        String phoneNo = edtMobileNo.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNo) || phoneNo.length() != 10) {
            UiHelper.showSnackbarMsg(activity, "Invalid phone number");
            return;
        }
        //edtMobileNo.setText("");
        UiHelper.showSnackbarMsg(activity, "Valid Phone number");
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                String.format("+91%s", phoneNo),        // Phone number to verify
                40,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Log.w(TAG, "onVerificationFailed", e);
                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            UiHelper.showSnackbarMsg(activity, "Invalid request");
                        } else if (e instanceof FirebaseTooManyRequestsException) {
                            UiHelper.showSnackbarMsg(activity, "The SMS quota for the project has been exceeded");
                        } else UiHelper.showSnackbarMsg(activity, e.getMessage());

                    }

                    @Override
                    public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        UiHelper.showSnackbarMsg(activity, "Verification code sent");
                        Log.d(TAG, "onCodeSent:" + verificationId);
                        mVerificationId = verificationId;
                    }

                    @Override
                    public void onCodeAutoRetrievalTimeOut(String verificationId) {
                        super.onCodeAutoRetrievalTimeOut(verificationId);
                        mVerificationId = verificationId;
                        UiHelper.showSnackbarMsg(activity, "AutoRetrieval Timeout, Please enter OTP.");
                        showOTPPopUp();
                    }
                });
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }
}
