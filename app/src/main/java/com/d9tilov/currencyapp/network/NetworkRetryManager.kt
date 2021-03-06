package com.d9tilov.currencyapp.network

import io.reactivex.subjects.PublishSubject

class NetworkRetryManager : RetryManager {

    private val retrySubject = PublishSubject.create<RetryEvent>()

    override fun observeRetries(): PublishSubject<RetryEvent> {
        return retrySubject
    }

    override fun retry() {
        retrySubject.onNext(RetryEvent())
    }
}