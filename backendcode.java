package com.example2.railu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity implements ValueEventListener{
    TextView textView, textView1, textView2;
    Button btn;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference firstDatabase = databaseReference.child("Train status");
    private DatabaseReference secondDatabase = databaseReference.child("Distance");
    private DatabaseReference thirdDatabase = databaseReference.child("Speed");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = (TextView) findViewById(R.id.textView12);
        textView1 = (TextView) findViewById(R.id.textView14);
        textView2 = (TextView) findViewById(R.id.textView16);
        btn=(Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenActivity();
            }
        });

    }
    public void OpenActivity()
    {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        Object dataSnapshot;
        if (snapshot.getValue(Object.class) != null) {
            Object key = snapshot.getKey();
            if (key.equals("Train status")) {
                String train = snapshot.getValue(String.class);
                textView.setText(train);
            }
            if (key.equals("Distance")) {
                Integer adc = snapshot.getValue(Integer.class);
                textView1.setText("" + adc);
            }
            if (key.equals("Speed")) {
                Integer efg = snapshot.getValue(Integer.class);
                textView2.setText("" + efg);

            }
        }
    }


    @Override
    public void onCancelled (DatabaseError databaseerror)
    {


    }
    protected void onStart () {

        super.onStart();
        firstDatabase.addValueEventListener(this);
        secondDatabase.addValueEventListener(this);
        thirdDatabase.addValueEventListener(this);

    }

}
