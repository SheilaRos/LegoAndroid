package com.example.dam.lego;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebDownloader extends AsyncTask<Void, String, Boolean> {

    private Context context;
    private String tema;
    public WebDownloader(Context context, String tema) {
        this.tema=tema;
        this.context = context;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    private OnCurrenciesLoadedListener listener = null;
	public void setOnCurrenciesLoadedListener(OnCurrenciesLoadedListener listener) {
		this.listener = listener;
	}
    private ProgressDialog pDialog;

    @Override protected void onPreExecute() {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Downloading file. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(true);
        pDialog.setTitle("Please wait...");
        String msg = "Downloading...";
        pDialog.setMessage(msg);
        pDialog.setCancelable(true);
        pDialog.show();
    }

	@Override protected Boolean doInBackground(Void... params) {
		int count;
		try {
			URL url = new URL("http://rebrickable.com/api/v3/lego/sets/?key=QuZPTD9gr7&search="+this.tema);
			URLConnection connection = url.openConnection();
			connection.connect();
			InputStream input = new BufferedInputStream(url.openStream(), 8192);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte data[] = new byte[1024];
			long total = 0;
			while ((count = input.read(data)) != -1) {
				total += count;
				output.write(data, 0, count);
			}

            input.close();
			output.flush();
            String json = new String(output.toByteArray());

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            String prueba = "{\n" +
                    "            \"set_num\": \"7958-17\",\n" +
                    "            \"last_modified_dt\": \"2015-11-24T10:01:51.219706Z\"\n" +
                    "        }";


            Prueba prueba1 = gson.fromJson(prueba,  Prueba.class);
            Log.d("GSON PRUEBA", prueba1.toString());
		} catch (Exception e) {
			Log.e("Error: ", e.getMessage());
            return false;
		}

		return true;
	}

    protected void onProgressUpdate(String... progress) {
        pDialog.setProgress(Integer.parseInt(progress[0]));
    }

	@Override public void onPostExecute(Boolean result) {
        pDialog.dismiss();
		if (listener != null) listener.onCurrenciesLoaded(result);
	}

    public interface OnCurrenciesLoadedListener {
        public void onCurrenciesLoaded(boolean ok);
    }
}