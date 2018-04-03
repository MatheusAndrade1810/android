package com.example.vitor.exemplo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference("texts");
    private DatabaseReference userDatabaseReference = databaseReference.child("myText");

    private EditText myName;
    private EditText myText;

    private Text text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myName = (EditText)findViewById(R.id.editTextId);
        myText = (EditText)findViewById(R.id.editTextId2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //carrega valores ao Create
        userDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                text = dataSnapshot.getValue(Text.class);
                myName.setText(text.getName());
                myText.setText(text.getText());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //salva volores ao Stop
    @Override
    protected void onStop() {
        super.onStop();
        text = new Text();
        text.setName(myName.getText().toString());
        text.setText(myText.getText().toString());
        userDatabaseReference.setValue(text);
    }
}
