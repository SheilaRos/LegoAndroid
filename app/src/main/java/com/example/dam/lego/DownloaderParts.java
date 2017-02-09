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
    //Creamos las variables necesarias
    private Context context;
    private String codigo;
    //creamos las variables para poder pasar de string a gson y llenar asi las clases
    private GsonBuilder builder ;
    private Gson gson;
    //Creamos una variable MainActivity con setters y getters para devolver la descarga en el MainActivity
    private MainActivity mainActivity;
    public MainActivity getMainActivity() {
        return mainActivity;
    }
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    //Creamos el constructor
    public DownloaderParts(Context context, String codigo) {
        this.codigo=codigo;
        this.context = context;
        this.builder = new GsonBuilder();
        this.gson = builder.create();
    }
    //TODO use a callback interface
    //creamos el getter y el setter del tema que busca el usuario
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    //creamos el pDialog para ver el progreso de la descarga
    private ProgressDialog pDialog;
    //Función para el proceso de la descarga
    @Override protected void onPreExecute() {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Downloading file. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(true);
        pDialog.setTitle("Please wait...");
        String msg = "Downloading...";
        pDialog.setMessage(msg);
        pDialog.setCancelable(true);
        pDialog.show();
    }
    //Función de descarga, retornamos la string que descargamos de la web, en este caso descargamos la string de un json
	@Override protected String doInBackground(Void... params) {
		int count;
		try {
			URL url = new URL("http://rebrickable.com/api/v3/lego/sets/"+this.codigo+"/parts/?key=QuZPTD9gr7");
			URLConnection connection = url.openConnection();
			connection.connect();
            int lengthOfFile = connection.getContentLength();
            pDialog.setMax(lengthOfFile);
			InputStream input = new BufferedInputStream(url.openStream(), 8192);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte data[] = new byte[1024];
            long total = 0;
			while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress("" + (int) ((total * 100) / lengthOfFile));
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
    //progreso de la descarga
    protected void onProgressUpdate(String... progress) {
        pDialog.setProgress(Integer.parseInt(progress[0]));
    }
    //Devolvemos la descarga al main
    @Override public void onPostExecute(String json) {
        //Creamos un PartList donde pasamos la descarga a gson
        PartsList partsList = gson.fromJson(json,  PartsList.class);
        pDialog.dismiss();
        //Devolvemos a la función del MainActivity "notifyPartsList" el partsList que hemos llenado con la descarga
        mainActivity.notifyPartsList(partsList);
    }

}