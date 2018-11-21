package june.delachica.com.sai.Modules.User.Reset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import june.delachica.com.sai.R;

public class ForgotPassSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass_success);
        CardView sendCheck = findViewById(R.id.sendCheck);
        sendCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ForgotPassSuccess.this, ForgotPassReset.class);
                startActivity(i);
            }
        });
    }
}