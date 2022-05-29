package com.example.bootcampproject;

import android.os.Bundle;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bootcampproject.databinding.ActivityMainBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://api.calorieninjas.com/v1/nutrition?query=";
    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private CustomRecyclerViewAdapter adapter;
    ArrayList<Product> productsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        productsList = new ArrayList<>();
        recyclerView = binding.recyclerView;

        adapter = new CustomRecyclerViewAdapter(this, productsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyItemInserted(0);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fill(binding.editText.getText().toString());
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                productsList.remove(pos);
                adapter.notifyItemRemoved(pos);
            }
        });
        helper.attachToRecyclerView(recyclerView);
    }

    public void fill(String text){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = BASE_URL + text;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONArray items = response.getJSONArray("items");
                            JSONObject item = items.getJSONObject(i);
                            double calories = item.getDouble("calories");
                            double proteins = item.getDouble("protein_g");
                            double fats = item.getDouble("fat_total_g");
                            double carbs = item.getDouble("carbohydrates_total_g");
                            Product product = new Product(text, calories, proteins, fats, carbs);
                            productsList.add(product);
                        }

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "There's no such product", Toast.LENGTH_SHORT).show();
                    }

                    adapter.notifyItemInserted(productsList.size());
                }, error -> Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show()){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-Api-Key", "osyCD0FGNS7FbvyVby1MIg==X4ibj03Xdj9RUv7p");
                return params;
            }
        };
        queue.add(request);
    }
}