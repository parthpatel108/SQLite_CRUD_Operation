package demo.parth_dev.parth.com.practical_35b;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText  name, mobile, address, email;
    Button  update;
    DataHelper helper;
    String num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        helper = new DataHelper(UpdateActivity.this);
        Intent i = getIntent();
        num=i.getStringExtra("id");
        name = (EditText) findViewById(R.id.name);
        mobile = (EditText) findViewById(R.id.mobile);
        address = (EditText) findViewById(R.id.address);
        email = (EditText) findViewById(R.id.email);
        update = (Button) findViewById(R.id.btn_update);


        Cursor c = helper.getdata(num);
        while (c.moveToNext()) {
            name.setText(c.getString(1));
            mobile.setText(c.getString(2));
            address.setText(String.valueOf(c.getString(3)));
            email.setText(c.getString(4));
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean b = helper.updateData(num,name.getText().toString(),mobile.getText().toString(),address.getText().toString(),email.getText().toString());
                if(b){
                    Toast.makeText(UpdateActivity.this,"Data Updated Successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(UpdateActivity.this,"Data not Updated Successfully",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
