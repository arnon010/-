package info.androidhive.firebase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnLogin;
    TextView txtForgotPass, txtRegister;
    EditText edtUsername, edtPassword;
    final FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    Context context = this;
//    String userId;
//    private static final String TAG = LoginActivity.class.getSimpleName();
//    DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        txtForgotPass = findViewById(R.id.txtForgotPass);
        txtRegister = findViewById(R.id.txtRegister);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin.setOnClickListener(this);
        txtForgotPass.setOnClickListener(this);
        txtRegister.setOnClickListener(this);


        databaseReference = mFirebaseInstance.getReference("users");


//        mFirebaseDatabase = mFirebaseInstance.getReference("users");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                // Profile

                Query query = databaseReference.orderByChild("username").equalTo(edtUsername.getText().toString().trim());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // dataSnapshot is the "issue" node with all children with id 0

                            for (DataSnapshot user : dataSnapshot.getChildren()) {
                                // do something with the individual "issues"
                                Register usersBean = user.getValue(Register.class);

                                if (usersBean.password.equals(edtPassword.getText().toString().trim())) {
                                    Intent intent = new Intent(context, MainActivity.class);
                                    intent.putExtra("SENDER_KEY", "MyActivty");
                                    intent.putExtra("username", edtUsername.getText().toString().trim());
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(context, "Password is wrong", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            Toast.makeText(context, "User not found", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(context, "Fail To Connect To database", Toast.LENGTH_LONG).show();
                    }
                });

                Log.d("username", edtUsername.getText().toString().trim());
                Log.d("password", edtPassword.getText().toString().trim());

//                String username = edtUsername.getText().toString();
//                String password = edtPassword.getText().toString();
//
//                // Check for already existed userId
//                if (TextUtils.isEmpty(userId)) {
//                    createUser(username, password);
//
//
//                } else {
////                    updateUser(name, email);
//
//                }


                break;
            case R.id.txtForgotPass:
                // Forgot Password
                Intent intent2 = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent2);
                break;
            case R.id.txtRegister:
                // Register
                Intent intent3 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent3);
                break;
            default:
                break;

        }
    }


//    private void createUser(String username, String password) {
//        // TODO
//        // In real apps this userId should be fetched
//        // by implementing firebase auth
//        if (TextUtils.isEmpty(userId)) {
//            userId = mFirebaseDatabase.push().getKey();
//        }
//
//        User user = new User(username, password);
//
//        mFirebaseDatabase.child(userId).setValue(user);
//
//        addUserChangeListener();
//    }
//
//    /**
//     * User data change listener
//     */
//    private void addUserChangeListener() {
//        // User data change listener
//        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//
//                // Check for null
//                if (user == null) {
//                    Log.e(TAG, "User data is null!");
//                    return;
//                }
//
//                Log.e(TAG, "User data is changed!" + user.username + ", " + user.password);
//
//
//
//                // clear edit text
//                edtUsername.setText("");
//                edtPassword.setText("");
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.e(TAG, "Failed to read user", error.toException());
//            }
//        });
//    }
//
//


}
