package info.project.firebase;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    EditText forgetPassword;
    Button btnForgetPassword;
    TextView txtForgetPassword;
    final FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        toolbar = findViewById(R.id.toolbar2);
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


        forgetPassword = findViewById(R.id.forgetPassword);
        btnForgetPassword = findViewById(R.id.btnForgetPassword);
        txtForgetPassword = findViewById(R.id.txtForgetPassword);


        databaseReference = mFirebaseInstance.getReference("register");

        btnForgetPassword.setOnClickListener(this);




    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnForgetPassword:
                Query query = databaseReference.orderByChild("username").equalTo(forgetPassword.getText().toString().trim());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // dataSnapshot is the "issue" node with all children with id 0

                            for (DataSnapshot user : dataSnapshot.getChildren()) {
                                // do something with the individual "issues"
                                Register usersBean = user.getValue(Register.class);
                                txtForgetPassword.setText("รหัสผ่านของคุณคือ\n" + usersBean.getPassword());

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

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
