package info.androidhive.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    TextView txtForgotPass, txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        txtForgotPass = findViewById(R.id.txtForgotPass);
        txtRegister = findViewById(R.id.txtRegister);


        btnLogin.setOnClickListener(this);
        txtForgotPass.setOnClickListener(this);
        txtRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.txtForgotPass:
                Intent intent2 = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent2);
                break;
            case R.id.txtRegister:
                Intent intent3 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent3);
                break;
            default:
                break;

        }
    }
}
