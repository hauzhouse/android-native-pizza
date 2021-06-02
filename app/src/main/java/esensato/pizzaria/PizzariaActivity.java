package esensato.pizzaria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PizzariaActivity extends AppCompatActivity
implements AdapterView.OnItemSelectedListener,
        RadioGroup.OnCheckedChangeListener{

    private Spinner spSabores;
    private ImageView imgPizza;
    private RadioGroup rgTamanho;
    private CheckBox chkBorda;
    private TextView txtPreco;

    private List<PizzaBean> pizzas;
    private ArrayAdapter<PizzaBean> adpPizza;

    private PizzaBean pizzaSelecionada;
    private int tamanhoSelecionado;
    private boolean bordaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzaria);
        spSabores = (Spinner) findViewById(R.id.spSabor);
        criarPizzasTeste();
        popularSpinnerSabores();
        spSabores.setOnItemSelectedListener(this);
        imgPizza = (ImageView) findViewById(R.id.imgPizza);
        rgTamanho = (RadioGroup) findViewById(R.id.rgTamanho);
        rgTamanho.setOnCheckedChangeListener(this);
        chkBorda = (CheckBox) findViewById(R.id.chkBorda);
        txtPreco = (TextView) findViewById(R.id.txtPreco);

    }

    private void calcularPreco(){
        double preco = pizzaSelecionada.getPreco();

        if (tamanhoSelecionado == R.id.rbPequeno)
            preco += 5.0;
        else if (tamanhoSelecionado == R.id.rbMedio)
            preco += 10.0;
        else if (tamanhoSelecionado == R.id.rbGrande)
            preco += 15.0;

        if (bordaSelecionada)
            preco += 5.0;

        txtPreco.setText(String.valueOf(preco));
    }

    private void popularSpinnerSabores() {

        adpPizza = new ArrayAdapter<PizzaBean>(this,
                android.R.layout.simple_spinner_item,
                this.pizzas);

        spSabores.setAdapter(adpPizza);
    }

    private void criarPizzasTeste() {

        pizzas = new ArrayList<PizzaBean>();
        pizzas.add(new PizzaBean("Super Bacon", 10.0, R.drawable.pizzabacon));
        pizzas.add(new PizzaBean("Mega Carbonara", 20.0, R.drawable.pizzacarbonara));
        pizzas.add(new PizzaBean("La Pancia del Nono", 15.0, R.drawable.pizzapancianono));
        pizzas.add(new PizzaBean("Queijo Puxa Puxa", 13.0, R.drawable.pizzaqueijo));
        pizzas.add(new PizzaBean("Ridiculúcula", 30.0, R.drawable.pizzarucula));

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // retorna a pizza (PizzaBean) selecionada em position
        pizzaSelecionada = (PizzaBean) parent.getItemAtPosition(position);
        Toast.makeText(this, pizzaSelecionada.getSabor(), Toast.LENGTH_SHORT).show();
        // troca a imagem da pizza (ImageView) de acordo
        // com o R.drawable definido no PizzaBean
        imgPizza.setImageResource(pizzaSelecionada.getImagem());
        calcularPreco();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // acionado quando usuario seleciona um RadioButton
    // checkedId = R.id do RadioButton selecionado
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // if (group.getId() == R.id.rgTamanho)

        String t = "";
        if (checkedId == R.id.rbPequeno)
            t = "PEQUENO";
        else if (checkedId == R.id.rbMedio)
            t = "MÉDIO";
        else if (checkedId == R.id.rbGrande)
            t = "GRANDE";

        Toast.makeText(this, t, Toast.LENGTH_SHORT).show();

        tamanhoSelecionado = checkedId;
        calcularPreco();
    }

    public void borda(View v){

        if (chkBorda.isChecked())
            Toast.makeText(this, "COM BORDA", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "SEM BORDA", Toast.LENGTH_SHORT).show();

        bordaSelecionada = chkBorda.isChecked();
        calcularPreco();
    }
}

