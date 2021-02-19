package sankovskij.api.testappfortfn.devices.view

import sankovskij.api.testappfortfn.devices.model.DeviceList
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import sankovskij.api.testappfortfn.devices.model.Device

@StateStrategyType(AddToEndSingleStrategy::class)
interface DevicesView : MvpView {
    fun init()
    fun updateRV(deviceList: DeviceList)
    fun notifyDataChanged()
    fun navigateToErrorScreen(error: String?)
    fun removeDeviceFromAdapter(device: Device)

}