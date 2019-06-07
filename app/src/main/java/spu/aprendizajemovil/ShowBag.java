package spu.aprendizajemovil;

import java.util.ArrayList;

import spu.aprendizajemovil.model.PrototypeContext;
import spu.aprendizajemovil.model.positionLayer.PositionedElement;
import spu.aprendizajemovil.model.positionLayer.PositionedTask;
import spu.aprendizajemovil.model.userLayer.User;
import spu.aprendizajemovil.utils.SeparatedListAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import spu.aprendizajemovil.R;

public class ShowBag extends Activity
{

	PrototypeContext context;
	ListView lView;

	private SeparatedListAdapter adapter;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_bag);

		Intent i = getIntent();
		this.context = (PrototypeContext) i
				.getSerializableExtra("prototypeContext");

		TextView textView = (TextView) findViewById(R.id.emptyBagText);
		if (this.context.getUser().isBagEmpty())
		{
			textView.setText(R.string.you_have_not_collected_elements_yet);
		} else
		{
			textView.setText(R.string.collected_elements_for_each_task);
		}

		// Create the ListView Adapter
		adapter = new SeparatedListAdapter(this, R.layout.list_header);

		User user = this.context.getUser();
		for (PositionedTask task : context.getUserCurrentActivity()
				.getPositionedTasks())
		{
			if (user.getBagOfElements().containsKey(task))
			{
				ArrayList<PositionedElement> elements = user.getBagOfElements()
						.get(task);
				// Collections.sort(elements);

				ArrayAdapter<PositionedElement> listadapter = new ArrayAdapter<PositionedElement>(
						this, R.layout.list_item, elements);
				adapter.addSection(task.toString(), listadapter);
			}
		}

		// Get a reference to the ListView holder
		listView = (ListView) this.findViewById(R.id.list_journal);

		// Set the adapter on the ListView holder
		listView.setAdapter(adapter);

	}
	
	public void onBackPressed()
	{
	}

	public void returnToTask(View view)
	{
		finish();
	}

}
