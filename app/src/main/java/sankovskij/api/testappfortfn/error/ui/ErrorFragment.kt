package sankovskij.api.testappfortfn.error.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_error.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import sankovskij.api.testappfortfn.App
import sankovskij.api.testappfortfn.R
import sankovskij.api.testappfortfn.devices.presenter.DevicesPresenter
import sankovskij.api.testappfortfn.error.presenter.ErrorPresenter
import sankovskij.api.testappfortfn.error.view.ErrorView

class ErrorFragment : MvpAppCompatFragment(), ErrorView {

    val presenter: ErrorPresenter by moxyPresenter {
        ErrorPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.instance.appComponent.inject(this)
        return View.inflate(context, R.layout.fragment_error, null)

    }
    override fun init() {
        error_button.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_error_to_fragment_with_devices2)
        }
    }
}
