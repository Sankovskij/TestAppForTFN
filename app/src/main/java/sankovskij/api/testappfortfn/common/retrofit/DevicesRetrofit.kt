package sankovskij.api.testappfortfn.common.retrofit

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class DevicesRetrofit {

    private val baseUrl = "https://dev.api.sls.ompr.io"

    fun getDevices(): DevicesAPI {
        val podRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(createOkHttpClient(PODInterceptor()))
            .build()
        return podRetrofit.create(DevicesAPI::class.java)
    }

    fun deleteDevice(): DeleteDeviceAPI {
        val podRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClient(PODInterceptor()))
            .build()
        return podRetrofit.create(DeleteDeviceAPI::class.java)
    }

    fun resetDevices(): ResetDevicesAPI {
        val podRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClient(PODInterceptor()))
            .build()
        return podRetrofit.create(ResetDevicesAPI::class.java)
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    inner class PODInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }
}
