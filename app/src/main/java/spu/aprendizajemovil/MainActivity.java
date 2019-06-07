package spu.aprendizajemovil;

import spu.aprendizajemovil.model.PrototypeContext;
import spu.aprendizajemovil.model.JasonModel.EActivityFromJson;
import spu.aprendizajemovil.utils.JsonLoader;
import spu.aprendizajemovil.utils.JsonToPositioned;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Locale;

import static spu.aprendizajemovil.utils.Submitter.createLog;
import static spu.aprendizajemovil.utils.Submitter.submit;


public class MainActivity extends Activity
{

	protected static final String SELECTED_TASK = null;
	private Button button;
	private Activity activity = this;
	PrototypeContext context;
	public EActivityFromJson jsonEA;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		/* carga de datos desde el XML
		context = new PrototypeContext();
		new XMLParser().parse(this, context);*/

		//prueba carga JSON
		this.tienePermisoDeLectura();
		String languaje = jsonEA.getLanguaje();
		JsonToPositioned jsonToPositioned = new JsonToPositioned(jsonEA);
		context = new PrototypeContext(jsonToPositioned.getPositionedActivity(),jsonToPositioned.getPositionedDeposits(),jsonToPositioned.getPositionedElements());

		setLocale(languaje);


		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		createLog();

		//Inicia tutorial
		Intent i = new Intent(this, CarruselActivity.class);
		startActivity(i);

		// para el prototipo
		context.getUser().setCurrentActivity(context.getPositionedActivity());

		((TextView) findViewById(R.id.activityGoalText)).setText(context
				.getUser().getCurrentActivity().getGoal());

		button = (Button) findViewById(R.id.startGame);
		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(activity, ReadTaskActivity.class);
				intent.putExtra("prototypeContext", context);
				finish();
				submit("Inicia el juego");
				startActivity(intent);
			}
		});
	}
	public void setLocale(String lang) {
		String languageToLoad  = lang; // your language
		Locale locale = new Locale(languageToLoad);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config,
				getBaseContext().getResources().getDisplayMetrics());
	}

	public void onBackPressed()
	{
		// Toast.makeText(getApplicationContext(), "back",
		// Toast.LENGTH_SHORT).show();
	}


	//Chequeo de permisos
	private static final int REQUEST_WRITE_STORAGE = 112;

	public void tienePermisoDeLectura(){
		boolean hasPermission = (ContextCompat.checkSelfPermission(activity,
				Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
		if (!hasPermission) {
			ActivityCompat.requestPermissions(this,
					new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
					REQUEST_WRITE_STORAGE);
		} else {
			JsonLoader loader = new JsonLoader();
			this.jsonEA= loader.load(Environment.getExternalStorageDirectory()+"/ResuelvoExplorando/Configuracion/configuracionResuelvoExplorando.json");
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode)
		{
			case REQUEST_WRITE_STORAGE: {
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
				{
					//reload my activity with permission granted or use the features what required the permission
					JsonLoader loader = new JsonLoader();
					this.jsonEA= loader.load(Environment.getExternalStorageDirectory()+"/ResuelvoExplorando/Configuracion/configuracionResuelvoExplorando.json");
				} else
				{
					Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
				}
			}
		}

	}
}
