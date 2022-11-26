package com.ualr.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ualr.recyclerviewassignment.Utils.DataGenerator;
import com.ualr.recyclerviewassignment.model.Inbox;
import com.ualr.recyclerviewassignment.Utils.Tools;

// TODO 05. Create a new Adapter class and the corresponding ViewHolder class in a different file. The adapter will be used to populate
//  the recyclerView and manage the interaction with the items in the list
    // I made changes to Inbox and created Adapter list based on the class slides
    // Just needed to set up various sets for texts, backgrounds, etc
// TODO 06. Detect click events on the list items. Implement a new method to toggle items' selection in response to click events
// TODO 07. Detect click events on the thumbnail located on the left of every list row when the corresponding item is selected.
//  Implement a new method to delete the corresponding item in the list
// TODO 08. Create a new method to add a new item on the top of the list. Use the DataGenerator class to create the new item to be added.

public class MainActivity extends AppCompatActivity{
    private FloatingActionButton mFAB;
    private RecyclerView recyclerView;
    private AdapterList mAdapter;
    private int listStartPosit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_multi_selection);
        initComponent();
    }



    private void initComponent() {
        // TODO 01. Generate the item list to be displayed using the DataGenerator class
        List<Inbox> items = DataGenerator.getInboxData(this);
        items.addAll(DataGenerator.getInboxData(this));

        // TODO 03. Do the setup of a new RecyclerView instance to display the item list properly
        // TODO 04. Define the layout of each item in the list
        // TODO 09. Create a new instance of the created Adapter class and bind it to the RecyclerView instance created in step 03
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this));

        mAdapter = new AdapterList(this, items);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AdapterList.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Inbox obj, int position) {
                mAdapter.toggleItemState(position);
            }
        });

        mAdapter.setOnThumbnailClickListener(new AdapterList.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Inbox obj, int position) {
                mAdapter.deleteItem(position);
            }
        });

        mFAB = findViewById(R.id.fab);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO 10. Invoke the method created to a new item to the top of the list so it's
                //  triggered when the user taps the Floating Action Button

                //I tried looking at the slides but below is edited code from Ivans example code branch 6
                mAdapter.addItem(listStartPosit, items.get(Tools.getRandomNumberInRange(listStartPosit,items.size()-1)));
                recyclerView.scrollToPosition(listStartPosit);
            }
        });
    }

}