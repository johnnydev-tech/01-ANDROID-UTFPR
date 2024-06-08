package tech.johnnydev.calculaimc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText etPeso;
    private EditText etAltura;
    private TextView tvIMC;
    private Button btCalc;
    private Button btClean;

    private double calculaIMC(double peso, double altura) {
        return peso / Math.pow(altura, 2);
    }

    private  void limpaCampos(){
        etPeso.setText("");
        etAltura.setText("");
        tvIMC.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etPeso = findViewById(R.id.peso);
        etAltura = findViewById(R.id.altura);
        tvIMC = findViewById(R.id.imc);
        btCalc = findViewById(R.id.calcula);
        btClean = findViewById(R.id.limpar);



        btCalc.setOnClickListener(v -> {

            if(etPeso.getText().toString().isEmpty()){
                etPeso.setError("Campo vazio");
                etPeso.requestFocus();
                return;
            }

            if(etAltura.getText().toString().isEmpty()){
                etAltura.setError("Campo vazio");
                etAltura.requestFocus();
                return;
            }

            if(Double.parseDouble(etPeso.getText().toString()) <= 0){
                etPeso.setError("Peso inválido");
                etPeso.requestFocus();
                return;
            }

            if(Double.parseDouble(etAltura.getText().toString()) <= 0){
                etAltura.setError("Altura inválida");
                etAltura.requestFocus();
                return;
            }

            double peso = Double.parseDouble(etPeso.getText().toString());
            double altura = Double.parseDouble(etAltura.getText().toString());
            double imc = calculaIMC(peso, altura);

            DecimalFormat df = new DecimalFormat("0.00");
            tvIMC.setText(df.format(imc));
        });

        btClean.setOnClickListener(v -> {
            limpaCampos();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}