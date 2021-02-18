package sankovskij.api.testappfortfn.error.view

import sankovskij.api.testappfortfn.devices.model.DeviceList
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ErrorView : MvpView {
    fun init()


}