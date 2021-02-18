package sankovskij.api.testappfortfn.common.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Path

interface DeleteDeviceAPI {
    @DELETE("api/v1.1/test/devices/{id}")
    fun DeleteDevice(@Path("id") id: Int?): Call<ResponseBody>
}
