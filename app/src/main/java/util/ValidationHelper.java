package util;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by Sharath on 3/2/2018.
 */

public class ValidationHelper {
    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return !TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches();
    }


}
