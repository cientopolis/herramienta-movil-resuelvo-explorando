package spu.aprendizajemovil.utils;

import android.os.Environment;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import spu.aprendizajemovil.model.JasonModel.EActivityFromJson;

public class JsonLoader {

    String path;

    public EActivityFromJson load (String path){
        EActivityFromJson jsonEA=null;
        try {
            File daFile = new File(path);
            FileInputStream stream = new FileInputStream(daFile);

            String jasonStr = null;

            try {
                FileChannel fc = stream.getChannel();
                MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
                jasonStr = Charset.defaultCharset().decode(bb).toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                stream.close();
            }
            Gson gson = new Gson();
            jsonEA = gson.fromJson(jasonStr, EActivityFromJson.class);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonEA;
    }
}
