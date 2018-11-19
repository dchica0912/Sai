package june.delachica.com.sai.Modules.User.Reset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import june.delachica.com.sai.R;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button sendForgot = findViewById(R.id.sendForgot);
        sendForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ForgotPassword.this, ForgotPassSuccess.class);
                startActivity(i);
            }
        });
    }
}
