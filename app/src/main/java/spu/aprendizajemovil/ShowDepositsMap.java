package spu.aprendizajemovil;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import spu.aprendizajemovil.model.PrototypeContext;
import spu.aprendizajemovil.model.positionLayer.PositionedDeposit;

public class ShowDepositsMap extends Activity
{
	PrototypeContext context;
	private TextView upperText;
	private ImageView nextTaskMap;
	private ImageView warningImage;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_read_task);
		//
		Intent i = getIntent();
		this.context = (PrototypeContext) i
				.getSerializableExtra("prototypeContext");

		// warningImage
		warningImage = (ImageView) findViewById(R.id.warningImage);
		warningImage.setVisibility(View.GONE);

		// upperText
		upperText = (TextView) findViewById(R.id.upperText);

		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

		upperText.setLayoutParams(layoutParams);

		// nextTaskMap
		nextTaskMap = (ImageView) findViewById(R.id.nextTaskMap);
		System.out.println(context.getCurrentPosition() + "_to_deposits");
		//TODO IMAGEN
		
		nextTaskMap.setImageURI(Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+"/ResuelvoExplorando/Configuracion/Imagenes/"
				+ context.getCurrentPosition() + "_to_deposit" +".png"));
		
		
		//nextTaskMap.setImageResource(getResources().getIdentifier(
		//		context.getCurrentPosition() + "_to_deposit", "drawable",
		//		getPackageName()));

	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu)
	// {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.show__deposits, menu);
	// return true;
	// }

	public void scannerOnClick(View view)
	{
		/*Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE",
				"QR_CODE_MODE");
		startActivityForResult(intent, 0);*/
		Intent i = new Intent(this, Escaner.class);
		startActivityForResult(i, 0);

	}

	public void onActivityResult(int requestCode, int resultCode, Intent res)
	{
		if (requestCode == 0)
		{
			if (resultCode == Activity.RESULT_OK)
			{
				String result = res.getStringExtra("SCAN_RESULT");

				PositionedDeposit deposit = context.existsDeposit(result);
				if (deposit != null)
				{
					Intent intent = new Intent(this, ShowDeposit.class);
					intent.putExtra("prototypeContext", context);
					intent.putExtra("deposit", deposit);
					finish();
					startActivity(intent);
				} else
				{
					((TextView) findViewById(R.id.upperText))
							.setText(R.string.sorry_invalid_code);
					((TextView) findViewById(R.id.lowerText))
							.setText(R.string.go_to_poi_and_read_code);
					nextTaskMap = (ImageView) findViewById(R.id.nextTaskMap);
					
					//TODO IMAGEN
					nextTaskMap.setImageURI(Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+"/ResuelvoExplorando/Configuracion/Imagenes/"
							+ "deposits" +".png"));
					
					//nextTaskMap.setImageResource(getResources().getIdentifier(
					//		"deposits", "drawable", getPackageName()));

					// warningImage
					warningImage = (ImageView) findViewById(R.id.warningImage);
					warningImage.setVisibility(View.VISIBLE);

					// upperText
					upperText = (TextView) findViewById(R.id.upperText);
					final float scale = getBaseContext().getResources()
							.getDisplayMetrics().density;
					int pixels = (int) (265 * scale + 0.5f);
					RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
							pixels, FrameLayout.LayoutParams.WRAP_CONTENT);
					layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
					layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

					upperText.setLayoutParams(layoutParams);
				}

			} else if (resultCode == RESULT_CANCELED)
			{
				// Handle cancel
			}
		}
	}

	public void onBackPressed()
	{
	}

}
