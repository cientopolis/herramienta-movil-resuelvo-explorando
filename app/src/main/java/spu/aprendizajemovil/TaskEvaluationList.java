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
import spu.aprendizajemovil.model.PrototypeContext;
import spu.aprendizajemovil.model.TaskEvaluator;
import spu.aprendizajemovil.model.positionLayer.PositionedTask;
import spu.aprendizajemovil.utils.SeparatedListAdapter;

import static spu.aprendizajemovil.utils.Submitter.submit;

public class TaskEvaluationList extends EvaluationList {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_evaluation_list);
        LinearLayout depositsList = (LinearLayout) this.findViewById(R.id.depositsListLayout);
        depositsList.setVisibility(View.GONE);
        View linea = findViewById(R.id.linea);
        linea.setVisibility(View.GONE);
        Button botonDesempeño = (Button) findViewById(R.id.boton_desempenio);
        botonDesempeño.setVisibility(View.GONE);
        generateList();
        submitearT(this.tasks);
    }

    @Override
    public void generateList(){
        generateTaskList();
    }

    @Override
    public void generateTaskList(){
            tasksList = (ListView) this.findViewById(R.id.tasksList);
		    tasks = new ArrayList<TaskEvaluator>();
		    TaskEvaluator taskEvaluator = context.getTaskEvaluator(context.getUserCurrentTask());
		    tasks.add(taskEvaluator);

            // taskAdapter = new ArrayAdapter<TaskEvaluator>(this,
            // android.R.layout.simple_list_item_1, tasks);
            // tasksList.setAdapter(taskAdapter);
            // tasksList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            ArrayAdapter<TaskEvaluator> listadapter3 = new ArrayAdapter<TaskEvaluator>(
                    this, R.layout.list_item_task_icon, tasks);

            adapter = new SeparatedListAdapter(this, R.layout.list_header);
            adapter.addSection("en tareas", listadapter3);

            tasksList.setAdapter(adapter);
            tasksList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            tasksList.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long duration)
                {
                    TaskEvaluator item = (TaskEvaluator) adapter.getItem(position);
                    openEvaluationTask(item);

                }
            });
    }
}
