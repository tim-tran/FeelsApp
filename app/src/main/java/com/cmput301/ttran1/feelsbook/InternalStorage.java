package com.cmput301.ttran1.feelsbook;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
Class: InternalStorage

Contains functions for writing and reading objects to internal storage.

Class taken from
Veaceslav Grec, Android Research Blog
https://androidresearch.wordpress.com/2013/04/07/caching-objects-in-android-internal-storage/
 2018-10-04
 */
public final class InternalStorage {

    private InternalStorage() {}

    public static void writeObject(Context context, String fileName, Object object) throws IOException {
        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
//        FileOutputStream fos = new FileOutputStream(fileName, false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    public static Object readObject(Context context, String fileName) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = context.openFileInput(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }
}
