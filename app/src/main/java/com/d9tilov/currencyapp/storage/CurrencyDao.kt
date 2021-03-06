package com.d9tilov.currencyapp.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.d9tilov.currencyapp.storage.model.CurrencyDto
import io.reactivex.Flowable

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateCurrencyList(currencyDtoList: List<CurrencyDto>)

    @Query("SELECT * FROM currencyData")
    fun getCurrencyList(): Flowable<List<CurrencyDto>>

}
