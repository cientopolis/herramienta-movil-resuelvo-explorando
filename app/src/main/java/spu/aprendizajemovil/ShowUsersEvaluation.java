package spu.aprendizajemovil;

import spu.aprendizajemovil.model.DepositEvaluator;
import spu.aprendizajemovil.model.PrototypeContext;
import spu.aprendizajemovil.model.TaskEvaluator;
import spu.aprendizajemovil.model.activityLayer.Deposit;
import spu.aprendizajemovil.model.positionLayer.PositionedDeposit;
import spu.aprendizajemovil.model.positionLayer.PositionedElement;
import spu.aprendizajemovil.utils.ListAdapterWithDifferentsIcons;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;

import spu.aprendizajemovil.R;

public class ShowUsersEvaluation extends Activity
{

	private ListView listView;
	private PrototypeContext context;
	private TextView evaluationMessage;
	private ListAdapterWithDifferentsIcons adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Intent i = getIntent();
		this.setContext((PrototypeContext) i.getSerializableExtra("prototypeContext"));

		if (i.getSerializableExtra("task") != null)
		{
			setContentView(R.layout.activity_show_users_evaluation_task);
			evaluationMessage = (TextView) findViewById(R.id.evaluationMessage);
			fillTaskList();
		} else
		{
			setContentView(R.layout.activity_show_users_evaluation_deposit);
			evaluationMessage = (TextView) findViewById(R.id.evaluationMessage);
			fillDepositList();
		}

	}

	public void goBack(View view)
	{
		finish();
	}

	private void fillTaskList()
	{

		TaskEvaluator task = (TaskEvaluator) getIntent().getSerializableExtra(
				"task");
		evaluationMessage.setText(task.getTask().getDescription());

		adapter = new ListAdapterWithDifferentsIcons(this);

		String title = "Recolectados Correctamente";
		adapter.addSeparatorGreen(title);
		for (PositionedElement element : task.getCorrectElements())
		{

			adapter.addItem(element.getName());
		}

		title = "Recolectados Incorrectamente";
		adapter.addSeparatorRed(title);
		for (PositionedElement element : task.getIncorrectElements())
		{
			adapter.addItem(element.getName());
		}

		title = "Elementos No Recolectados";
		adapter.addSeparatorYellow(title);
		for (PositionedElement element : task.getLeftElements())
		{
			adapter.addItem(element.getName());
		}

		// Get a reference to the ListView holder
		listView = (ListView) this.findViewById(R.id.evaluationList);

		// Set the adapter on the ListView holder
		listView.setAdapter(adapter);
	}

	private void fillDepositList()
	{
		// adapter = new SeparatedListAdapter(this, R.layout.list_header);
		DepositEvaluator deposit = (DepositEvaluator) getIntent()
				.getSerializableExtra("deposit");
		evaluationMessage.setText(deposit.getDeposit().getName());

		adapter = new ListAdapterWithDifferentsIcons(this);

		String title = "Depositados correctamente";
		adapter.addSeparatorGreen(title);
		for (PositionedElement element : deposit.getCorrectElements())
		{
			adapter.addItem(element.getName());
		}

		title = "Depositados incorrectamente";
		adapter.addSeparatorRed(title);
		for (PositionedElement element : deposit.getIncorrectElements())
		{
			String depositList = new String();
			for (PositionedDeposit auxDeposit : element.getDeposits()) {
				depositList+=auxDeposit.getName();
			}
			adapter.addItem(element.getName()+ " ("+depositList+")");
		}

		// title = "--> Faltaron depositar (de los que no depositaron)";
		// adapter.addSeparatorYellow(title);

		// Get a reference to the ListView holder
		listView = (ListView) this.findViewById(R.id.evaluationList);

		// Set the adapter on the ListView holder
		listView.setAdapter(adapter);
	}
	
	public void onBackPressed()
	{
	}

	public PrototypeContext getContext() {
		return context;
	}

	public void setContext(PrototypeContext context) {
		this.context = context;
	}

}
