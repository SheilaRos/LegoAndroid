package com.example.dam.lego;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloaderParts extends AsyncTask<Void, String, String> {

    private Context context;
    private String codigo;
    private GsonBuilder builder ;
    private Gson gson;
    private MainActivity mainActivity;
    public MainActivity getMainActivity() {
        return mainActivity;
    }
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    public DownloaderParts(Context context, String codigo) {
        this.codigo=codigo;
        this.context = context;
        this.builder = new GsonBuilder();
        this.gson = builder.create();
    }
    //TODO use a callback interface
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

	@Override protected String doInBackground(Void... params) {
		int count;
		try {
			URL url = new URL("http://rebrickable.com/api/v3/lego/sets/"+this.codigo+"/parts/?key=QuZPTD9gr7");
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
            return json;

		} catch (Exception e) {
			Log.e("Error: ", e.getMessage());
            return e.toString();
		}
	}
    protected void onProgressUpdate(String... progress) {
        pDialog.setProgress(Integer.parseInt(progress[0]));
    }
    @Override public void onPostExecute(String json) {
        PartsList partsList = gson.fromJson(json,  PartsList.class);
        pDialog.dismiss();
        mainActivity.notifyPartsList(partsList);
    }

}