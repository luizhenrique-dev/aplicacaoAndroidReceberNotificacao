package br.ufg.inf.es.gcm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class RegistroActivity extends Activity {

	Button btnRegistroGCM;
	Button btnCompartilharApp;
	GoogleCloudMessaging gcm;
	Context context;
	String regId;

	private static final String REG_ID = "regId";
	private static final String APP_VERSION = "appVersion";
	static final String TAG = "Activity Registro";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registro);

		context = getApplicationContext();

		btnRegistroGCM = (Button) findViewById(R.id.btnRegistroGCM);
		btnRegistroGCM.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(regId)) {
					regId = registrarGCM();
					Log.d("RegistroActivity", "GCM RegId: " + regId);
				} else {
					Toast.makeText(getApplicationContext(),
							"Já está registrado com o Servidor GCM!",
							Toast.LENGTH_LONG).show();
				}

			}
		});

		btnCompartilharApp = findViewById(R.id.btnCompartilharApp);
		btnCompartilharApp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(regId)) {
					Toast.makeText(getApplicationContext(),
							"RegId está vazio!", Toast.LENGTH_LONG).show();
				} else {
					Intent i = new Intent(getApplicationContext(),
							MainActivity.class);
					i.putExtra("regId", regId);
					Log.d("RegistroActivity",
							"onClick do Compartilhamento: Antes de iniciar a main activity.");
					startActivity(i);
					finish();
					Log.d("RegistroActivity",
							"onClick do Compartilhamento: Depois de finalizar.");
				}

			}
		});
	}

	public String registrarGCM() {
		gcm = GoogleCloudMessaging.getInstance(this);
		regId = getRegistrationId(context);

		if (TextUtils.isEmpty(regId)) {
			registerInBackground();
			
			Log.d("RegistroActivity",
					"registrarGCM - Registrado com sucesso no Servidor GCM - regId: "
							+ regId);
		} else {
			Toast.makeText(getApplicationContext(),
					"RegId já disponível. RegId: " + regId,
					Toast.LENGTH_LONG).show();
		}
		return regId;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registro, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_registro,
					container, false);
			return rootView;
		}
	}

}
