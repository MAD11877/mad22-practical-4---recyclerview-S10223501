package sg.edu.np.mad.exercise_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements RecyclerViewInterface{
    ArrayList<User> users = new ArrayList<>();
    RecyclerViewInterface recyclerViewInterface;
    User selectedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //create list of 20 objects
        for(int i=0; i<20; i++){
            Random rand = new Random();
            int randNameInt = rand.nextInt(100000000);
            String name = "Name-" +  randNameInt;
            int randDescInt = rand.nextInt(1000000000);
            String desc = "Description " + randDescInt;
            boolean bool = rand.nextBoolean();
            User user = new User();
            user.setName(name);
            user.setDescription(desc);
            user.setFollowed(bool);
            users.add(user);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        myAdaptor mAdaptor = new myAdaptor(users, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdaptor);

    }

    public void queryProfile(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //setCancelable to false forces the user to give an answer
        builder.setMessage(selectedUser.name).setCancelable(false);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent myIntent = new Intent(ListActivity.this, MainActivity.class);
                myIntent.putExtra("username", selectedUser.name);
                myIntent.putExtra("desc", selectedUser.description);
                startActivity(myIntent);            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
            }
        });
        //create the popup message and show it
        AlertDialog alert = builder.create();
        alert.setTitle("Profile");
        alert.show();
    }

    @Override
    public void onItemClick(int pos) {
        selectedUser = users.get(pos);
        queryProfile();
    }
}