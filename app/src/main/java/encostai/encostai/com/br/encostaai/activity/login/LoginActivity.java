package encostai.encostai.com.br.encostaai.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;

import encostai.encostai.com.br.encostaai.R;
import encostai.encostai.com.br.encostaai.activity.cadastro.CadastroActivity;
import encostai.encostai.com.br.encostaai.activity.inicial.InicialActivity;
import encostai.encostai.com.br.encostaai.utils.FirebaseConfig;

public class LoginActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {

            case R.id.entrar:
                intent = new Intent(LoginActivity.this, InicialActivity.class);
                startActivity(intent);
                break;
            case R.id.cadastrar:
                intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
                break;
        }
    }

}
