package br.ufg.inf.es.gcm;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
//Final
public class RegistroActivity extends Activity {

	Button btnRegistroGCM;
	Button btnCompartilharApp;
	GoogleCloudMessaging gcm;
	Context context;
	String regId;

	private static final String REG_ID = "regId";
	private static final String APP_VERSION = "appVersion";
	static final String TAG = "RegistroActivity";

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

		btnCompartilharApp = (Button) findViewById(R.id.btnCompartilharApp);
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
		regId = getIdRegistro(context);

		if (TextUtils.isEmpty(regId)) {
			registroEmSegundoPlano();

			Log.d("RegistroActivity",
					"registrarGCM - Registrado com sucesso no Servidor GCM - regId: "
							+ regId);
		} else {
			Toast.makeText(getApplicationContext(),
					"RegId já disponível. RegId: " + regId, Toast.LENGTH_LONG)
					.show();
		}
		return regId;
	}

	private String getIdRegistro(Context context) {
		final SharedPreferences prefs = getSharedPreferences(
				MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
		String registrationId = prefs.getString(REG_ID, "");
		if (registrationId.isEmpty()) {
			Log.i(TAG, "Registro não encontrado.");
			return "";
		}
		int registeredVersion = prefs.getInt(APP_VERSION, Integer.MIN_VALUE);
		int currentVersion = getVersaoApp(context);
		if (registeredVersion != currentVersion) {
			Log.i(TAG, "Versão do App modificada.");
			return "";
		}
		return registrationId;
	}

	private static int getVersaoApp(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			Log.d("RegistroActivity", "Erro inesperado!" + e);
			throw new RuntimeException(e);
		}
	}

	private void registroEmSegundoPlano() {
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String msg = "";
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(context);
					}
					regId = gcm.register(Config.GOOGLE_PROJECT_ID);
					Log.d("RegistroActivity", "registroEmSegundoPlano - regId: "
							+ regId);
					msg = "Dispositivo registrado, ID de registro =" + regId;

					storeRegistrationId(context, regId);
				} catch (IOException ex) {
					msg = "Error :" + ex.getMessage();
					Log.d("RegistroActivity", "Error: " + msg);
				}
				Log.d("RegistroActivity", "AsyncTask completed: " + msg);
				return msg;
			}

			@Override
			protected void onPostExecute(String msg) {
				Toast.makeText(getApplicationContext(),
						"Registrado com Servidor GCM." + msg, Toast.LENGTH_LONG)
						.show();
			}
		}.execute(null, null, null);
	}

	private void storeRegistrationId(Context context, String regId) {
		final SharedPreferences prefs = getSharedPreferences(
				MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
		int appVersion = getVersaoApp(context);
		Log.i(TAG, "Salvando regId no aplicativo versão " + appVersion);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(REG_ID, regId);
		editor.putInt(APP_VERSION, appVersion);
		editor.commit();
	}
}