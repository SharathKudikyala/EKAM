// Generated code from Butter Knife. Do not modify!
package com.ekam.sharath.ekam;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PhoneNoVerificationActivity_ViewBinding implements Unbinder {
  private PhoneNoVerificationActivity target;

  @UiThread
  public PhoneNoVerificationActivity_ViewBinding(PhoneNoVerificationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PhoneNoVerificationActivity_ViewBinding(PhoneNoVerificationActivity target, View source) {
    this.target = target;

    target.edtMobileNo = Utils.findRequiredViewAsType(source, R.id.edtMobileNo, "field 'edtMobileNo'", EditText.class);
    target.btnRegister = Utils.findRequiredViewAsType(source, R.id.btnRegister, "field 'btnRegister'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PhoneNoVerificationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edtMobileNo = null;
    target.btnRegister = null;
  }
}
