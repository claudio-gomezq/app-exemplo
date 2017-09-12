package com.example.claudio.myapplication;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import com.example.claudio.myapplication.data.LambdaInterface;
import com.example.claudio.myapplication.data.ListAsyncFromURL;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView)findViewById(R.id.list);
        Button button = (Button) findViewById(R.id.boton);
        final MainActivity main = this;
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String url = "http://www.quieromimascota.tk/RegionComuna/cargarRegion";
                String attributes[] = {"id", "region"};

                final View view1 = view;
                ListAsyncFromURL listAsyncFromURL = new ListAsyncFromURL(main, url, attributes,null,
                        new LambdaInterface(){
                            @Override
                            public void function(ArrayList<HashMap<String, String>> list) {
                                ListAdapter adapter = new SimpleAdapter(MainActivity.this, list,
                                        R.layout.list_item_region, new String[]{"id","region"},
                                        new int[]{R.id.id, R.id.nombre_region});
                                listView.setAdapter(adapter);
                            }
                        });
                listAsyncFromURL.execute();
            }
        });
    }

    public int sumar(){
        int number1 = Integer.parseInt(((EditText) findViewById(R.id.text)).getText().toString());
        int number2 = Integer.parseInt(((EditText) findViewById(R.id.text2)).getText().toString());
        return number1 + number2;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
