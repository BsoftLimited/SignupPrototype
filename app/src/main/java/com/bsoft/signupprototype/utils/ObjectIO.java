package com.bsoft.signupprototype.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ObjectIO {
    Context context;
    File dataDir;

    public ObjectIO(Context context, String folder){
        this.context = context;
        if(folder.isEmpty()){
            dataDir = this.context.getFilesDir();
        }
        dataDir = this.context.getDir(folder, Context.MODE_PRIVATE);
    }

    public ObjectIO(Context context){
        this(context, "");
    }

    public void writeObjectToFile(String[] files,Object... objects){
        for(int i = 0; i < files.length; i++){
            File init = new File(dataDir + "/" + files[i]);
            Object object = objects[i];
            try{
                if(!init.exists()){
                    if(!init.createNewFile()) {
                        Log.d("File", "Unable to create file");
                        return;
                    }
                }
                FileOutputStream fileOut = new FileOutputStream(init);
                ObjectOutputStream objectOutput = new ObjectOutputStream(fileOut);
                objectOutput.writeObject(object);
                objectOutput.close();
            }catch (IOException ioEx){
                ioEx.printStackTrace();
            }
        }
    }

    public Object[] readObjectFromFile(String... files){
        ArrayList<Object> objects = new ArrayList<>();
        for(String file : files){
            String init = dataDir + "/" + file;
            try{
                FileInputStream fileIn = new FileInputStream(init);
                ObjectInputStream objectInput = new ObjectInputStream(fileIn);
                objects.add(objectInput.readObject());
                objectInput.close();
            }catch (IOException | ClassNotFoundException ioEx){
                ioEx.printStackTrace();
            }
        }
        return objects.toArray();
    }
}
