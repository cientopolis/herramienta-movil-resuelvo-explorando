package spu.aprendizajemovil;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.hololo.tutorial.library.PermissionStep;
import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class CarruselActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setFinishText("Finalizar");
        this.setCancelText("Omitir");
        this.setPrevText("Anterior");
        this.setNextText("Siguiente");
        addFragment(new Step.Builder().setTitle(getResources().getString(R.string.BienvenidaTítulo))
                .setContent(getResources().getString(R.string.BienvenidaDescripcion))
                .setBackgroundColor(getResources().getColor(R.color.azulClarito)) // int background color
                .setDrawable(R.drawable.reicon_a) // int top drawable
                .setSummary(getResources().getString(R.string.Facultad))
                .build());
        // Permission Step
        addFragment(new PermissionStep.Builder().setTitle(getResources().getString(R.string.MapaTitulo))
                .setContent(getResources().getString(R.string.MapaDescripcion))
                .setBackgroundColor(getResources().getColor(R.color.azulClarito))
                .setDrawable(R.drawable.mapanue)
                .setSummary(getResources().getString(R.string.Facultad))
                .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .build());
        addFragment(new PermissionStep.Builder().setTitle(getResources().getString(R.string.LeerTareaTitulo))
                .setContent(getResources().getString(R.string.LeerTareaDescripcion))
                .setBackgroundColor(getResources().getColor(R.color.azulClarito))
                .setDrawable(R.drawable.rescanner_amarillo_posta)
                .setSummary(getResources().getString(R.string.Facultad))
                .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .build());
        addFragment(new PermissionStep.Builder().setTitle(getResources().getString(R.string.MasInfoTitulo))
                .setContent(getResources().getString(R.string.MasInfoDescripcion))
                .setBackgroundColor(getResources().getColor(R.color.azulClarito))
                .setDrawable(R.drawable.masinfo)
                .setSummary(getResources().getString(R.string.Facultad))
                .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .build());
        addFragment(new PermissionStep.Builder().setTitle(getResources().getString(R.string.MiBolsaTitulo))
              .setContent(getResources().getString(R.string.MiBolsaDescripcion))
              .setBackgroundColor(getResources().getColor(R.color.azulClarito))
              .setDrawable(R.drawable.mibolsa)
              .setSummary(getResources().getString(R.string.Facultad))
              .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
              .build());
        addFragment(new PermissionStep.Builder().setTitle(getResources().getString(R.string.AyudaTitulo))
                .setContent(getResources().getString(R.string.AyudaDescripcion))
                .setBackgroundColor(getResources().getColor(R.color.azulClarito))
                .setDrawable(R.drawable.ayuda)
                .setSummary(getResources().getString(R.string.Facultad))
                .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .build());
        addFragment(new PermissionStep.Builder().setTitle(getResources().getString(R.string.RecolectarElementoTitulo))
                .setContent(getResources().getString(R.string.RecolectarElementoDescripcion))
                .setBackgroundColor(getResources().getColor(R.color.azulClarito))
                .setDrawable(R.drawable.rescanner_amarillo_posta)
                .setSummary(getResources().getString(R.string.Facultad))
                .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .build());
        addFragment(new PermissionStep.Builder().setTitle(getResources().getString(R.string.DepositarElementosTitulo))
                .setContent(getResources().getString(R.string.DepositarElementoDescripcion))
                .setBackgroundColor(getResources().getColor(R.color.azulClarito))
                .setDrawable(R.drawable.depositart4)
                .setSummary(getResources().getString(R.string.Facultad))
                .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .build());
        addFragment(new PermissionStep.Builder().setTitle(getResources().getString(R.string.VerDesempeñoPorTareaTitulo))
                .setContent(getResources().getString(R.string.VerDesempeñoPorTareaDescripcion))
                .setBackgroundColor(getResources().getColor(R.color.azulClarito))
                .setDrawable(R.drawable.desempenio)
                .setSummary(getResources().getString(R.string.Facultad))
                .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .build());
        addFragment(new PermissionStep.Builder().setTitle(getResources().getString(R.string.VerDesempeñoTitulo))
                .setContent(getResources().getString(R.string.VerDesempeñoDescripcion))
                .setBackgroundColor(getResources().getColor(R.color.azulClarito))
                .setDrawable(R.drawable.desempenio_total_final)
                .setSummary(getResources().getString(R.string.Facultad))
                .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .build());

    }
}
