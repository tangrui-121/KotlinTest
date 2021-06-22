package com.person.kotlintest.reflex;

import android.content.Context;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @anthor tr
 * @date 2021/5/20
 * @desc
 */
public class StorageManagerTest extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StorageManager sm = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
        Class<? extends StorageManager> storageManagerClass = sm.getClass();

        try {
            Method method = storageManagerClass.getMethod("getVolumePaths");
            String[] paths = (String[]) method.invoke(sm, (Object[]) null);
            for (String aaa : paths){
                Log.i("www","aaa = " + aaa);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

