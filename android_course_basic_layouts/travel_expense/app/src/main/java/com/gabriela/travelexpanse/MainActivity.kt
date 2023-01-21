package com.gabriela.travelexpanse

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gabriela.travelexpanse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    // ciclo de vida activity
    // onCreate é responsavel por criar uma activity, ela é instanciada e colocada na memoria
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View Binding - conexão entre o layout e o código
        // ActivityMainBinding é uma classe criada para ajudar a mapear os elementos
        // layoutInflater torna possivel acessar os atributos de inface no código
        // Vamos utilizar essa abordagem
        binding = ActivityMainBinding.inflate(layoutInflater)
        // setContentView é responsável por atribuir o layout da activity -> cria o layout
        setContentView(binding.root)

        // identificar um click
        // Usando osetOnClickListener que recebe um View.OnClickListener como parametro
        // mas para isso devemos mplementar View.OnClickListener na classe
        binding.buttonCalculate.setOnClickListener(this)
        // Alem do método acima podemos utilizar o setOnClickListener que é um lamba:
        //  binding.buttonCalculate.setOnClickListener {
        //    calculate()
        //  }
    }

    private fun calculate() {
        try {
            if (isValid()) {
                val distance = binding.editDistance.text.toString().toFloat()
                val price = binding.editPrice.text.toString().toFloat()
                val autonomy = binding.editAutonomy.text.toString().toFloat()
                val totalValue = (distance * price) / autonomy
                binding.textviewTotalExpenseValue.text = "R$ ${"%.2f".format(totalValue)}"
            } else {
                val errorMessage =
                    if (isDivisibleByAutonomy()) "Preencha todos os campos" else "A Autonomia não pode ser zero."
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }

        } catch (error: Exception) {
            Toast.makeText(this, "Um erro ocorreu, tente novamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValid(): Boolean = isNotEmptyFields() && isDivisibleByAutonomy()

    private fun isNotEmptyFields(): Boolean {
        return binding.editDistance.text.isNotEmpty() && binding.editPrice.text.isNotEmpty() && binding.editAutonomy.text.isNotEmpty()
    }

    private fun isDivisibleByAutonomy(): Boolean =
        isNotEmptyFields() && binding.editAutonomy.text.toString().toFloat() != 0f

    override fun onClick(view: View) {
        // Quando utilizamos o binding.buttonCalculate.setOnClickListener(this) teremos que
        // tratar as açoes dentro do onClick. Se tiver mais de um botão, trataremos todos aqui
        // Para identificar o botão para realizar uma ação utilizaremos o view.id
        // A classe R significa Resourser do android, com ela podemos acessar os recursos
        // especificados em xml
        if (view.id == R.id.button_calculate) calculate()
    }
}



