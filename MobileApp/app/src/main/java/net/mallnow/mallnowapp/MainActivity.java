package net.mallnow.mallnowapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.mallnow.mallnowapp.adapters.MallListAdapter;
import net.mallnow.mallnowapp.models.Mall;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL ="http://localhost/test.html";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Mall> mallList;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.MallList);
        jsonrequest();
    }

    public void jsonrequest() {


       request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;


                for (int i = 0 ; i<response.length();i++) {

                    //Toast.makeText(getApplicationContext(),String.valueOf(i),Toast.LENGTH_SHORT).show();

                    try {

                        jsonObject = response.getJSONObject(i);
                        Mall mall = new Mall();

                        mall.setMallName(jsonObject.getString("MallName"));
                        mall.setCapacity(jsonObject.getString("Capacity"));
                        mall.setAddress(jsonObject.getString("Address")) ;
                        mall.setMobileIcon(jsonObject.getString("MobileIcon"));

                        //Toast.makeText(MainActivity.this,anime.toString(),Toast.LENGTH_SHORT).show();
                       mallList.add(mall);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                Toast.makeText(MainActivity.this,"Size of Mall "+String.valueOf(mallList.size()),Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this,mallList.get(1).toString(),Toast.LENGTH_SHORT).show();

                setuprecyclerview(mallList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }


    /*private void jsonrequest(){
        final JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray Malls = response.getJSONArray("Malls");
                            for (int i = 0; i < Malls.length(); i++) {
                                JSONObject c = Malls.getJSONObject(i);

                                Mall mall = new Mall();
                                mall.setMallName(c.getString("MallName"));

                                String show = c.getString("id");
                                mall.setMallName(c.getString("MallName"));
                                mall.setAddress(c.getString("Address"));
                                mall.setCapacity(c.getString("Capacity"));
                                mall.setMobileIcon(c.getString("MobileIcon"));

                                mallList.add(mall);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                 new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
                 }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> parameters = new HashMap<String,String>();
                parameters.put("op","Select");
                return parameters;
            }
        };

        requestQueue.add(jsonObjectRequest);

    } */

    /*private void jsonrequest() {
        request= new JsonObjectRequest(Request.Method.GET,JSON_URL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray Malls = response.getJSONArray("Malls");
                            for (int i = 0; i < Malls.length(); i++) {
                                JSONObject jsonObject = Malls.getJSONObject(i);

                                Mall mall = new Mall();
                                mall.setMallName(response.getString("MallName"));
                                mall.setAddress(response.getString("Address"));
                                mall.setCapacity(response.getString("Capacity"));
                                mall.setMobileIcon(response.getString("MobileIcon"));

                                mallList.add(mall);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<String,String>();
                parameters.put("op","Select");
                return parameters;
            }
        };


        setuprecyclerview(mallList);
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    } */

    private void setuprecyclerview(List<Mall> mallList) {
        MallListAdapter mallListAdapter =new MallListAdapter(this,mallList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mallListAdapter);
    }
}
