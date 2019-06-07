package spu.aprendizajemovil.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.format.Time;
import android.view.View;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Submitter {

    static String name = "logActividades"+DateFormat.getDateTimeInstance().format(new Date())+".txt";

    static File log = new File(Environment.getExternalStorageDirectory()+"/ResuelvoExplorando/RegistroDeUso",name);

    public static String getName(){
        return name;
    }

    public static File getLog() {
        return log;
    }

    public static void delete(){
        log.delete();
    }

    public static void createLog(){
        File folder = new File(Environment.getExternalStorageDirectory()+"/ResuelvoExplorando");
        if(! folder.exists()){
            folder.mkdirs();
        }
        try {
            delete();
            log.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void submit (String text){
        String time = DateFormat.getDateTimeInstance().format(new Date());
        try {
            FileWriter fileWriter = new FileWriter(log,true);
            BufferedWriter salida = new BufferedWriter(fileWriter);
            salida.write(time+" - "+text+"\n");
            salida.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
