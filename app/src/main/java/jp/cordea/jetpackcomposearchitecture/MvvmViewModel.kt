package jp.cordea.jetpackcomposearchitecture

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import jp.cordea.jetpackcomposearchitecture.response.Id

class MvvmViewModel(
    private val repository: QuestionRepository
) : ViewModel() {
    val items by lazy {
        val liveData = MutableLiveData<List<MvvmListItemModel>>()
        repository.find("mvvm")
            .map { MvvmListItemModelImpl.from(it) }
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
    val onCheckedStateUpdate = MutableLiveData<Id>()

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun clickedItem(link: String) {
        onUriOpen.value = link.toUri()
    }

    fun clickedIcon(id: Id) {
        onCheckedStateUpdate.value = id
    }

    fun clickedFab() {
    }
}
