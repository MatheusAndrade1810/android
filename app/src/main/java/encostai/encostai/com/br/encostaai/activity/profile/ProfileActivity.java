package encostai.encostai.com.br.encostaai.activity.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import encostai.encostai.com.br.encostaai.R;
import encostai.encostai.com.br.encostaai.utils.Preferences;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, IProfileView {

    private EditText name;
    private TextView email;
    private EditText password;
    private EditText newPassword;
    private EditText confirmPassword;
    private Switch exposure;
    private IProfilePresenter profilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.editText5);
        email = findViewById(R.id.textViewEmail);
        password = findViewById(R.id.editText2);
        newPassword = findViewById(R.id.editText3);
        confirmPassword = findViewById(R.id.editText4);
        exposure = findViewById(R.id.switch1);
        profilePresenter = new ProfilePresenter(this, new ProfileInteractor());
        final Preferences preferences = getUserInfo();
        name.setHint(preferences.getName());
        email.setText(preferences.getEmail());

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.button2:

                profilePresenter.changeUserInfo(name.getText().toString(), exposure.isChecked(), password.getText().toString(), newPassword.getText().toString(), confirmPassword.getText().toString());

                break;
        }


    }

    public void setMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void restart() {
        Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private Preferences getUserInfo() {

        return profilePresenter.getUserInfo(this);
    }

}
