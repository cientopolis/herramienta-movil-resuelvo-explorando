package spu.aprendizajemovil;

import spu.aprendizajemovil.model.PrototypeContext;
import spu.aprendizajemovil.model.activityLayer.Task;
import spu.aprendizajemovil.model.positionLayer.PositionedDeposit;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import spu.aprendizajemovil.R;

import static spu.aprendizajemovil.utils.Submitter.submit;

public class EndTask extends Activity
{
	PrototypeContext context;
	Button nextTaskButton;
	Button openDepositsButton;
	TextView textMain;
	TextView textSub;
	TextView textQuestion;
	ImageView imageQuestion;
	RelativeLayout questionLayout;
	Activity activity = this;
	PrototypeContext contextActual;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end_task);

		Intent i = getIntent();
		this.context = (PrototypeContext) i
				.getSerializableExtra("prototypeContext");
		
		textMain = (TextView) findViewById(R.id.textMain);
		textSub = (TextView) findViewById(R.id.textSub);
		openDepositsButton = (Button) findViewById(R.id.openDepositsButton);
		textQuestion = (TextView) findViewById(R.id.textView2);
		imageQuestion = (ImageView) findViewById(R.id.questionMarkImage);
		
		if (context.getUser().hasNextTask() && context.getUser().getBagOfElements().isEmpty())
		{
			textMain.setText(R.string.you_finished_task_without_elements);
			textSub.setText(R.string.end_task_without_elements);
			openDepositsButton.setVisibility(View.GONE);
			textQuestion.setVisibility(View.GONE);
			imageQuestion.setVisibility(View.GONE);
		}

		if (!context.getUser().hasNextTask())
		{
			textMain.setText(R.string.you_finished_all_tasks);
			textSub.setText(R.string.keep_depositing_or_end_game);
			nextTaskButton = (Button) findViewById(R.id.nextTaskButton);
			nextTaskButton.setText(R.string.end_game);
			submit("Se terminó la última tarea");
			if (context.getUser().getBagOfElements().isEmpty())
			{
				textMain.setText(R.string.you_finished_last_task_without_elements);
				textSub.setText(R.string.end_tasks_without_elements);
				textQuestion.setVisibility(View.GONE);
				imageQuestion.setVisibility(View.GONE);
				openDepositsButton.setVisibility(View.GONE);
				
				
				nextTaskButton.setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						Intent intent = new Intent(
								activity, EndGame.class);
						intent.putExtra("prototypeContext",
								context);
						finish();
						startActivity(intent);
					}
				});
				
			}
			
			else{
				nextTaskButton.setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						new AlertDialog.Builder(activity)
								.setTitle(R.string.end_game)
								.setMessage(
										R.string.bag_is_not_empty)
								.setPositiveButton(R.string.yes,
										new DialogInterface.OnClickListener()
										{
											public void onClick(
													DialogInterface dialog,
													int which)
											{
												Intent intent = new Intent(
														activity, EndGame.class);
												intent.putExtra("prototypeContext",
														context);
												finish();
												startActivity(intent);
											}
										})
								.setNegativeButton(R.string.no,
										new DialogInterface.OnClickListener()
										{
											public void onClick(
													DialogInterface dialog,
													int which)
											{
												/*Intent intent = new Intent(
														"com.google.zxing.client.android.SCAN");
												intent.putExtra(
														"com.google.zxing.client.android.SCAN.SCAN_MODE",
														"QR_CODE_MODE");
												startActivityForResult(intent, 0);*/
												Intent i = new Intent(activity, Escaner.class);
												startActivityForResult(i, 0);
												submit("Abre el scanner");
											}
										}).setIcon(R.drawable.alert_small).show();
					}
				});
			}

		}
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu)
	// {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.end_task, menu);
	// return true;
	// }

	public void openDepositsView(View view)
	{
		Intent intent = new Intent(this, ShowDepositsMap.class);

		// intent.putExtra(EXTRA_MESSAGE, message);
		intent.putExtra("prototypeContext", context);
		finish();
		startActivity(intent);
	}

	public void nextTask(View view)
	{
		Intent intent = new Intent(this, ReadTaskActivity.class);
		intent.putExtra("prototypeContext", context);
		submit("Se solicitó ir a otra tarea");
		finish();
		startActivity(intent);

	}
	public void showUsersEvaluations(View view)
	{
		// Intent intent = new Intent(this, ShowUsersEvaluation.class);
		submit("Solicita ver su desempeño");
		Intent intent = new Intent(this, TaskEvaluationList.class);
		intent.putExtra("prototypeContext", context);
		// finish();
		startActivity(intent);
	}

	public void onBackPressed()
	{
	}

	public void onActivityResult(int requestCode, int resultCode, Intent res)
	{
		if (requestCode == 0)
		{
			if (resultCode == Activity.RESULT_OK)
			{
				// lo que retorna el "emulador" del scanner
				String contents = res.getStringExtra("SCAN_RESULT");

				// String format = res.getStringExtra("SCAN_RESULT_FORMAT");

				PositionedDeposit deposit = context.existsDeposit(contents);
				if (deposit != null)
				{
					Intent intent = new Intent(this, ShowDeposit.class);
					intent.putExtra("prototypeContext", context);
					intent.putExtra("deposit", deposit);
					submit("Se solicitó ir a" + deposit.getName());
					finish();
					startActivity(intent);
				} else
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					submit("Se leyó un código de depósito inválido");
					builder.setMessage(R.string.invalid_code)
							.setCancelable(false)
							.setPositiveButton("OK",
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

				// Handle successful scan
			} else if (resultCode == RESULT_CANCELED)
			{
				// Handle cancel
			}
		}
	}

}
