package info.androidhive.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView txtDetails;
    private Button btnIncome, btnOutcome;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String strUsername;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar5);
        toolbar.setTitle(getResources().getString(R.string.register));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        txtDetails = findViewById(R.id.txt_user);
        btnIncome = findViewById(R.id.btnIncome);
        btnOutcome = findViewById(R.id.btnOutcome);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("register");

//        // store app title to 'app_title' node
//        mFirebaseInstance.getReference("app_title").setValue(getResources().getString(R.string.app_name));
//
//        // app_title change listener
//        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.e(TAG, "App title updated");
//
//                String appTitle = dataSnapshot.getValue(String.class);
//
//                // update toolbar title
//                getSupportActionBar().setTitle(appTitle);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.e(TAG, "Failed to read app title value.", error.toException());
//            }
//        });

        btnIncome.setOnClickListener(this);
        btnOutcome.setOnClickListener(this);

        addUserChangeListener();

    }


    @Override
    protected void onResume() {
        super.onResume();
        //DETERMINE WHO STARTED THIS ACTIVITY
        final String sender=this.getIntent().getExtras().getString("SENDER_KEY");

        //IF ITS THE FRAGMENT THEN RECEIVE DATA
        if(sender != null)
        {
            this.receiveData();

        }
    }

    private void receiveData() {
        //RECEIVE DATA VIA INTENT
        Intent i = getIntent();
        strUsername = i.getStringExtra("username");
    }


    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child("username").equalTo(strUsername).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Check for null



                for (DataSnapshot user : dataSnapshot.getChildren()) {


                    String strName = user.child("name").getValue().toString();
                    String strSurname = user.child("surname").getValue().toString();

                    txtDetails.setText(strName + " " + strSurname);

                    Log.d("CheckData", strName + strSurname);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIncome:
                Intent intent = new Intent(MainActivity.this, InComeActivity.class);
                startActivity(intent);
                break;
            case R.id.btnOutcome:
                Intent intent2 = new Intent(MainActivity.this, OutComeActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}