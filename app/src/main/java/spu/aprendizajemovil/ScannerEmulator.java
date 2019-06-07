package spu.aprendizajemovil;

import spu.aprendizajemovil.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ScannerEmulator extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scanner_emulator);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu)
	// {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.scanner_emulator, menu);
	// return true;
	// }

	public void acceptButtonOnClick(View view)
	{
		String readed = ((EditText) findViewById(R.id.editText1)).getText()
				.toString();

		Intent resultData = new Intent();
		resultData.putExtra("SCAN_RESULT", readed);
		setResult(Activity.RESULT_OK, resultData);
		finish();
	}

	public void scanOnClick(View view)
	{
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE",
				"QR_CODE_MODE");
		startActivityForResult(intent, 0);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		if (requestCode == 0)
		{
			if (resultCode == Activity.RESULT_OK)
			{
				String result = intent.getStringExtra("SCAN_RESULT");

				Intent resultData = new Intent();
				resultData.putExtra("SCAN_RESULT", result);
				setResult(Activity.RESULT_OK, resultData);
				finish();

			} else if (resultCode == RESULT_CANCELED)
			{
				// Handle cancel
			}
		}
	}
}
