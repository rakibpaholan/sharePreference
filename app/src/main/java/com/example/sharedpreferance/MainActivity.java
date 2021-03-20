package com.example.sharedpreferance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText user_edit_text, pass_edit_text;
    private Button save_button, load_button;
    private TextView user_name_load,user_pass_load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        user_edit_text = (EditText)findViewById(R.id.user_name_id);
        pass_edit_text = (EditText)findViewById(R.id.user_pass);
        save_button = (Button)findViewById(R.id.save_Button_id);
        load_button = (Button)findViewById(R.id.load_Button_id);
        user_name_load = (TextView)findViewById(R.id.load_data_result_user_name);
        user_pass_load = (TextView)findViewById(R.id.load_data_result_user_pass);

        save_button.setOnClickListener(this);
        load_button.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.save_Button_id){

            String user_name = user_edit_text.getText().toString();
            String user_pass = pass_edit_text.getText().toString();

            if (user_name.equals("") && user_pass.equals("")){
                Toast.makeText(MainActivity.this,"Details need to fillUp",Toast.LENGTH_SHORT).show();
            }else {
                SharedPreferences sharedPreferences = getSharedPreferences("userDetails",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("user_name",user_name);
                editor.putString("user_pass",user_pass);
                editor.commit();

                pass_edit_text.setText("");
                user_edit_text.setText("");
            }
        }

        if (id==R.id.load_Button_id){
            SharedPreferences sharedPreferences = getSharedPreferences("userDetails",MODE_PRIVATE);
            if (sharedPreferences.contains("user_name") && sharedPreferences.contains("user_pass")){
                String user_name = sharedPreferences.getString("user_name","Not Data found");
                String user_pass =sharedPreferences.getString("user_pass","Not data found");

                user_name_load.setText(user_name);
                user_pass_load.setText(user_pass);
            }
        }
    }
}