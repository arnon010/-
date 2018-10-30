package info.project.firebase;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class InComeActivity extends AppCompatActivity {

    private static final String TAG = InComeActivity.class.getSimpleName();

    EditText edtIncome, edtAmountIn;
    Button btnSaveIncome;
    ImageView imgCalendarIncome;

    String strUsername;

    Toolbar toolbar;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String userId;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    TextView txtDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_come);

        toolbar = findViewById(R.id.toolbar3);
        toolbar.setTitle(getResources().getString(R.string.forgot_password));
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
        mFirebaseDatabase = mFirebaseInstance.getReference("income");

        edtIncome = findViewById(R.id.edtIncome);
        edtAmountIn = findViewById(R.id.edtAmountIn);
        btnSaveIncome = findViewById(R.id.btnSaveIncome);
        imgCalendarIncome = findViewById(R.id.imgCalendarIncome);
        txtDate = findViewById(R.id.txtDateIn);




        btnSaveIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String detail_income = edtIncome.getText().toString();
                String value_income = edtAmountIn.getText().toString();
                String username = strUsername;


                if (detail_income.equals("")) {
                    edtIncome.setError("กรุณาใส่ข้อมูลรายรับ");
                } else if (value_income.equals("")) {
                    edtAmountIn.setError("กรุณาใส่จำนวนรายรับ");
                } else if (detail_income.equals("") && value_income.equals("")) {
                    edtIncome.setError("กรุณาใส่ข้อมูลให้ครบถ้วน");
                    edtAmountIn.setError("กรุณาใส่ข้อมูลให้ครบถ้วน");
                } else


                // Check for already existed userId
                if (TextUtils.isEmpty(userId)) {
                    createUser(detail_income,value_income,username);

                    Intent intent = new Intent(InComeActivity.this, MainActivity.class);
                    intent.putExtra("username", strUsername);
                    startActivity(intent);
                    finish();
                    Toast.makeText(InComeActivity.this, "เพิ่มข้อมูลรายรับเรียบร้อย", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InComeActivity.this, "ไม่สามารถเพิ่มข้อมูลรายรับได้", Toast.LENGTH_SHORT).show();
                }


            }
        });


        imgCalendarIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(InComeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                txtDate.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
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



    }


    /**
     * Creating new user node under 'users'
     */
    private void createUser(String detail_income, String value_income, String username) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        Income income = new Income(detail_income, value_income, username);

        mFirebaseDatabase.child(userId).setValue(income);

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
                Income income = dataSnapshot.getValue(Income.class);

                // Check for null
                if (income == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }



                // clear edit text
                edtAmountIn.setText("");
                edtIncome.setText("");

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

        finish();

    }
}
