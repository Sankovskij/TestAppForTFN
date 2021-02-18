package sankovskij.api.testappfortfn.common.retrofit

import sankovskij.api.testappfortfn.devices.model.DeviceList
import retrofit2.Call
import retrofit2.http.GET

interface DevicesAPI {
    @GET("api/v1.1/test/devices")
    fun getDevices(): Call<DeviceList>
}
