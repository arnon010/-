package info.project.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView txtDetails, txtBalance;
    private ImageButton btnIncome, btnOutcome;
    private DatabaseReference databaseReference, incomeDatabaseReference, outDatabaseReference;
    private FirebaseDatabase firebaseDatabase;

    ListView listView1 , listView2;
    ArrayList<String> incomeArrayList , outcomeArrayList;
    ArrayAdapter<String> incomeArrayAdapter,outcomeArrayAdapter;


    String strUsername;

    Toolbar toolbar;
    Income income;
    Outcome outcome;
    DecimalFormat formatter;
    static int intIncome, intOutcome;
    static Integer intResultIncome = 0;
    static Integer intResultOutcome = 0;
    static Integer intResult = 0;
    static String strIncome, strOutcome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intResult = 0;
        intResultIncome = 0;
        intResultOutcome = 0;

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
                finish();
            }
        });

        txtDetails = findViewById(R.id.txtUser);
        btnIncome = findViewById(R.id.btnIncome);
        btnOutcome = findViewById(R.id.btnOutcome);

        firebaseDatabase = FirebaseDatabase.getInstance();

        txtBalance = findViewById(R.id.txtBalance);

        formatter = new DecimalFormat("#,###,###");

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



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        intResult = 0;
        intResultIncome = 0;
        intResultOutcome = 0;

    }

    @Override
    protected void onResume() {
        super.onResume();
        intResult = 0;
        intResultIncome = 0;
        intResultOutcome = 0;
    }

    @Override
    protected void onStop() {
        super.onStop();
        intResult = 0;
        intResultIncome = 0;
        intResultOutcome = 0;
    }

    @Override
    protected void onStart() {
        super.onStart();
        intResult = 0;
        intResultIncome = 0;
        intResultOutcome = 0;
    }

    /**
     * User data change listener
     */
    private void addUserChangeListener() {

        intResult = 0;
        intResultIncome = 0;
        intResultOutcome = 0;

        databaseReference = firebaseDatabase.getReference();
        Query query = databaseReference.child("register");

        // User data change listener

        query.orderByChild("username").equalTo(strUsername).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Register newPost = dataSnapshot.getValue(Register.class);
                    System.out.println("MooUsername: " + newPost.username);
                    System.out.println("MooPassword: " + newPost.password);
                    System.out.println("MooName: " + newPost.name);
                    System.out.println("MooSurname: " + newPost.surname);
                    System.out.println("MooEmail: " + newPost.email);
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
        query.orderByChild("username").equalTo(strUsername).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Register newPost = dataSnapshot.getValue(Register.class);
                System.out.println("MooUsername: " + newPost.username);
                System.out.println("MooPassword: " + newPost.password);
                System.out.println("MooName: " + newPost.name);
                System.out.println("MooSurname: " + newPost.surname);
                System.out.println("MooEmail: " + newPost.email);
                System.out.println("MooPrevious Post ID:: " + s);
                System.out.println("Moo "+ dataSnapshot.getKey() + " was " + newPost.username);

                System.out.println("Moo " + dataSnapshot.getKey() + " score is " + dataSnapshot.getValue());


                txtDetails.setText(newPost.getName() + " " + newPost.getSurname());



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                intResult = 0;
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
        income = new Income();
        listView1 = findViewById(R.id.listView1);
        incomeDatabaseReference = firebaseDatabase.getReference();
        Query incomeQuery = incomeDatabaseReference.child("income");
        incomeArrayList = new ArrayList<>();
        incomeArrayAdapter = new ArrayAdapter<String>(this, R.layout.listview_row, R.id.txtValue, incomeArrayList);
        incomeQuery.orderByChild("username").equalTo(strUsername).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Income income = dataSnapshot.getValue(Income.class);
                    System.out.println("InUsername: " + income.username);
                    System.out.println("InPassword: " + income.detail_income);
                    System.out.println("InName: " + income.value_income);

                }

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    income = ds.getValue(Income.class);
                    intIncome = Integer.parseInt(income.getValue_income());
                    intResultIncome = intResultIncome + intIncome;
                    Log.d("ResultIncomeOnly", String.valueOf(intResultIncome));
                    strIncome = formatter.format(intIncome);
                    incomeArrayList.add(income.getDetail_income() + " : " + strIncome);


                    intResult = intResultIncome - intResultOutcome;
                    Log.d("ResultIn", String.valueOf(intResultIncome));
                    Log.d("ResultOut", String.valueOf(intResultOutcome));

                    txtBalance.setText(String.valueOf(formatter.format(intResult)));

                }
//                TextView textView = findViewById(R.id.txtValue);
//                textView.setTextColor(getColor(R.color.green));
                listView1.setAdapter(incomeArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        // User data change listener
        incomeQuery.orderByChild("username").equalTo(strUsername).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Income income = dataSnapshot.getValue(Income.class);
                System.out.println("IncomeUsername: " + income.username);
                System.out.println("IncomeDetail: " + income.detail_income);
                System.out.println("IncomeValue: " + income.value_income);





            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                intResult = 0;
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
        outcome = new Outcome();
        listView2 = findViewById(R.id.listView2);
        outDatabaseReference = firebaseDatabase.getReference();
        Query outcomeQuery = outDatabaseReference.child("outcome");
        outcomeArrayList = new ArrayList<>();
        outcomeArrayAdapter = new ArrayAdapter<String>(this, R.layout.listview_row2, R.id.txtValue2, outcomeArrayList);
        outcomeQuery.orderByChild("username").equalTo(strUsername).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Outcome outcome = dataSnapshot.getValue(Outcome.class);
                    System.out.println("OutUsername: " + outcome.username);
                    System.out.println("OutPassword: " + outcome.detail_outcome);
                    System.out.println("OutName: " + outcome.value_outcome);

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        outcome = ds.getValue(Outcome.class);
                        intOutcome = Integer.parseInt(outcome.getValue_outcome());
                        intResultOutcome = intResultOutcome + intOutcome;
                        Log.d("ResultOutcomeOnly", String.valueOf(intResultOutcome));
                        strOutcome = formatter.format(intOutcome);
                        outcomeArrayList.add(outcome.getDetail_outcome() + " : " + strOutcome);



                        intResult = intResultIncome - intResultOutcome;
                        Log.d("ResultIn", String.valueOf(intResultIncome));
                        Log.d("ResultOut", String.valueOf(intResultOutcome));

                        txtBalance.setText(String.valueOf(formatter.format(intResult)));

                    }
                    listView2.setAdapter(outcomeArrayAdapter);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        // User data change listener
        outcomeQuery.orderByChild("username").equalTo(strUsername).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Outcome outcome = dataSnapshot.getValue(Outcome.class);

                System.out.println("OutUsername: " + outcome.username);
                System.out.println("OutPassword: " + outcome.detail_outcome);
                System.out.println("OutName: " + outcome.detail_outcome);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                intResult = 0;

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
                finish();
                break;
            case R.id.btnOutcome:
                Intent intent2 = new Intent(MainActivity.this, OutComeActivity.class);
                intent2.putExtra("username", strUsername);
                startActivity(intent2);
                finish();
                break;
            default:
                break;
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();

    }
}