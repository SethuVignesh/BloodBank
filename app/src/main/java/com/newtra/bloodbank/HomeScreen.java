package com.newtra.bloodbank;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class HomeScreen extends Activity implements View.OnClickListener {
	Button btnAp, btnAn, btnBp, btnBn, btnABp, btnABn, btnOp, btnOn;
	ListView lv;
	DBHandler db;
	MySeekBar mSeekBarDistance;
	private TextView textViewPopup,textViewDistance;
	private int oldLocation = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		lv = (ListView) findViewById(R.id.lVDonors);
		btnAp = (Button) findViewById(R.id.btnAp);
		btnAn = (Button) findViewById(R.id.btnAn);
		btnBp = (Button) findViewById(R.id.btnBp);
		btnBn = (Button) findViewById(R.id.btnBn);
		btnABp = (Button) findViewById(R.id.btnABp);
		btnABn = (Button) findViewById(R.id.btnABn);
		btnOp = (Button) findViewById(R.id.btnOp);
		btnOn = (Button) findViewById(R.id.btnOn);
		mSeekBarDistance = (MySeekBar) findViewById(R.id.seekBarDistance);
		textViewPopup = (TextView) findViewById(R.id.textProgress);
		textViewDistance=(TextView)findViewById(R.id.textViewDistance);
		
		mSeekBarDistance.setMax(2000);
		mSeekBarDistance.setProgress(0);
		mSeekBarDistance.setThumb(HomeScreen.this.getResources().getDrawable(
				R.drawable.ic_launcher));
		mSeekBarDistance
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						textViewPopup.setVisibility(View.GONE);

					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						textViewPopup.setVisibility(View.VISIBLE);

					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						// TODO Auto-generated method stub
						RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
								RelativeLayout.LayoutParams.WRAP_CONTENT,
								RelativeLayout.LayoutParams.WRAP_CONTENT);
						p.addRule(RelativeLayout.ABOVE, seekBar.getId());
						Rect thumbRect = mSeekBarDistance.getSeekBarThumb()
								.getBounds();
						p.setMargins(thumbRect.centerX(), 0, 0, 0);
						textViewPopup.setLayoutParams(p);
						textViewPopup.setText(String.valueOf(progress) + " ft.");
						textViewDistance.setText(String.valueOf(progress) + " ft.");
					}
				});

		db = new DBHandler(getApplicationContext());
		// DEBUG purpose
		loadDemoDB(db);

		btnAn.setOnClickListener(this);
		btnAp.setOnClickListener(this);
		btnBp.setOnClickListener(this);
		btnBn.setOnClickListener(this);
		btnABp.setOnClickListener(this);
		btnABn.setOnClickListener(this);
		btnOp.setOnClickListener(this);
		btnOn.setOnClickListener(this);

		loadListView("all");

	}

	public BitmapDrawable writeOnDrawable(int drawableId, String text) {

		Bitmap bm = BitmapFactory.decodeResource(getResources(), drawableId)
				.copy(Bitmap.Config.ARGB_8888, true);

		Paint paint = new Paint();
		paint.setStyle(Style.FILL);
		paint.setColor(Color.BLACK); // Change this if you want other color of
										// text
		paint.setTextSize(20); // Change this if you want bigger/smaller font

		Canvas canvas = new Canvas(bm);
		canvas.drawText(text, 0, bm.getHeight() / 2, paint); // Change the
																// position of
																// the text here

		return new BitmapDrawable(bm);
	}

	private void loadDemoDB(DBHandler db) {
		Donor donor = new Donor("SETHU", "1123456789034567890", "3", "4", "A+",
				"6", "7");
		db.addContactDetails(donor);
		donor = new Donor("VIGNESH", "1123456789034567890", "3", "4", "Ap",
				"6", "7");
		db.addContactDetails(donor);
		donor = new Donor("AADHI", "1123456789034567890", "3", "4", "Ap", "6",
				"7");
		db.addContactDetails(donor);
		donor = new Donor("SURYA", "1234567890", "3", "4", "Ap", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("RAJESH", "1234567890", "3", "4", "Ap", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("RAJA", "1234567890", "3", "4", "An", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("CHERAN", "1234567890", "3", "4", "An", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("MANIKANDAN", "1234567890", "3", "4", "An", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("SUNDARAMM", "1234567890", "3", "4", "An", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("MANIKANDAN", "1234567890", "3", "4", "Bp", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("BABU", "1234567890", "3", "4", "5", "Bp", "7");
		db.addContactDetails(donor);
		donor = new Donor("ABHNASH", "1234567890", "3", "4", "Bp", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("SHRAVAN", "1234567890", "3", "4", "ABp", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("KUMAR", "1234567890", "3", "4", "ABp", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("AMBANI", "1234567890", "3", "4", "ABp", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("MADHUSUDHANA", "1234567890", "3", "4", "ABn", "6",
				"7");
		db.addContactDetails(donor);
		donor = new Donor("RAMBO", "1234567890", "3", "4", "ABn", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("SUMO", "1234567890", "3", "4", "ABn", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("PADHMANABHAN", "1234567890", "3", "4", "ABn", "6",
				"7");
		db.addContactDetails(donor);
		donor = new Donor("PRABHKAR", "1234567890", "3", "4", "Op", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("CHANDRA", "1234567890", "3", "4", "Op", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("ANAND", "1234567890", "3", "4", "Op", "6", "7");
		db.addContactDetails(donor);
		donor = new Donor("VISVAK", "1234567890", "3", "4", "On", "6", "7");
		db.addContactDetails(donor);
		db.addContactDetails(donor);
		donor = new Donor("VISVAK", "1234567890", "3", "4", "Bn", "6", "7");
		// Toast.makeText(getApplicationContext(),db.getDatabaseName(),Toast.LENGTH_LONG).show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_home_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		Intent intent = null;
		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		switch (id) {
		case R.id.action_settings:
			startActivity(new Intent(this, Settings.class));

			break;
		case R.id.action_edit_profile:
			startActivity(new Intent(getApplicationContext(),
					Registration.class));
			break;
		case R.id.action_aboutus:
			// startActivity(new Intent(getApplicationContext(),AboutUs.class));

			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.btnAp:
			loadListView("Ap");
			break;
		case R.id.btnAn:
			loadListView("An");
			break;
		case R.id.btnBp:
			loadListView("Bp");
			break;
		case R.id.btnBn:
			loadListView("Bn");
			break;
		case R.id.btnABp:
			loadListView("ABp");
			break;
		case R.id.btnABn:
			loadListView("ABn");
			break;
		case R.id.btnOp:
			loadListView("Op");
			break;
		case R.id.btnOn:
			loadListView("On");
			break;

		}
	}

	private void loadListView(String type) {
		List<Donor> contacts;// = db.readAllContacts();
		if (type.equals("all")) {
			contacts = db.readAllContacts();
		} else {
			contacts = db.readContactsParticular(type);
		}

		// Initialize Custom Adapter
		CustomAdapter adapter = new CustomAdapter(this, contacts);

		// Set Adapter to ListView
		lv.setAdapter(adapter);

		// See the log int LogCat
		for (Donor c : contacts) {
			String record = "ID=" + c.getID() + " | Name=" + c.getName()
					+ " | " + c.getPhoneNumber();
			Log.d("Record", record);
		}
	}

}
