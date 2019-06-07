package spu.aprendizajemovil.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import spu.aprendizajemovil.R;

import static spu.aprendizajemovil.utils.Submitter.submit;

public class Help extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help2);
    }
    public void cerrar (View view){
        submit("Se cerró el solicitar ayuda");
        this.finish();
    }
}
