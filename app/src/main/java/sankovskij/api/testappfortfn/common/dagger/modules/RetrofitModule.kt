package sankovskij.api.testappfortfn.common.dagger.modules

import dagger.Module
import dagger.Provides
import sankovskij.api.testappfortfn.common.retrofit.DevicesRetrofit
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun devicesRetrofit() = DevicesRetrofit()
}



