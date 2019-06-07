package spu.aprendizajemovil;

import java.util.ArrayList;

import spu.aprendizajemovil.model.PrototypeContext;
import spu.aprendizajemovil.model.positionLayer.PositionedElement;
import spu.aprendizajemovil.model.positionLayer.PositionedTask;
import spu.aprendizajemovil.model.userLayer.User;
import spu.aprendizajemovil.utils.CollectedElement;
import spu.aprendizajemovil.utils.Help;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static spu.aprendizajemovil.utils.Submitter.submit;

public class ShowTask extends Activity
{
	private Button pickUpElement;
	private Button returnElementButton;
	private TextView selectedTask;
	private TextView listText;
	private RelativeLayout listLayout;
	private RelativeLayout listTextLayout;
	private RelativeLayout taskLayout;
	private RelativeLayout pickUpItemLayout;
	public boolean ok = true;
	PrototypeContext context;
	PositionedTask currentTask;
	ListView listOfElementsForThisTask;
	User user;


	final int requestCodeForReturn = 555;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_show_task2);
		listOfElementsForThisTask = (ListView) findViewById(R.id.listOfElementsForThisTask);
		setListText((TextView) findViewById(R.id.listText));
		pickUpElement = (Button) findViewById(R.id.pickUpElement);
		returnElementButton = (Button) findViewById(R.id.returnElementButton);
		listLayout = (RelativeLayout) findViewById(R.id.listLayout);
		listTextLayout = (RelativeLayout) findViewById(R.id.listTextLayout);
		taskLayout = (RelativeLayout) findViewById(R.id.taskLayout);
		pickUpItemLayout = (RelativeLayout) findViewById(R.id.pickUpItemLayout);

		pickUpElement.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				scanner(v);
			}
		});

		returnElementButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				removeFromBag();
			}
		});
		// btnScannear = (Button)findViewById(R.id.btnScannear);
		// btnScannear.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		// startActivityForResult(intent, 0);
		// }
		// });

		Intent i = getIntent();
		this.context = (PrototypeContext) i
				.getSerializableExtra("prototypeContext");

		currentTask = this.context.getUserCurrentTask();
		user = context.getUser();

		TextView taskDescription = (TextView) findViewById(R.id.taskDescription);
		taskDescription.setText(currentTask.getDescription());
	}
	public void onActivityResult(int requestCode, int resultCode, Intent res)
	{
		if (requestCode == 0)
		{
			if (resultCode == Activity.RESULT_OK)
			{
				// lo que retorna el "emulador" del scanner
				// String result = res.getStringExtra("result");

				String result = res.getStringExtra("SCAN_RESULT");

				// String contents = res.getStringExtra("SCAN_RESULT");
				// String format = res.getStringExtra("SCAN_RESULT_FORMAT");

				// para la emulacion
				PositionedElement element = context.hasElement(result);
				String message = "";
				User user = context.getUser();

				if (element == null)
				{
					message = "El elemento leído es inválido.";
					submit("Se leyó un código inválido");
				} else
				{
					if (user.hasElementInBag(element, currentTask))
					{
						message = "Ya recolectaron " + element.getName()
								+ " para esta tarea.";
						submit("Se intentó recoger " + element.getName()+" pero ya lo había hecho");
					} else if (!currentTask.elementIsAvailable(element))
					{
						message = "El elemento "
								+ element.getName()
								+ " no se encuentra disponible para esta tarea.";
								submit("Se recolectó un elemento no disponible para esta tarea");
					} else
					{
						// if lo tengo
						{
							// si realmente lo recogio (ya que paso por todas
							// las validaciones previas, asi que deber�a
							// recogerlo)
							if (user.pickElementFromCurrentTask(element))
							{
								updateList();
								submit("Se recogió " + element.getName());
							} else
							{
								message = "No se pudo recolectar el elemento "
										+ element.getName();
								submit("No se pudo recolectar el elemento");
								updateList();
							}

						}
					}
				}

				if (!message.equals(""))
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setMessage(message)
							.setCancelable(false)
							.setPositiveButton(R.string.ok,
									new DialogInterface.OnClickListener()
									{
										public void onClick(
												DialogInterface dialog, int id)
										{

										}
									});
					AlertDialog alert = builder.create();
					alert.setTitle(R.string.invalid_code);
					alert.setIcon(R.drawable.alert_small);
					alert.show();
				}
			} else if (resultCode == RESULT_CANCELED)
			{
				// Handle cancel
			}
		} else
		{
			if (requestCode == requestCodeForReturn)
			{
				if (resultCode == Activity.RESULT_OK)
				{
					this.context = (PrototypeContext) res
							.getSerializableExtra("context");
					this.currentTask = this.context.getUserCurrentTask();
					this.user = this.context.getUser();
					updateList();
				}
			}
		}
	}

	public void openDepositsView(View view)
	{
		Intent intent = new Intent(this, ShowDepositsMap.class);
		intent.putExtra("prototypeContext", context);
		startActivity(intent);
	}

	public void openBagView(View view)
	{
		Intent intent = new Intent(this, ShowBag.class);
		intent.putExtra("prototypeContext", context);
		submit("Se solicitó ver la bolsa");
		startActivity(intent);
	}

	public void scanner(View view)
	{
		/*Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE",
				"QR_CODE_MODE");
		startActivityForResult(intent, 0);*/
		Intent i = new Intent(this, Escaner.class);
		startActivityForResult(i, 0);
	}

	public void endTask(View view)
	{
		// se evaluan los elementos recogidos (los que fueron dejados no se
		// toman en cuenta)
		context.evaluateTask(currentTask, user);
		Intent intent = new Intent(this, EndTask.class);
		intent.putExtra("prototypeContext", context);
		submit("Se finalizó " + currentTask.getName());
		finish();
		startActivity(intent);
	}

	private void updateList()
	{
		ArrayList<CollectedElement> items = this.context.getUser()
				.getCollectedElementsForTask(currentTask);

		ArrayAdapter<CollectedElement> arrayAdapter = new ArrayAdapter<CollectedElement>(
				this, android.R.layout.simple_list_item_1, items);

		if (arrayAdapter.isEmpty())
		{
			listTextLayout.setVisibility(View.GONE);
			listLayout.setVisibility(View.GONE);
			this.setHeightInDp(147, taskLayout);
			this.setHeightInDp(119, pickUpItemLayout);
		} else
		{
			TextView consigna = (TextView) findViewById(R.id.taskDescription);
			consigna.setText(context.getUserCurrentTask().getTask().getConsigna());
			listTextLayout.setVisibility(View.VISIBLE);
			listLayout.setVisibility(View.VISIBLE);
			this.setHeightInDp(102, taskLayout);
			this.setHeightInDp(72, pickUpItemLayout);
		}
		listOfElementsForThisTask.setAdapter(arrayAdapter);
		listOfElementsForThisTask.setChoiceMode(ListView.CHOICE_MODE_NONE);
		listOfElementsForThisTask.setSelection(arrayAdapter.getCount() - 1);
	}

	public void removeFromBag()
	{
		Intent intent = new Intent(this, ReturnTaskElements.class);
		intent.putExtra("prototypeContext", context);
		intent.putExtra("requestCode", requestCodeForReturn);
		startActivityForResult(intent, requestCodeForReturn);
	}

	private void setHeightInDp(int dp, RelativeLayout layout)
	{
		final float scale = getBaseContext().getResources().getDisplayMetrics().density;
		int pixels = (int) (dp * scale + 0.5f);
		layout.getLayoutParams().height = pixels;
	}

	public void onBackPressed()
	{
	}

	public TextView getSelectedTask() {
		return selectedTask;
	}

	public void setSelectedTask(TextView selectedTask) {
		this.selectedTask = selectedTask;
	}

	public TextView getListText() {
		return listText;
	}

	public void setListText(TextView listText) {
		this.listText = listText;
	}

	public void showText(View view){
		submit("Se solicitó ver la consigna completa");
			String textoCompleto = context.getUserCurrentTask().getDescription();
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(textoCompleto);
			builder.setPositiveButton("OK", null);
			builder.setTitle("Consigna");
			builder.setCancelable(true);
			builder.create().show();
	}

	public void moreInfo(View view){
		Intent i = new Intent(this,MoreInfo.class);
		i.putExtra("extras",context.getUserCurrentTask().getTask().getExtras());
		i.putExtra("consigna",context.getUserCurrentTask().getTask().getDescription());
		startActivity(i);
	}
	public void help (View view){
		submit("Se solicitó ayuda del docente");
		Intent i = new Intent(this, Help.class);
		startActivity(i);
	}


}
