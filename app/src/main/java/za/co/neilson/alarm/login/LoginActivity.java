package za.co.neilson.alarm.login;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import za.co.neilson.alarm.AlarmActivity;
import za.co.neilson.alarm.R;
import za.co.neilson.alarm.database.Database;
import za.co.neilson.alarm.group.User;

public class LoginActivity extends ActionBarActivity {
    EditText idEditText;
    Button signInButton;
    EditText pwEditText;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idEditText = (EditText) findViewById(R.id.idEditText);
        signInButton = (Button) findViewById(R.id.signInButton);
        pwEditText = (EditText) findViewById(R.id.pwEditText);
        signUpButton = (Button) findViewById(R.id.signUpButton);

        signInButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                User user = Database.getUser(idEditText.getText().toString(), pwEditText.getText().toString());
                if(user == null){
                    Toast.makeText(getApplicationContext(),"입력 오류",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                Database.addUser((User)data.getSerializableExtra("user"));
            }
        }
    }
}
