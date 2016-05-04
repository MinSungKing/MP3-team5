package za.co.neilson.alarm.login;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import za.co.neilson.alarm.R;
import za.co.neilson.alarm.database.Database;
import za.co.neilson.alarm.group.User;

public class SignUpActivity extends ActionBarActivity {
    EditText inputIdEditText;
    EditText inputPwEditText;
    Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputIdEditText = (EditText) findViewById(R.id.inputIdEditText);
        inputPwEditText = (EditText) findViewById(R.id.inputPwEditText);
        okButton = (Button) findViewById(R.id.okButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(inputIdEditText.getText().toString(), inputPwEditText.getText().toString());
                if(Database.findUser(user)){
                    Toast.makeText(getApplicationContext(), "입력 오류",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = getIntent();
                    intent.putExtra("user",user);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }
}
