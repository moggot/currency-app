package com.d9tilov.currencyapp.network

import com.d9tilov.currencyapp.network.data.CurrencyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RevolutApi {

    @GET("latest")
    fun getCurrencies(@Query("base") baseCurrency: String): Single<CurrencyResponse>
}
