package sankovskij.api.testappfortfn.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import sankovskij.api.testappfortfn.App
import sankovskij.api.testappfortfn.R
import sankovskij.api.testappfortfn.main.presenter.MainPresenter
import sankovskij.api.testappfortfn.main.view.MainView

class MainActivity : MvpAppCompatActivity(), MainView {

    val presenter: MainPresenter by moxyPresenter  {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.appComponent.inject(this)
    }
}