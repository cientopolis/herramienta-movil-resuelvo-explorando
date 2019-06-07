package spu.aprendizajemovil;

import spu.aprendizajemovil.model.PrototypeContext;
import spu.aprendizajemovil.model.activityLayer.Deposit;
import spu.aprendizajemovil.model.positionLayer.PositionedDeposit;

import spu.aprendizajemovil.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static spu.aprendizajemovil.utils.Submitter.submit;

public class EndDeposit extends Activity
{

	private Button nextTaskButton;
	private TextView endDepositText;
	private Button openDepositsButton;
	private PrototypeContext context;
	private Activity activity = this;
	private RelativeLayout questionLayout;
	private Boolean hasDepositedElement;
	private TextView evaluationSubtitle;
	public PositionedDeposit lastDepo;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end_deposit);

		Intent i = getIntent();
		this.lastDepo = (PositionedDeposit) i.getExtras().get("lastDepoOpened");
		this.context = (PrototypeContext) i
				.getSerializableExtra("prototypeContext");

		this.hasDepositedElement = (Boolean) i
				.getSerializableExtra("hasDepositedElement");

		nextTaskButton = (Button) findViewById(R.id.nextTaskButton);
		endDepositText = (TextView) findViewById(R.id.endDepositText);
		questionLayout = (RelativeLayout) findViewById(R.id.questionLayout);
		evaluationSubtitle = (TextView) findViewById(R.id.evaluationSubtitle);
		openDepositsButton = (Button) findViewById(R.id.openDepositsButton);

		if (!hasDepositedElement)
		{
			evaluationSubtitle.setText(R.string.no_element_has_been_deposited);
		}

		questionLayout.setVisibility(View.VISIBLE);

		if (context.getUser().isBagEmpty())
		{
			openDepositsButton.setVisibility(View.GONE);
		}
		if (context.getUser().hasNextTask())
		{
			if (context.getUser().isBagEmpty())
			{
				endDepositText
						.setText(R.string.all_elements_have_been_deposited_go_to_next_task);
				questionLayout.setVisibility(View.INVISIBLE);
			} else
			{
				endDepositText
						.setText(R.string.keep_depositing_or_go_to_next_task);
			}
			nextTaskButton.setText(R.string.go_to_next_task);
			nextTaskButton.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					Intent intent = new Intent(activity, ReadTaskActivity.class);
					intent.putExtra("prototypeContext", context);
					finish();
					startActivity(intent);
				}
			});
		} else
		{
			endDepositText
					.setText(R.string.no_more_tasks_keep_depositing_or_end_game);
			nextTaskButton.setText(R.string.end_game);

			if (context.getUser().isBagEmpty())
			{
				Intent intent = new Intent(activity, EndGame.class);
				intent.putExtra("prototypeContext", context);
				finish();
				startActivity(intent);
			} else
			{
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
												intent.putExtra(
														"prototypeContext",
														context);
												finish();
												submit("Finaliza el juego");
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
												startActivityForResult(intent,
														0);*/
												Intent i = new Intent(activity, Escaner.class);
												startActivityForResult(i, 0);
												submit("Abre el scanner");
											}
										}).setIcon(R.drawable.alert_small)
								.show();
					}
				});
			}
		}
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu)
	// {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.end_deposit, menu);
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
		submit("Abre el scanner");
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
				String contents = res.getStringExtra("SCAN_RESULT");
				// String format = res.getStringExtra("SCAN_RESULT_FORMAT");

				PositionedDeposit deposit = context.existsDeposit(contents);
				if (deposit != null)
				{
					Intent intent = new Intent(this, ShowDeposit.class);
					intent.putExtra("prototypeContext", context);
					intent.putExtra("deposit", deposit);
					finish();
					startActivity(intent);
				} else
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setMessage(R.string.read_code_is_invalid)
							.setCancelable(false)
							.setPositiveButton(R.string.ok,
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
	public void showUsersEvaluations(View view)
	{
		// Intent intent = new Intent(this, ShowUsersEvaluation.class);
		submit("Solicita ver su desempeño");
		Intent intent = new Intent(this, DepositEvaluationList.class);
		intent.putExtra("prototypeContext", context);
		intent.putExtra("lastDepoOpened",lastDepo);
		// finish();
		startActivity(intent);
	}

}
