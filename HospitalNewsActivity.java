package com.example.user.gridl_cardview.HospitalNews;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.gridl_cardview.Hospital.HospitalActivity;
import com.example.user.gridl_cardview.Hospital.Hospital_arrayAdapter;
import com.example.user.gridl_cardview.Info;
import com.example.user.gridl_cardview.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class HospitalNewsActivity extends AppCompatActivity {

    String url = Info.URL+"showHos_news";
    RecyclerView rv_hosNews;
    ArrayList<ProductNews> list = new ArrayList<>();
    SwipeRefreshLayout swip_RV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_news);

        rv_hosNews = (RecyclerView) findViewById(R.id.rv_hosNews);
        swip_RV = (SwipeRefreshLayout) findViewById(R.id.swip_RV);
        callApi();
        swip_RV.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               callApi();

                Toast.makeText(HospitalNewsActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
            }
        });


    }

void callApi(){
    list.clear();
    RequestQueue rq = Volley.newRequestQueue(this);
    JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {


            for (int x=0; x<response.length(); x++){


                try {
                    list.add(new ProductNews(
                            response.getJSONObject(x).getString("pic"),
                            response.getJSONObject(x).getString("name"),
                            response.getJSONObject(x).getString("News")

                    ));

                } catch (JSONException e) {

                    Toast.makeText(HospitalNewsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            AdapterHos_News adapter = new AdapterHos_News(list,HospitalNewsActivity.this);
            rv_hosNews.setAdapter(adapter);
            rv_hosNews.setLayoutManager(new LinearLayoutManager(HospitalNewsActivity.this));
            swip_RV.setRefreshing(false);

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(HospitalNewsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

        }
    });

    rq.add(jar);
}
    }



