package info.androidhive.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OutComeActivity extends AppCompatActivity {

    private static final String TAG = OutComeActivity.class.getSimpleName();

    EditText edtOutcome, edtAmountOut;
    Button btnSaveOutcome;
    ImageView imgCalendarOutcome;

    String strUsername;

    Toolbar toolbar;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_come);

        toolbar = findViewById(R.id.toolbar4);
        toolbar.setTitle(getResources().getString(R.string.outcome));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_white_36dp));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("outcome");

        edtOutcome = findViewById(R.id.edtOutcome);
        edtAmountOut = findViewById(R.id.edtAmountOut);
        btnSaveOutcome = findViewById(R.id.btnSaveOutcome);
        imgCalendarOutcome = findViewById(R.id.imgCalendarOutcome);
        imgCalendarOutcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                strUsername = null;
            } else {
                strUsername = extras.getString("username");
            }
        } else {
            strUsername = (String) savedInstanceState.getSerializable("username");
        }




        btnSaveOutcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String detail_outcome = edtOutcome.getText().toString();
                String value_outcome = "-" + edtAmountOut.getText().toString();
                String username = strUsername;


                // Check for already existed userId
                if (TextUtils.isEmpty(userId)) {
                    createUser(detail_outcome,value_outcome,username);

                    Intent intent = new Intent(OutComeActivity.this, MainActivity.class);
                    intent.putExtra("username", strUsername);
                    startActivity(intent);
                    Toast.makeText(OutComeActivity.this, "เพิ่มข้อมูลรายจ่ายเรียบร้อย", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OutComeActivity.this, "ไม่สามารถเพิ่มข้อมูลรายจ่ายได้", Toast.LENGTH_SHORT).show();
                }



            }
        });




    }



    /**
     * Creating new user node under 'users'
     */
    private void createUser(String detail_outcome, String value_outcome, String username) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        Outcome outcome = new Outcome(detail_outcome, value_outcome, username);

        mFirebaseDatabase.child(userId).setValue(outcome);

        addUserChangeListener();
    }

    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Outcome outcome = dataSnapshot.getValue(Outcome.class);

                // Check for null
                if (outcome == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }



                // clear edit text
                edtAmountOut.setText("");
                edtOutcome.setText("");

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
