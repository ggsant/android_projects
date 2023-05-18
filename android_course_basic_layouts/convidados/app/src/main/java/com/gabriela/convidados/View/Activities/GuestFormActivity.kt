import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.gabriela.convidados.R
import com.gabriela.convidados.ViewModel.GuestFormViewModel
import com.gabriela.convidados.databinding.ActivityGuestFormBinding

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        binding.save.setOnClickListener(this)
        binding.present.isChecked = true
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.save -> saveGuestStatus()
        }
    }

    private fun saveGuestStatus() {
    }
}