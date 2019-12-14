package com.d9tilov.currencyapp.rates

import androidx.annotation.WorkerThread
import com.d9tilov.currencyapp.network.CurrencyRemoteRepository
import com.d9tilov.currencyapp.rates.repository.CurrencyItem
import com.d9tilov.currencyapp.rates.repository.CurrencyRateData
import com.d9tilov.currencyapp.storage.CurrencyLocalRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import java.math.BigDecimal

class CurrencyRateInteractor(
    private val currencyLocalRepository: CurrencyLocalRepository,
    private val currencyRemoteRepository: CurrencyRemoteRepository,
    private val schedulerIo: Scheduler,
    private val schedulerMain: Scheduler
) {

    @WorkerThread
    fun updateCurrencyRates(): Completable {
        return Completable.fromSingle(
            currencyRemoteRepository.updateCurrencyRates()
                .map { t: CurrencyRateData ->
                    currencyLocalRepository.updateCurrencyList(t)
                })
            .subscribeOn(schedulerIo)
            .observeOn(schedulerMain)
    }

    @WorkerThread
    fun getAllCurrencies(): Flowable<List<CurrencyItem>> {
        return currencyLocalRepository.getAllCurrencies()
            .subscribeOn(schedulerIo)
            .observeOn(schedulerMain)
    }

    @WorkerThread
    fun changeBaseCurrency(baseCurrency: CurrencyItem): Completable {
        currencyLocalRepository.updateBaseCurrency(baseCurrency)
        return updateCurrencyRates()
    }

    fun changeValue(value: BigDecimal): Completable {
        currencyLocalRepository.changeValue(value)
        return updateCurrencyRates()

    }

    fun writeDataAndCancel() {
        currencyLocalRepository.cancelWorking()
    }
}
