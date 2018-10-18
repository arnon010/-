package info.androidhive.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView txtDetails;
    private ImageButton btnIncome, btnOutcome;
    private DatabaseReference databaseReference, incomeDatabaseReference, outDatabaseReference;
    private FirebaseDatabase firebaseDatabase;


    TextView txtIncomeDetail, txtIncomeValue, txtOutcomeDetail, txtOutcomeValue;

    String strUsername;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar5);
        toolbar.setTitle(getResources().getString(R.string.register));
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

        txtDetails = findViewById(R.id.txtUser);
        btnIncome = findViewById(R.id.btnIncome);
        btnOutcome = findViewById(R.id.btnOutcome);

        firebaseDatabase = FirebaseDatabase.getInstance();

        txtIncomeDetail = findViewById(R.id.txtIncomeDetail);
        txtIncomeValue = findViewById(R.id.txtIncomeValue);
        txtOutcomeDetail = findViewById(R.id.txtOutcomeDetail);
        txtOutcomeValue = findViewById(R.id.txtOutcomeValue);

        // get reference to 'users' node


//        // store app title to 'app_title' node
//        firebaseDatabase.getReference("app_title").setValue(getResources().getString(R.string.app_name));
//
//        // app_title change listener
//        firebaseDatabase.getReference("app_title").addValueEventListener(new ValueEventListener() {
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

        addUserChangeListener();


//        makeListView();

    }

    private void makeListView() {
        String[] list1 = { "Aerith Gainsborough", "Barret Wallace", "Cait Sith"
                , "Cid Highwind", "Cloud Strife", "RedXIII", "Sephiroth"
                , "Tifa Lockhart", "Vincent Valentine", "Yuffie Kisaragi"
                , "ZackFair" };

        String[] list2 = { "Aerith Gainsborough", "Barret Wallace", "Cait Sith"
                , "Cid Highwind", "Cloud Strife", "RedXIII", "Sephiroth"
                , "Tifa Lockhart", "Vincent Valentine", "Yuffie Kisaragi"
                , "ZackFair" };

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), list1, list2);

        ListView listView = findViewById(R.id.listView1);
        listView.setAdapter(adapter);

    }

    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        databaseReference = firebaseDatabase.getReference("register");
        databaseReference.orderByChild("username").equalTo(strUsername).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Register newPost = dataSnapshot.getValue(Register.class);
                    System.out.println("MooUsername: " + newPost.username);
                    System.out.println("MooPassword: " + newPost.password);
                    System.out.println("MooName: " + newPost.name);
                    System.out.println("MooSurname: " + newPost.surname);
                    System.out.println("MooEmail: " + newPost.email);
                    System.out.println("MooDetailIncome: " + newPost.detail_income);
                    System.out.println("MooDetailOutcome: " + newPost.detail_outcome);
                    System.out.println("MooIncome: " + newPost.value_income);
                    System.out.println("MooOutcome: " + newPost.value_outcome);
                    System.out.println("Moo "+ dataSnapshot.getKey() + " was " + newPost.username);

                    System.out.println("Moo " + dataSnapshot.getKey() + " score is " + dataSnapshot.getValue());


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        // User data change listener
        databaseReference.orderByChild("username").equalTo(strUsername).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Register newPost = dataSnapshot.getValue(Register.class);
                System.out.println("MooUsername: " + newPost.username);
                System.out.println("MooPassword: " + newPost.password);
                System.out.println("MooName: " + newPost.name);
                System.out.println("MooSurname: " + newPost.surname);
                System.out.println("MooEmail: " + newPost.email);
                System.out.println("MooDetailIncome: " + newPost.detail_income);
                System.out.println("MooDetailOutcome: " + newPost.detail_outcome);
                System.out.println("MooIncome: " + newPost.value_income);
                System.out.println("MooOutcome: " + newPost.value_outcome);
                System.out.println("MooPrevious Post ID:: " + s);
                System.out.println("Moo "+ dataSnapshot.getKey() + " was " + newPost.username);

                System.out.println("Moo " + dataSnapshot.getKey() + " score is " + dataSnapshot.getValue());


                txtDetails.setText(newPost.getName() + " " + newPost.getSurname());



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }); //tbRegister


        // User data change listener
        incomeDatabaseReference = firebaseDatabase.getReference("income");
        incomeDatabaseReference.orderByChild("username").equalTo(strUsername).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Income income = dataSnapshot.getValue(Income.class);
                    System.out.println("InUsername: " + income.username);
                    System.out.println("InPassword: " + income.detail_income);
                    System.out.println("InName: " + income.value_income);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        // User data change listener
        incomeDatabaseReference.orderByChild("username").equalTo(strUsername).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Income income = dataSnapshot.getValue(Income.class);
                System.out.println("IncomeUsername: " + income.username);
                System.out.println("IncomeDetail: " + income.detail_income);
                System.out.println("IncomeValue: " + income.value_income);



                txtIncomeDetail.setText(income.getDetail_income());
                txtIncomeValue.setText(income.getValue_income());




//                String[] list1 = { income.getDetail_income() };
//
//                String[] list2 = { income.getValue_income() };
//
//                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), list1, list2);
//
//                ListView listView = findViewById(R.id.listView1);
//                listView.setAdapter(adapter);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }); //tbIncome


        // User data change listener
        outDatabaseReference = firebaseDatabase.getReference("outcome");
        outDatabaseReference.orderByChild("username").equalTo(strUsername).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Outcome outcome = dataSnapshot.getValue(Outcome.class);
                    System.out.println("OutUsername: " + outcome.username);
                    System.out.println("OutPassword: " + outcome.detail_outcome);
                    System.out.println("OutName: " + outcome.value_outcome);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        // User data change listener
        outDatabaseReference.orderByChild("username").equalTo(strUsername).limitToFirst(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Outcome outcome = dataSnapshot.getValue(Outcome.class);

                System.out.println("OutUsername: " + outcome.username);
                System.out.println("OutPassword: " + outcome.detail_outcome);
                System.out.println("OutName: " + outcome.detail_outcome);

                txtOutcomeDetail.setText(outcome.getDetail_outcome());
                txtOutcomeValue.setText(outcome.getValue_outcome());


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }); //tbOutcome


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIncome:
                Intent intent = new Intent(MainActivity.this, InComeActivity.class);
                intent.putExtra("username", strUsername);
                startActivity(intent);
                break;
            case R.id.btnOutcome:
                Intent intent2 = new Intent(MainActivity.this, OutComeActivity.class);
                intent2.putExtra("username", strUsername);
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