package spu.aprendizajemovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import spu.aprendizajemovil.model.DepositEvaluator;
import spu.aprendizajemovil.model.TaskEvaluator;
import spu.aprendizajemovil.model.positionLayer.PositionedDeposit;
import spu.aprendizajemovil.utils.SeparatedListAdapter;

public class DepositEvaluationList extends EvaluationList {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_evaluation_list);
        LinearLayout tasksList = (LinearLayout)  this.findViewById(R.id.tasksListLayout);
        tasksList.setVisibility(View.GONE);
        View linea = findViewById(R.id.linea);
        linea.setVisibility(View.GONE);
        Button botonDesempeño = (Button) findViewById(R.id.boton_desempenio);
        botonDesempeño.setVisibility(View.GONE);
        generateList();
        submitearD(this.deposits);
    }

    @Override
    public void generateList(){
        generateTaskList();
    }

    @Override
    public void generateTaskList(){
        depositsList = (ListView) this.findViewById(R.id.depositsList);

        //deposits = context.getDepositEvaluators();
        deposits = new ArrayList<DepositEvaluator>();
        Intent i = getIntent();
        deposits.add(context.getDepositEvaluator((PositionedDeposit)i.getExtras().get("lastDepoOpened")));

        // depositAdapter = new ArrayAdapter<DepositEvaluator>(this,
        // android.R.layout.simple_list_item_1, deposits);
        // depositsList.setAdapter(depositAdapter);
        // depositsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<DepositEvaluator> listadapter4 = new ArrayAdapter<DepositEvaluator>(
                this, R.layout.list_item_deposit_icon, deposits);

        adapter2 = new SeparatedListAdapter(this, R.layout.list_header);
        adapter2.addSection("en depositos", listadapter4);

        depositsList.setAdapter(adapter2);
        depositsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        depositsList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long duration)
            {
                DepositEvaluator item = (DepositEvaluator) adapter2
                        .getItem(position);
                openEvaluationDeposit(item);
            }
        });
    }
}
