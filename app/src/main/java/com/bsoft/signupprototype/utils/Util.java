package com.bsoft.signupprototype.utils;

import android.content.Context;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.UUID;

public class Util {
    public static final short ToastDuration = Toast.LENGTH_LONG;

    public static boolean saveDetail(Context context, Details details){
        try {
            ObjectIO objectIO = new ObjectIO(context, "dta");
            objectIO.writeObjectToFile(new String[]{"details"}, details);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static Details retrieveDetail(Context context){
        ObjectIO objectIO = new ObjectIO(context, "data");
        Object[] savedObjects = objectIO.readObjectFromFile("details");
        if (savedObjects != null && savedObjects.length > 0) {
            return ((Details) savedObjects[0]);

        }
        return new Details();
    }

    public static String generateUid(String username){
        UUID.fromString(username + Time.CurrentTime().toString());
        return username;
    }

    public static boolean isFormValid(Context context, View... views){
        for (View view : views){
            if(view instanceof EditText || view instanceof AutoCompleteTextView){
                if(((EditText)view).getText().toString().isEmpty()){
                    Toast.makeText(context, view.getTag().toString() + " cannot be left empty", ToastDuration).show();
                    return  false;
                }
            }else if(view instanceof Spinner){
                if(((Spinner)view).getSelectedItemPosition() <= 0){
                    Toast.makeText(context, "An option must be selected in " + view.getTag().toString(), ToastDuration).show();
                    return false;
                }
            }
        }
        return true;
    }

    public static void loadFragment(AppCompatActivity activity, Fragment fragment, int layout){
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(layout, fragment);
        transaction.commit();
    }
}
