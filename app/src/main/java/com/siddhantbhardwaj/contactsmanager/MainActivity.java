package com.siddhantbhardwaj.contactsmanager;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.siddhantbhardwaj.contactsmanager.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();

    private MyAdapter myAdapter;

    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandler handler;

    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set up window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up data binding and click handler
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handler = new MainActivityClickHandler(this);
        activityMainBinding.setClickHandler(handler);

        // Initialize RecyclerView and adapter
        RecyclerView recyclerView = activityMainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Initialize the adapter here before observing LiveData
        myAdapter = new MyAdapter(contactsArrayList);
        recyclerView.setAdapter(myAdapter);

        // Initialize ViewModel
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);


        // Observe LiveData for changes to contacts
        myViewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {
                // Clear existing list and update with new data
                contactsArrayList.clear();
                contactsArrayList.addAll(contacts);  // Add new contacts to the list
                Log.v("TAGY", "Number of contacts: " + contactsArrayList.size());

                // Notify the adapter that the data has changed
                myAdapter.notifyDataSetChanged();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contacts c = contactsArrayList.get(viewHolder.getAdapterPosition());
                myViewModel.deleteContact(c);
            }
        }).attachToRecyclerView(recyclerView);

    }
}
