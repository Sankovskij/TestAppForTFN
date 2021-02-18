package sankovskij.api.testappfortfn.common.dagger

import dagger.Component
import sankovskij.api.testappfortfn.common.dagger.modules.AppModule
import sankovskij.api.testappfortfn.common.dagger.modules.RetrofitModule
import sankovskij.api.testappfortfn.devices.presenter.DevicesPresenter
import sankovskij.api.testappfortfn.devices.ui.DevicesFragment
import sankovskij.api.testappfortfn.error.presenter.ErrorPresenter
import sankovskij.api.testappfortfn.error.ui.ErrorFragment
import sankovskij.api.testappfortfn.main.presenter.MainPresenter
import sankovskij.api.testappfortfn.main.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RetrofitModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(devicesPresenter: DevicesPresenter)
    fun inject(devicesFragment: DevicesFragment)
    fun inject(errorFragment: ErrorFragment)
    fun inject(errorPresenter: ErrorPresenter)

}


