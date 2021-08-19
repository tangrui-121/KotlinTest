package com.person.kotlintest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText aaa = findViewById(R.id.aaaaa);
        Log.e("wwww",editTextIsShown(aaa)+"");
    }

    private boolean editTextIsShown(EditText editText) {
        View current = editText;
        do {
            if (current.getVisibility() != View.VISIBLE) {
                return false;
            }
            ViewParent parent = current.getParent();
            if (parent == null) {
                Log.e("wwww","11111111111111111111111111111");
                if (current.getVisibility() == View.VISIBLE) {
                    return true;
                } else {
                    return false;
                }
            }
            if (!(parent instanceof View)) {
                Log.e("wwww","2222222222222222222222222222222");
                return true;
            }
            current = (View) parent;
        } while (current != null);
        return false;
    }
}