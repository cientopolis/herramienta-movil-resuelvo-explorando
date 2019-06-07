package spu.aprendizajemovil;

import spu.aprendizajemovil.utils.ListAdapterWithDifferentsIcons;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import spu.aprendizajemovil.R;

public class MultipleItemsList extends Activity
{

	private ListAdapterWithDifferentsIcons mAdapter;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_multiple_items_list);

		mAdapter = new ListAdapterWithDifferentsIcons(this);
		mAdapter.addSeparatorGreen("Elementos correctos");
		for (int i = 1; i < 6; i++)
		{
			mAdapter.addItem("Elemento " + i);
		}

		mAdapter.addSeparatorRed("Elementos incorrectos");
		for (int i = 1; i < 6; i++)
		{
			mAdapter.addItem("Elemento " + i);
		}
		mAdapter.addSeparatorYellow("Elementos faltantes");
		for (int i = 1; i < 6; i++)
		{
			mAdapter.addItem("Elemento " + i);
		}

		ListView myList = (ListView) findViewById(R.id.list);

		myList.setAdapter(mAdapter);

	}
}