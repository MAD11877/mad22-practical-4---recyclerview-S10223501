package sg.edu.np.mad.exercise_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Main Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent myIntent = getIntent();
        String name = myIntent.getStringExtra("username");
        String userDesc = myIntent.getStringExtra("desc");
        User user = new User();

        TextView userName = findViewById(R.id.textView2);
        userName.setText(name);
        TextView desc = findViewById(R.id.textView);
        desc.setText(userDesc);
        Button myFollowButton = findViewById(R.id.myFollowButton);
        myFollowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.isFollowed() == false){
                    myFollowButton.setText("Unfollow");
                    user.setFollowed(true);
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_LONG).show();
                }
                else{
                    myFollowButton.setText("Follow");
                    user.setFollowed(false);
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button myMessageButton = findViewById(R.id.myMessageButton);
        myMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(myIntent);
            }
        });
    }
}