package info.androidhive.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InComeActivity extends AppCompatActivity {

    EditText edtIncome, edtAmountIn;
    Button btnSaveIncome;

    Toolbar toolbar;

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

        edtIncome = findViewById(R.id.edtIncome);
        edtAmountIn = findViewById(R.id.edtAmountIn);
        btnSaveIncome = findViewById(R.id.btnSaveIncome);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
