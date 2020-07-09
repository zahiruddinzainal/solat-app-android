package com.zvhir.solatmy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private TextView xImsak;
    private TextView xSubuh;
    private TextView xSyuruk;
    private TextView xZohor;
    private TextView xAsar;
    private TextView xMaghrib;
    private TextView xIsyak;

    private TextView xBakiWaktu;
    private TextView xTarikh;
    private TextView xZon;

    private Button xMenu;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xImsak = findViewById(R.id.imsak);
        xSubuh = findViewById(R.id.subuh);
        xSyuruk = findViewById(R.id.syuruk);
        xZohor = findViewById(R.id.zohor);
        xAsar = findViewById(R.id.asar);
        xMaghrib = findViewById(R.id.maghrib);
        xIsyak = findViewById(R.id.isyak);
        xBakiWaktu = findViewById(R.id.bakiWaktu);

        xMenu = findViewById(R.id.menu);

        //  xTarikh = findViewById(R.id.tarikh);//   xZon = findViewById(R.id.zon);

        xMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(MainActivity.this, Menu.class );
                startActivity(x);
            }
        });

        mQueue = VolleySingleton.getInstance(this).getRequestQueue();
        String url = "https://api.azanpro.com/times/today.json?zone=jhr04&format=12-hour";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            //------------------------- Maklumat --------------------------

                            /*

                            String x = response.getString(" start");
                            xTarikh.append(x);

                            String y = response.getString(" locations");
                            xZon.append(y);

                             */

                            //------------------------- Waktu --------------------------

                            Calendar calendar = Calendar.getInstance();
                            int hour12hrs = calendar.get(Calendar.HOUR);
                            int minutes = calendar.get(Calendar.MINUTE);

                            String hour = String.valueOf(hour12hrs);
                            String min = String.valueOf(minutes);

                            xBakiWaktu.setText(hour + min);



                            JSONObject referWaktu  = response.getJSONObject("prayer_times");

                            String a = referWaktu.getString("imsak");
                            xImsak.append(a.toUpperCase());

                            String b = referWaktu.getString("subuh");
                            xSubuh.append(b.toUpperCase());

                            String c = referWaktu.getString("syuruk");
                            xSyuruk.append(c.toUpperCase());

                            String d = referWaktu.getString("zohor");
                            xZohor.append(d.toUpperCase());

                            String e = referWaktu.getString("asar");
                            xAsar.append(e.toUpperCase());

                            String f = referWaktu.getString("maghrib");
                            xMaghrib.append(f.toUpperCase());

                            String g = referWaktu.getString("isyak");
                            xIsyak.append(g.toUpperCase());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);


    }

}