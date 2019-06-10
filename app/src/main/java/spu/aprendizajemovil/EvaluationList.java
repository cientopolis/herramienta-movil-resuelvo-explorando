package spu.aprendizajemovil;

import java.io.File;
import java.util.ArrayList;

import spu.aprendizajemovil.model.DepositEvaluator;
import spu.aprendizajemovil.model.PrototypeContext;
import spu.aprendizajemovil.model.TaskEvaluator;
import spu.aprendizajemovil.model.activityLayer.Element;
import spu.aprendizajemovil.model.positionLayer.PositionedElement;
import spu.aprendizajemovil.model.positionLayer.PositionedTask;
import spu.aprendizajemovil.utils.SeparatedListAdapter;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import spu.aprendizajemovil.R;

import static spu.aprendizajemovil.utils.Submitter.getName;
import static spu.aprendizajemovil.utils.Submitter.submit;

public class EvaluationList extends Activity
{

	protected ListView tasksList;
	protected ListView depositsList;
	protected PrototypeContext context;
	ArrayAdapter<TaskEvaluator> taskAdapter;
	ArrayAdapter<DepositEvaluator> depositAdapter;
	protected SeparatedListAdapter adapter;
	protected SeparatedListAdapter adapter2;
	protected String desempenio = new String();
	protected ArrayList<TaskEvaluator> tasks = new ArrayList<TaskEvaluator>();
	protected ArrayList<DepositEvaluator> deposits = new ArrayList<DepositEvaluator>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evaluation_list);
		submit("Elige ver su desempeño");
		Intent i = getIntent();
		context = (PrototypeContext) i.getSerializableExtra("prototypeContext");
		generateList();

	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu)
	// {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.evaluation_list, menu);
	// return true;
	// }

	protected void openEvaluationDeposit(DepositEvaluator deposit)
	{
		submit("Solicita ver desempeño en "+deposit.getDeposit().getName());
		Intent intent = new Intent(this, ShowUsersEvaluation.class);
		intent.putExtra("prototypeContext", context);
		intent.putExtra("deposit", deposit);
		// finish();
		startActivity(intent);
	}

	protected void openEvaluationTask(TaskEvaluator task)
	{
		submit("Solicita ver desempeño en "+task.getTask().getName());
		Intent intent = new Intent(this, ShowUsersEvaluation.class);
		intent.putExtra("prototypeContext", context);
		intent.putExtra("task", task);
		// finish();
		startActivity(intent);
	}

	public void goBack(View view)
	{
		// Intent intent = new Intent(this, EndGame.class);
		// intent.putExtra("prototypeContext", context);
		finish();
		// startActivity(intent);
	}
	
	public void onBackPressed()
	{
	}

	public void sentAMail (View view){
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent .setType("vnd.android.cursor.dir/email");
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"iam@lifia.info.unlp.edu.ar"});
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Registro de eventos");
		emailIntent.putExtra(Intent.EXTRA_TEXT, "Gracias por compartir su desempeño");
		File filelocation = new File(Environment.getExternalStorageDirectory()+"/ResuelvoExplorando/RegistroDeUso/"+getName());
		Uri path = Uri.fromFile(filelocation);
		emailIntent .putExtra(Intent.EXTRA_STREAM, path);
		startActivity(Intent.createChooser(emailIntent , "Send email..."));
	}
	public void generateTaskList (){
		tasksList = (ListView) this.findViewById(R.id.tasksList);
		this.tasks = context.getTaskEvaluators();
		/*ArrayList<TaskEvaluator> tasks = new ArrayList<TaskEvaluator>();
		tasks.add(context.getTaskEvaluator(context.getUserCurrentTask()));*/

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

		tasksList.setOnItemClickListener(new OnItemClickListener()
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
	public void generateDepositList (){
		depositsList = (ListView) this.findViewById(R.id.depositsList);

		this.deposits = context.getDepositEvaluators();
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

		depositsList.setOnItemClickListener(new OnItemClickListener()
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

	protected void sumarDesempenio(String s){
		this.desempenio+=(s+"\n");
	}

	public void generateList (){
		generateTaskList();
		generateDepositList();
		submitear(this.tasks,this.deposits);
	}

	public void submitearTask(TaskEvaluator taskEvaluator){
		sumarDesempenio("Elementos correctos:");
		for (PositionedElement element: taskEvaluator.getCorrectElements()) {
			this.sumarDesempenio("    "+element.getName());
		}
		sumarDesempenio("Elementos incorrectos:");
		for (PositionedElement element: taskEvaluator.getIncorrectElements()) {
			this.sumarDesempenio("    "+element.getName());
		}
		sumarDesempenio("Elementos sin recoger:");
		for (PositionedElement element: taskEvaluator.getLeftElements()) {
			this.sumarDesempenio("    "+element.getName());
		}
	}

	public void submitearDeposit(DepositEvaluator depositEvaluator){
		sumarDesempenio("Elementos correctos:");
		for (PositionedElement element: depositEvaluator.getCorrectElements()) {
			this.sumarDesempenio("    "+element.getName());
		}
		sumarDesempenio("Elementos incorrectos:");
		for (PositionedElement element: depositEvaluator.getIncorrectElements()) {
			this.sumarDesempenio("    "+element.getName());
		}
	}

	public void submitearTasks(ArrayList<TaskEvaluator> tasks){
		for (TaskEvaluator task:tasks) {
			sumarDesempenio(task.getTask().getName());
			submitearTask(task);
		}
	}

	public void submitearDeposits(ArrayList<DepositEvaluator> depositEvaluators){
		for (DepositEvaluator depositEvaluator:depositEvaluators) {
			sumarDesempenio(depositEvaluator.getDeposit().getName());
			submitearDeposit(depositEvaluator);
		}
	}

	public void submitear(ArrayList<TaskEvaluator> tasks,ArrayList<DepositEvaluator> depositEvaluators){
		submitearTasks(tasks);
		submitearDeposits(depositEvaluators);
		submit("Desempeño actual: \n"+this.desempenio);
	}

	public void submitearD(ArrayList<DepositEvaluator> depositEvaluators){
		submitearDeposits(depositEvaluators);
		submit("Desempeño actual en depositos: \n"+this.desempenio);
	}

	public void submitearT(ArrayList<TaskEvaluator> tasks){
		submitearTasks(tasks);
		submit("Desempeño actual en tarea: \n"+this.desempenio);
	}
}
