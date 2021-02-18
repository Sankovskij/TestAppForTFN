package sankovskij.api.testappfortfn.devices.presenter

import sankovskij.api.testappfortfn.devices.model.DeviceList
import moxy.InjectViewState
import moxy.MvpPresenter
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sankovskij.api.testappfortfn.common.retrofit.DevicesRetrofit
import sankovskij.api.testappfortfn.devices.view.DevicesView
import sankovskij.api.testappfortfn.devices.model.Device
import javax.inject.Inject

@InjectViewState
class DevicesPresenter() : MvpPresenter<DevicesView>() {


    @Inject
    lateinit var  deviceRetrofit: DevicesRetrofit

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        getDevicesFromServer()
    }


    private fun getDevicesFromServer() {
        deviceRetrofit.getDevices().getDevices().enqueue(object :
            Callback<DeviceList> {
            override fun onResponse(call: Call<DeviceList>, response: Response<DeviceList>) {
                if (response.isSuccessful && response.body() != null) {
                    viewState.updateRV(response.body()!!)
                }
            }

            override fun onFailure(call: Call<DeviceList>, t: Throwable) {
                viewState.navigateToErrorScreen()
            }
        }
        )
    }

    fun deleteDeviceFromServer(device : Device) {
        deviceRetrofit.deleteDevice().DeleteDevice(device.id).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                viewState.removeDeviceFromAdapter(device)
                viewState.notifyDataChanged()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                viewState.navigateToErrorScreen()
            }
        })
    }

    fun resetDeviceFromServer() {
        viewState.init()
        deviceRetrofit.resetDevices().resetDevices().enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                getDevicesFromServer()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                viewState.navigateToErrorScreen()
            }
        })
    }
}
