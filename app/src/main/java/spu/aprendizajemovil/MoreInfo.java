package spu.aprendizajemovil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import spu.aprendizajemovil.model.JasonModel.ExtraInfo;

import static spu.aprendizajemovil.utils.Submitter.submit;

public class MoreInfo extends Activity {

    ArrayList<ExtraInfo> extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        Bundle datos = getIntent().getExtras();
        TextView consigna = (TextView) findViewById(R.id.consigna);
        consigna.setText(datos.getString("consigna"));
        ArrayAdapter<String> adaptador;
        final ListView lista = (ListView)findViewById(R.id.list);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //call(lista.getItemAtPosition(i).toString());
                onListItemClick(lista,view,i,l);
            }
        });
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        lista.setAdapter(adaptador);

        //ArrayList<String> extras = datos.getStringArrayList("extras");
        extras = (ArrayList<ExtraInfo>)datos.getSerializable("extras");
        for (ExtraInfo aux: extras) {
            adaptador.add(aux.toString());
        }
    }

    public boolean isEnabled( int position) {
        return true;
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        /*PARA ABRIR DOCUMENTO O LINK:
            call(l.getItemAtPosition(position).toString());*/
        //Seteo que se llame este metodo en el setOnItemClickListener en el onCreate
        String elemSeleccionado = l.getItemAtPosition(position).toString();
        ExtraInfo miElem= new ExtraInfo("","");
        for (ExtraInfo elem: extras) {
            if(elem.title.equals(elemSeleccionado)){
                miElem=elem;
            }
        }

        new AlertDialog.Builder(this)
                .setTitle(miElem.title)
                .setMessage(
                        miElem.content)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(
                                    DialogInterface dialog,
                                    int which)
                            {
                            }
                        }).show();
    }

    /*public Dialog onCreateDialog(Bundle savedInstanceState, String title, String content){
        AlertDialog.Builder b = new AlertDialog.Builder(getApplicationContext());
        b.setTitle(title);
        b.setMessage(content).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return b.create();
    }*/

    public void call (String link){
        if (/*Patterns.WEB_URL.matcher(link).matches()*/
                URLUtil.isValidUrl(link)) {
            openUrl(link);
        }
        else {
            openDoc(Environment.getExternalStorageDirectory()+"/Prueba/"+link);
        }
    }

    public void openDoc (String s){
        File file = new File(s);
        Uri uri = Uri.parse("file://" + file.getAbsolutePath());
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String type = "application/msword";
        intent.setDataAndType(Uri.fromFile(file), type);
        startActivity(intent);
    }

    public void openUrl (String direccion){
        try {
            URL myURL = new URL(direccion);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(direccion));
        startActivity(i);

    }
}
