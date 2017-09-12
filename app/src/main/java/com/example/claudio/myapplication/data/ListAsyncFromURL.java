package com.example.claudio.myapplication.data;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.example.claudio.myapplication.MainActivity;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;


public class ListAsyncFromURL extends AsyncTask<Void, Void, Void>{

    private final MainActivity mainActivity;
    private final String url;
    private final String attributes[];
    private final String nameArray;
    private final LambdaInterface lambdaInterface;
    private  ArrayList<HashMap<String, String>> list;

    private final String TAG = ListAsyncFromURL.class.getSimpleName();

    public ListAsyncFromURL(MainActivity mainActivity, String url,
                            String attributes[], String nameArray, LambdaInterface lambdaInterface){
        this.url = url;
        this.attributes = attributes;
        this.mainActivity = mainActivity;
        this.nameArray = nameArray;
        this.lambdaInterface = lambdaInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler httpHandler = new HttpHandler();
        String jsonString  = httpHandler.makeServiceCall(this.url);
        try {
            DynamicObjectFromJSONString dynamicObject = new DynamicObjectFromJSONString(jsonString, attributes);
            list = dynamicObject.toArray(this.nameArray);
        } catch (final JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
            mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mainActivity.getApplicationContext(),
                            "Json parsing error: " + e.getMessage(),
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        }catch (final IllegalArgumentException e){
            Log.e(TAG, "Couldn't get json from server.");
            mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mainActivity.getApplicationContext(),
                            "No podemos obtener el json desde el servidor",
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        lambdaInterface.function(list);

    }


}
