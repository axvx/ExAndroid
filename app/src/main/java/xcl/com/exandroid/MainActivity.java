package xcl.com.exandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    private RequestQueue mRequestQueue;
    private RequestQueue queue;
    private StringRequest mStringRequest;
    private String url = "";
    MyRecyclerViewAdapter adapter;
    ArrayList<clase_zapatos> zapatos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        }

    @Override
    public void onItemClick(View view, int position) {
       // Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
    private void sendAndRequestResponse(String criteria) {

        url="https://shoppapp.liverpool.com.mx/appclienteservices/services/v3/plp?force-plp=true&search-string='" +criteria+"'&page-number=1&number-of-items-per-page=6";

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               // Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen


                try {
                    JSONObject object=new JSONObject(response);



                    JSONArray array= object.getJSONObject("plpResults").getJSONArray("records");
                    for(int i=0;i<array.length();i++) {
                        JSONObject object1=array.getJSONObject(i);
                        String name =object1.getString("productDisplayName");
                        String price =object1.getString("listPrice");
                        String place =object1.getString("isMarketPlace");

                        // Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();

                        // data to populate the RecyclerView with
                        clase_zapatos nuevo_zapato=new clase_zapatos(name,price,place);

                        zapatos.add(nuevo_zapato);

                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(mStringRequest);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, zapatos);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void BuscarZapatos(View v){

        TextView txt_buscar;

        txt_buscar=(TextView)findViewById(R.id.txt_busca);



        sendAndRequestResponse(txt_buscar.getText().toString());



    }
}
