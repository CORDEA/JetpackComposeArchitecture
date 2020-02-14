package jp.cordea.jetpackcomposearchitecture

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MvvmViewModel(
    private val repository: QuestionRepository
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val items by lazy {
        val liveData = MutableLiveData<List<MvvmListItemModel>>()
        repository.find("mvvm")
            .map { MvvmListItemModel.from(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveData.value = it
            }, {
            })
            .addTo(compositeDisposable)
        liveData
    }

    val onUriOpen = MutableLiveData<Uri>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun clickedItem(link: String) {
        onUriOpen.value = link.toUri()
    }
}
