// Generated code from Butter Knife. Do not modify!
package com.ekam.sharath.ekam;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegistrationActivity_ViewBinding implements Unbinder {
  private RegistrationActivity target;

  private View view2131230759;

  @UiThread
  public RegistrationActivity_ViewBinding(RegistrationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegistrationActivity_ViewBinding(final RegistrationActivity target, View source) {
    this.target = target;

    View view;
    target.edtFirstName = Utils.findRequiredViewAsType(source, R.id.edtFirstName, "field 'edtFirstName'", EditText.class);
    target.txtInpLayFirstName = Utils.findRequiredViewAsType(source, R.id.txtInpLayFirstName, "field 'txtInpLayFirstName'", TextInputLayout.class);
    target.edtLastName = Utils.findRequiredViewAsType(source, R.id.edtLastName, "field 'edtLastName'", EditText.class);
    target.txtInpLayLastName = Utils.findRequiredViewAsType(source, R.id.txtInpLayLastName, "field 'txtInpLayLastName'", TextInputLayout.class);
    target.edtEmail = Utils.findRequiredViewAsType(source, R.id.edtEmail, "field 'edtEmail'", EditText.class);
    target.txtInpLayEmail = Utils.findRequiredViewAsType(source, R.id.txtInpLayEmail, "field 'txtInpLayEmail'", TextInputLayout.class);
    target.edtCity = Utils.findRequiredViewAsType(source, R.id.edtCity, "field 'edtCity'", EditText.class);
    target.txtInpLayCity = Utils.findRequiredViewAsType(source, R.id.txtInpLayCity, "field 'txtInpLayCity'", TextInputLayout.class);
    target.edtCountry = Utils.findRequiredViewAsType(source, R.id.edtCountry, "field 'edtCountry'", EditText.class);
    target.txtInpLayCountry = Utils.findRequiredViewAsType(source, R.id.txtInpLayCountry, "field 'txtInpLayCountry'", TextInputLayout.class);
    view = Utils.findRequiredView(source, R.id.btnRegister, "field 'btnRegister' and method 'onViewClicked'");
    target.btnRegister = Utils.castView(view, R.id.btnRegister, "field 'btnRegister'", Button.class);
    view2131230759 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RegistrationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edtFirstName = null;
    target.txtInpLayFirstName = null;
    target.edtLastName = null;
    target.txtInpLayLastName = null;
    target.edtEmail = null;
    target.txtInpLayEmail = null;
    target.edtCity = null;
    target.txtInpLayCity = null;
    target.edtCountry = null;
    target.txtInpLayCountry = null;
    target.btnRegister = null;

    view2131230759.setOnClickListener(null);
    view2131230759 = null;
  }
}
