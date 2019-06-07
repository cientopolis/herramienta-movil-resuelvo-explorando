package spu.aprendizajemovil;

import spu.aprendizajemovil.model.PrototypeContext;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;

import spu.aprendizajemovil.R;

import static spu.aprendizajemovil.utils.Submitter.submit;

public class EndGame extends Activity
{

	PrototypeContext context;
	Activity activity = this;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end_game);

		Intent i = getIntent();
		this.context = (PrototypeContext) i
				.getSerializableExtra("prototypeContext");
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu)
	// {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.end_game, menu);
	// return true;
	// }

	public void showUsersEvaluations(View view)
	{
		// Intent intent = new Intent(this, ShowUsersEvaluation.class);
		submit("Solicita ver su desempeño");
		Intent intent = new Intent(this, EvaluationList.class);
		intent.putExtra("prototypeContext", context);
		// finish();
		startActivity(intent);
	}

	public void closeGame(View view)
	{
		new AlertDialog.Builder(this)
				.setTitle(R.string.end_game)
				.setMessage(
						R.string.exit_game_confirmation_text)
				.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener()
						{
							public void onClick(
									DialogInterface dialog,
									int which)
							{
								submit("Sale del juego");
								activity.finish();
								Process.killProcess(Process.myPid() ); 
							}
						})
				.setNegativeButton(R.string.no,
						new DialogInterface.OnClickListener()
						{
							public void onClick(
									DialogInterface dialog,
									int which)
							{
								
							}
						})
				.setIcon(R.drawable.alert_small).show();
	}

	public void onBackPressed()
	{
	}

}
