package com.example.amal.esa;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactUs extends AppCompatActivity {
    EditText Title, Msg;
    Button btSend;
    String to, subject, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Title = (EditText) findViewById(R.id.title);
        Msg = (EditText) findViewById(R.id.msg);

        btSend = (Button) findViewById(R.id.submit);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subject = Title.getText().toString();
                message = Msg.getText().toString();

                Intent email=new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","Esa@gmail.com",null));
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);


                startActivity(Intent.createChooser(email," "));

            }
        });
    }

}
