package encostai.encostai.com.br.encostaai.activity.termsPolicies;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import encostai.encostai.com.br.encostaai.R;
import encostai.encostai.com.br.encostaai.activity.login.LoginActivity;

public class TermsPoliciesActivity extends AppCompatActivity implements ITermsPoliciesView {


    private AlertDialog alerta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_policies);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AlertDialog.Builder termos = new AlertDialog.Builder(TermsPoliciesActivity.this);
        termos.setTitle("Política e Termos");
        termos.setMessage("Política e Termos de Serviço\n" +
                "\n" +
                "Agradecemos por usar o EncostaAí.\n" +
                "\n" +
                "Ao acessar o EncostaAí, realizar download, ou usar, você concorda com:\n" +
                "1.\tTermos de Serviço;\n" +
                "2.\tPolítica de privacidade;\n" +
                "3.\tAvisos Legais;\n" +
                "\n" +
                "Leia cada um desses documentos com atenção:\n" +
                "\n" +
                "Termos de Serviço\n" +
                "Como usar nossos Serviços\n" +
                "É preciso que você siga as políticas disponibilizadas a você dentro dos Serviços.\n" +
                "Não faça uso indevido de nossos Serviços. Por exemplo, não interfira com nossos Serviços nem tente acessá-los por um método diferente da interface e das instruções que fornecemos. Você pode usar nossos serviços somente conforme permitido por lei, inclusive leis e regulamentos de controle de exportação e reexportação. Podemos suspender ou deixar de fornecer nossos Serviços se você descumprir nossos termos ou políticas ou se estivermos investigando casos de suspeita de má conduta.\n" +
                "O uso de nossos Serviços não lhe confere a propriedade sobre direitos de propriedade intelectual sobre os nossos Serviços ou sobre o conteúdo que você acessar. Você não pode usar conteúdos de nossos Serviços a menos que obtenha permissão do proprietário de tal conteúdo ou que o faça por algum meio permitido por lei. Estes termos não conferem a você o direito de usar quaisquer marcas ou logotipos utilizados em nossos Serviços. Não remova, oculte ou altere quaisquer avisos legais exibidos em ou junto a nossos Serviços.\n" +
                "\n" +
                "Política de Privacidade\n" +
                "Avisos Legais\n");


        alerta = termos.create();
        alerta.show();
    }

}
