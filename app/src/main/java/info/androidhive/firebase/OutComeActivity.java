package info.androidhive.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OutComeActivity extends AppCompatActivity {

    EditText edtOutcome, edtAmountOut;
    Button btnSaveOutcome;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_come);

        toolbar = findViewById(R.id.toolbar4);
        toolbar.setTitle(getResources().getString(R.string.outcome));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        edtOutcome = findViewById(R.id.edtOutcome);
        edtAmountOut = findViewById(R.id.edtAmountOut);
        btnSaveOutcome = findViewById(R.id.btnSaveOutcome);




    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
