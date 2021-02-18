package sankovskij.api.testappfortfn.common.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.POST

interface ResetDevicesAPI {
    @POST("api/v1.1/test/devices/reset-deleted")
    fun resetDevices(): Call<ResponseBody>
}
