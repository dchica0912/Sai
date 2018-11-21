package june.delachica.com.sai.Modules.User.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import june.delachica.com.sai.DashboardFragment;
import june.delachica.com.sai.Main.MainActivity;
import june.delachica.com.sai.Modules.User.Reset.ForgotPassword;
import june.delachica.com.sai.Modules.User.Registration.RegistrationFirst;
import june.delachica.com.sai.R;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CardView login = findViewById(R.id.login);
        TextView forgotPassword = findViewById(R.id.forgotPassword);
        TextView register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (Login.this, MainActivity.class);
                startActivity(i);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, ForgotPassword.class);
                startActivity(i);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, RegistrationFirst.class);
                startActivity(i);
            }
        });
    }
}
