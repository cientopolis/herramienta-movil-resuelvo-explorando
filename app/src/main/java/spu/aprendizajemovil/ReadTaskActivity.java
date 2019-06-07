package spu.aprendizajemovil;

import spu.aprendizajemovil.model.PrototypeContext;
import spu.aprendizajemovil.model.positionLayer.PositionedTask;

import android.annotation.SuppressLint;
import android.app.ActionBar;
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

import java.util.Scanner;

import spu.aprendizajemovil.R;

import static spu.aprendizajemovil.utils.Submitter.submit;

public class ReadTaskActivity extends Activity
{
	PrototypeContext context;

	// private Button scanButton;
	private TextView upperText;
	private ImageView nextTaskMap;
	private ImageView warningImage;

	@SuppressLint("ResourceType")
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
		//TODO  preview.setImageURI(Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Echo/Images/"+file_name));
		//http://stackoverflow.com/questions/17334529/setting-imageview-image-using-path-of-file-from-external-storage
			
		nextTaskMap.setImageURI(Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+"/ResuelvoExplorando/Configuracion/Imagenes/"
								+ context.getCurrentPosition() + "_to_"
								+ context.getUser().getNextTask().getName()+".png"));
		
		
		// nextTaskMap.setImageResource(getResources().getIdentifier(
		//		context.getCurrentPosition() + "_to_"
		//				+ context.getUser().getNextTask().getName(),
		//		"drawable", getPackageName()));

	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu)
	// {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.read_task, menu);
	// return true;
	// }

	public void onActivityResult(int requestCode, int resultCode, Intent res)
	{
		if (requestCode == 0)
		{
			if (resultCode == Activity.RESULT_OK)
			{
				// lo que retorna el "emulador" del scanner
				//String contents = res.getStringExtra("SCAN_RESULT");
				String contents = res.getStringExtra("SCAN_RESULT");
				submit("El scanner leyó " + contents);

				// String format = res.getStringExtra("SCAN_RESULT_FORMAT");

				PositionedTask nextTask = context.getUser().getNextTask();

				if (nextTask.hasIdentification(contents))
				{
					submit("Se inicia " + nextTask.getName());
					context.getUser().nextTask();
					Intent newIntent = new Intent(this, ShowTask.class);
					newIntent.putExtra("prototypeContext", context);
					context.setCurrentPosition(nextTask.getName());
					finish();
					startActivity(newIntent);
				} else
				{
					((TextView) findViewById(R.id.upperText))
							.setText(R.string.sorry_invalid_code);
					submit("Se leyó un código inválido");
					((TextView) findViewById(R.id.lowerText))
							.setText(R.string.go_to_poi_and_read_code);
					nextTaskMap = (ImageView) findViewById(R.id.nextTaskMap);
					
					//TODO IMAGEN
					nextTaskMap.setImageURI(Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+"/ResuelvoExplorando/Configuracion/Imagenes/"
							+ context.getUser().getNextTask().getName()+".png"));

					
					//nextTaskMap.setImageResource(getResources().getIdentifier(
					//		context.getUser().getNextTask().getName(),
					//		"drawable", getPackageName()));

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

				// Handle successful scan
			} else if (resultCode == RESULT_CANCELED)
			{
				submit("Se cerró scanner");
				// Handle cancel
			}
		}
	}

	public void scannerOnClick(View view)
	{
		/*Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE",
				"QR_CODE_MODE");
		startActivityForResult(intent, 0);*/
		Intent i = new Intent(this, Escaner.class);
		startActivityForResult(i, 0);
		submit("Abre el scanner");
	}

	public void onBackPressed()
	{
	}

}
