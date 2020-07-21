package com.siscofran.testcermati.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.siscofran.testcermati.data.ApiRepository
import com.siscofran.testcermati.data.model.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainViewModel @Inject constructor(private val apiRepository: ApiRepository, application: Application) : AndroidViewModel(application) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val user = MutableLiveData<ArrayList<Item>>()
    private val page = MutableLiveData<Int>()
    private val loadMore = MutableLiveData<ArrayList<Item>>()
    private val isConnected = MutableLiveData<Boolean>()

    init {
        compositeDisposable.add(ReactiveNetwork.observeNetworkConnectivity(application)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { connect ->
                isConnected.postValue(connect.available())
            })
    }

    fun getGithubUsers(name: String) {
        page.value = 1
        Log.d("viewModel", "masukk -> ${page.value}")
        compositeDisposable.add(
            apiRepository.getDataUser(name, page.value?.toInt()!!, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    user.value = it.items
                },{
                    user.value = null
                }))
    }

    fun loadMore(name: String) {
        page.value = page.value?.plus(1)
        Log.d("viewModel", "masukk page -> ${page.value}")
        compositeDisposable.add(
            apiRepository.getDataUser(name, page.value?.toInt()!!, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loadMore.value = it.items
                },{
                    loadMore.value = null
                }))
    }

    fun getUser() = user

    fun getLoadMore() = loadMore

    fun getNetwork() = isConnected

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}