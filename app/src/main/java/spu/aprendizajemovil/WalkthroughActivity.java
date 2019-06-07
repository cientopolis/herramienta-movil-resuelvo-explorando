package spu.aprendizajemovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

import spu.aprendizajemovil.R;

public class WalkthroughActivity extends FancyWalkthroughActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_walkthrough);
        FancyWalkthroughCard fancywalkthroughCard1 = new FancyWalkthroughCard("Bienvenidos al tutorial de  Resuelvo Explorando", "Description", R.drawable.reicon_a);
        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        fancywalkthroughCard1.setTitleColor(R.color.black);
        fancywalkthroughCard1.setDescriptionColor(R.color.black);
        FancyWalkthroughCard fancywalkthroughCard2 = new FancyWalkthroughCard("Visualizarán el siguiente lugar a realizar la tarea", "Description", R.drawable.mapita);
        fancywalkthroughCard2.setBackgroundColor(R.color.white);
        fancywalkthroughCard2.setTitleColor(R.color.black);
        fancywalkthroughCard2.setDescriptionColor(R.color.black);
        FancyWalkthroughCard fancywalkthroughCard3 = new FancyWalkthroughCard("Recibirán la consigna de una tarea", "Description", R.drawable.rescanner_amarillo_posta);
        fancywalkthroughCard3.setBackgroundColor(R.color.white);
        fancywalkthroughCard3.setTitleColor(R.color.black);
        fancywalkthroughCard3.setDescriptionColor(R.color.black);
        FancyWalkthroughCard fancywalkthroughCard4 = new FancyWalkthroughCard("", "Description", R.drawable.masinfo);
        fancywalkthroughCard4.setBackgroundColor(R.color.white);
        fancywalkthroughCard4.setTitleColor(R.color.black);
        fancywalkthroughCard4.setDescriptionColor(R.color.black);
        FancyWalkthroughCard fancywalkthroughCard5 = new FancyWalkthroughCard("Se les mostrará el botón mi bolsa", "Description", R.drawable.mibolsa);
        fancywalkthroughCard5.setBackgroundColor(R.color.white);
        fancywalkthroughCard5.setTitleColor(R.color.black);
        fancywalkthroughCard5.setDescriptionColor(R.color.black);
        FancyWalkthroughCard fancywalkthroughCard6 = new FancyWalkthroughCard("Se les mostrará el botón ayuda", "Description", R.drawable.ayuda);
        fancywalkthroughCard6.setBackgroundColor(R.color.white);
        fancywalkthroughCard6.setTitleColor(R.color.black);
        fancywalkthroughCard6.setDescriptionColor(R.color.black);
        FancyWalkthroughCard fancywalkthroughCard7 = new FancyWalkthroughCard("Recolectarán elementos", "Description", R.drawable.rescanner_amarillo_posta);
        fancywalkthroughCard7.setBackgroundColor(R.color.white);
        fancywalkthroughCard7.setTitleColor(R.color.black);
        fancywalkthroughCard7.setDescriptionColor(R.color.black);
        FancyWalkthroughCard fancywalkthroughCard8 = new FancyWalkthroughCard("Depositarán elementos", "Description", R.drawable.depositart4);
        fancywalkthroughCard8.setBackgroundColor(R.color.white);
        fancywalkthroughCard8.setTitleColor(R.color.black);
        fancywalkthroughCard8.setDescriptionColor(R.color.black);
        FancyWalkthroughCard fancywalkthroughCard9 = new FancyWalkthroughCard("Podrán ver su desempeño", "Description", R.drawable.desempenio);
        fancywalkthroughCard9.setBackgroundColor(R.color.white);
        fancywalkthroughCard9.setTitleColor(R.color.black);
        fancywalkthroughCard9.setDescriptionColor(R.color.black);
        List<FancyWalkthroughCard> pages = new ArrayList<>();
        pages.add(fancywalkthroughCard1);
        pages.add(fancywalkthroughCard2);
        pages.add(fancywalkthroughCard3);
        pages.add(fancywalkthroughCard4);
        pages.add(fancywalkthroughCard5);
        pages.add(fancywalkthroughCard6);
        pages.add(fancywalkthroughCard7);
        pages.add(fancywalkthroughCard8);
        pages.add(fancywalkthroughCard9);
        setOnboardPages(pages);
        setColorBackground(R.color.azulClarito);

    }

    @Override
    public void onFinishButtonPressed() {
        this.finish();
    }

}
