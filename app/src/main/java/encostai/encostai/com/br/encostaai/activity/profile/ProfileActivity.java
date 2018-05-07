package encostai.encostai.com.br.encostaai.activity.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import encostai.encostai.com.br.encostaai.R;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener,IProfileView {

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText newPassword;
    private EditText confirmPassword;
    private Button confirmButton;
    private ImageButton userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        name = findViewById(R.id.editText5);
        email = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        newPassword = findViewById(R.id.editText3);
        confirmPassword = findViewById(R.id.editText4);
        confirmButton = findViewById(R.id.button2);
        confirmButton.setOnClickListener(this);
        userImage = findViewById(R.id.imageButton);
        userImage .setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.button2:

                break;

            case R.id.imageButton:

                break;

        }




    }
}
