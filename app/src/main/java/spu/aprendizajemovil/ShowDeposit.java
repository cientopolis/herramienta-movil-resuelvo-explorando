package spu.aprendizajemovil;

import java.util.ArrayList;

import spu.aprendizajemovil.model.PrototypeContext;
import spu.aprendizajemovil.model.positionLayer.PositionedDeposit;
import spu.aprendizajemovil.model.userLayer.User;
import spu.aprendizajemovil.utils.CollectedElement;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import spu.aprendizajemovil.R;

import static spu.aprendizajemovil.utils.Submitter.submit;

public class ShowDeposit extends Activity
{
	PrototypeContext context;
	PositionedDeposit deposit;
	ListView lView;
	Activity activity = this;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_show_deposit);
		TextView descripcion  = (TextView) findViewById(R.id.TextView01);

		Intent i = getIntent();
		this.context = (PrototypeContext) i
				.getSerializableExtra("prototypeContext");

		deposit = (PositionedDeposit) i.getSerializableExtra("deposit");
		lView = (ListView) findViewById(R.id.ListView01);

		descripcion.setText(deposit.getDescription());

		((TextView) findViewById(R.id.upperText)).setText(deposit.getName());

		context.setCurrentPosition("deposit");

		this.updateDeposit();

	}

	private void updateDeposit()
	{
		lView = (ListView) findViewById(R.id.ListView01);

		ArrayList<CollectedElement> items = this.context.getUser()
				.getCollectedElements();

		// Set option as Multiple Choice. So that user can able to select more
		// the one option from list
		ArrayAdapter<CollectedElement> arrayAdapter = new ArrayAdapter<CollectedElement>(
				this, android.R.layout.simple_list_item_multiple_choice, items);
		lView.setAdapter(arrayAdapter);
		lView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu)
	// {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.show_deposit, menu);
	// return true;
	// }

	public void deposit(View view)
	{
		User user = this.context.getUser();
		SparseBooleanArray checked = lView.getCheckedItemPositions();

		// depositar
		Boolean hasTrueElement = false;

		for (int i = 0; i < checked.size(); i++)
		{
			if (checked.valueAt(i) == true)
			{
				hasTrueElement = true;
			}
		}

		if (!hasTrueElement)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.no_selected_elements_to_deposit)
					.setCancelable(false)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener()
							{
								public void onClick(DialogInterface dialog,
										int id)
								{

								}
							});
			AlertDialog alert = builder.create();
			alert.setTitle(R.string.deposit_elements);
			alert.setIcon(R.drawable.alert_small);
			alert.show();
		} else
		{

			for (int i = 0; i < checked.size(); i++)
			{
				if (checked.valueAt(i) == true)
				{
					CollectedElement collectedElement = (CollectedElement) lView
							.getItemAtPosition(checked.keyAt(i));

					if (user.takeElementFromBag(collectedElement))
					{
						submit("Se depositó "+ collectedElement.getElement().getName());
						deposit.depositElement(collectedElement.getElement());

						// se evalua cada elemento depositado
						context.evaluateDeposit(deposit,
								collectedElement.getElement());
					}

					// Log.i("xxxx", i + " " + tag);
				}
			}
			// actualiza la lista
			this.leaveDeposit(view);
		}
	}

	public void onBackPressed()
	{
	}
	
	public void leaveDeposit(View view)
	{
		Intent intent = new Intent(this, EndDeposit.class);
		// en caso de que la tarea actual no haya sido terminada, se debe abrir
		// la ventana ReadTask

		// intent.putExtra(EXTRA_MESSAGE, message);
		intent.putExtra("prototypeContext", context);
		intent.putExtra("lastDepoOpened",deposit);
		intent.putExtra("hasDepositedElement", true);
		finish();
		startActivity(intent);
	}

	// public void onActivityResult(int requestCode, int resultCode, Intent res)
	// {
	// if (requestCode == 0)
	// {
	// if (resultCode == Activity.RESULT_OK)
	// {
	// // lo que retorna el "emulador" del scanner
	// String result = res.getStringExtra("SCAN_RESULT");
	// // String format = res.getStringExtra("SCAN_RESULT_FORMAT");
	//
	// // para la emulacion
	// boolean hasElementInBag = context.getUser().hasElementInBag(
	// result);
	//
	// String message = "";
	//
	// if (!hasElementInBag)
	// {
	// message = "No posee el elemento le�do";
	// } else
	// {
	// String elem = this.context.getUser().depositElement(result, deposit);
	// message = "Deposit� el elemento '" + elem + "' safistactoriamente";
	//
	// }
	//
	// AlertDialog alertDialog = new AlertDialog.Builder(this)
	// .create();
	// // alertDialog.setTitle("");
	// alertDialog.setMessage(message);
	// alertDialog.setIcon(R.drawable.ic_launcher);
	// alertDialog.show();
	//
	// //
	//
	// // Handle successful scan
	// } else if (resultCode == RESULT_CANCELED)
	// {
	// // Handle cancel
	// }
	// }
	// }

	public void goBack(View view)
	{
		Intent intent = new Intent(activity, EndDeposit.class);
		intent.putExtra("prototypeContext", context);
		intent.putExtra("hasDepositedElement", false);
		submit("El usuario terminó de depositar");
		finish();
		startActivity(intent);
	}

}
