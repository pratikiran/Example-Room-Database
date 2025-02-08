package com.e4.explainroomdatabase;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.textView);

        // Get the database instance
        appDatabase = AppDatabase.getInstance(this);

        // Insert a user and display all users
        insertAndDisplayUsers();
    }

    private void insertAndDisplayUsers() {
        // Do this on a background thread to avoid blocking the UI
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Insert a new user
                User user = new User("Nitheeshwar B R", 50);
                appDatabase.userDao().insertUser(user);

                // Retrieve all users
                final List<User> userList = appDatabase.userDao().getAllUsers();

                // Display on the UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Build a string of all users to display
                        StringBuilder sb = new StringBuilder();
                        for (User u : userList) {
                            sb.append("ID: ").append(u.getId())
                                    .append(", Username: ").append(u.getUsername())
                                    .append(", Age: ").append(u.getAge())
                                    .append("\n");
                        }
                        textView.setText(sb.toString());
                    }
                });
            }
        }).start();
    }
}

