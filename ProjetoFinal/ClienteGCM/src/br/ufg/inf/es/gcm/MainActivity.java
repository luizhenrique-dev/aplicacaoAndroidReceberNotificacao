package br.ufg.inf.es.gcm;
//Final
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {

	CompartilharServidorExterno appUtil;
	String regId;
	AsyncTask<Void, Void, String> shareRegidTask;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		appUtil = new CompartilharServidorExterno();

		regId = getIntent().getStringExtra("regId");
		Log.d("MainActivity", "regId: " + regId);

		final Context context = this;
		shareRegidTask = new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String result = appUtil.compartilhaRegIdComServidorApp(context, regId);
				return result;
			}

			@Override
			protected void onPostExecute(String result) {
				shareRegidTask = null;
				Toast.makeText(getApplicationContext(), result,
						Toast.LENGTH_LONG).show();
			}

		};
		shareRegidTask.execute(null, null, null);
	}

}