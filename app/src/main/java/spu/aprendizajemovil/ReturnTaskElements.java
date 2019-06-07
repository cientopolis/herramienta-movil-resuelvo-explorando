package spu.aprendizajemovil;

import java.util.ArrayList;

import spu.aprendizajemovil.model.PrototypeContext;
import spu.aprendizajemovil.model.positionLayer.PositionedTask;
import spu.aprendizajemovil.model.userLayer.User;
import spu.aprendizajemovil.utils.CollectedElement;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import spu.aprendizajemovil.R;

public class ReturnTaskElements extends Activity
{
	private PrototypeContext context;
	private PositionedTask currentTask;
	private TextView taskText;
	private ListView lView;
	// private TextView numberOfElements;
	int requestCodeForReturn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_return_task_elements);

		Intent i = getIntent();
		this.context = (PrototypeContext) i
				.getSerializableExtra("prototypeContext");

		// numberOfElements = (TextView) findViewById(R.id.numberOfElements);
		lView = (ListView) findViewById(R.id.listOfElementsForThisTask);
		taskText = (TextView) findViewById(R.id.taskText);

		taskText.setText(context.getUser().getCurrentTask().getDescription());

		this.requestCodeForReturn = i.getExtras().getInt("requestCode");

		currentTask = this.context.getUserCurrentTask();

		ArrayList<CollectedElement> items = this.context.getUser()
				.getCollectedElementsForTask(currentTask);

		// numberOfElements.setText("Elementos Recolectados: " + items.size());

		// Set option as Multiple Choice. So that user can able to select more
		// the one option from list

		ArrayAdapter<CollectedElement> arrayAdapter = new ArrayAdapter<CollectedElement>(
				this, android.R.layout.simple_list_item_multiple_choice, items);
		lView.setAdapter(arrayAdapter);
		lView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}

	public void returnElements(View view)
	{
		// Intent intent = new Intent(this, ShowBag.class);
		//
		// // intent.putExtra(EXTRA_MESSAGE, message);
		// intent.putExtra("prototypeContext", context);
		// startActivity(intent);

		User user = this.context.getUser();

		// depositar
		SparseBooleanArray checked = lView.getCheckedItemPositions();

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
			builder.setMessage(R.string.no_elements_selected_to_return)
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
			alert.setTitle(R.string.return_elements);
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
						currentTask
								.returnElement(collectedElement.getElement());
					}

					// Log.i("xxxx", i + " " + tag);
				}
			}
			Intent resultData = new Intent();
			resultData.putExtra("context", context);
			setResult(Activity.RESULT_OK, resultData);
			finish();
		}
	}

	public void onBackPressed()
	{
	}

	public void goBack(View view)
	{
		finish();
	}

}
