package com.kimistudios.mogger;

import org.json.JSONException;
import org.json.JSONObject;
 
import library.DatabaseHandler;
import library.UserFunctions;
 
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
public class RegisterActivity extends Activity {
    Button btnRegister;
    Button btnLinkToLogin;
    EditText inputFullName;
    EditText inputEmail;
    EditText inputPassword;
    TextView registerErrorMsg;
 
    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
 
        // Importing all assets like buttons, text fields
        inputFullName = (EditText) findViewById(R.id.firstlast);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.pass);
        btnRegister = (Button) findViewById(R.id.regsubmit);
        btnLinkToLogin = (Button) findViewById(R.id.reglogin);
        registerErrorMsg = (TextView) findViewById(R.id.regerror);
 
        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
				progressDialog.setMessage("Registering...");
				RegisterTask registerTask = new RegisterTask(RegisterActivity.this, progressDialog);
				registerTask.execute();
            }
        });
 
        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                // Close Registration View
                finish();
            }
        });
    }

	public void registerReport(Integer responseCode) {
    	int duration = Toast.LENGTH_LONG;
    	Context context = getApplicationContext();
		
		if (responseCode == 0) {
			Toast toast = Toast.makeText(context, "Register Error", duration);
			toast.show();
		}
		else {
			Toast toast = Toast.makeText(context, "Register Success", duration);
			toast.show();
            Intent i = new Intent(getApplicationContext(),
                    Dashboard.class);
            startActivity(i);
            finish();
		}
		
		
		
	}
}