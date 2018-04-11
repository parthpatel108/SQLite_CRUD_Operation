package demo.parth_dev.parth.com.practical_35b;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText id;
    Button show, update, delete;
    TextView result;
    DataHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = (EditText) findViewById(R.id.id);
        show = (Button) findViewById(R.id.btn_show);
        update=(Button)findViewById(R.id.btn_u);
        result = (TextView) findViewById(R.id.result);
        delete = (Button) findViewById(R.id.btn_delete);
        helper = new DataHelper(MainActivity.this);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String rs = "";
                String num = id.getText().toString();
                Cursor c = helper.getdata(num);
                while (c.moveToNext()) {
                    rs = rs + "Name :" + c.getString(1);
                    rs = rs + "\nMobile :" + String.valueOf(c.getString(2));
                    rs = rs + "\nEmail :" + c.getString(3);
                    rs = rs + "\nAddresss :" + c.getString(4);
                }

                result.setText(rs.toString());
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, UpdateActivity.class);
                i.putExtra("id", String.valueOf(id.getText().toString()));
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean b = helper.deletedata(String.valueOf(id.getText().toString()));
                if(b){
                    Toast.makeText(MainActivity.this,"Data Deleted Successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data not deleted Successfully",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
