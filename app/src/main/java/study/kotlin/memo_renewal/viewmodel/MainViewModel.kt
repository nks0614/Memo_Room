package study.kotlin.memo_renewal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import study.kotlin.memo_renewal.base.BaseViewModel
import study.kotlin.memo_renewal.model.DataBase
import study.kotlin.memo_renewal.model.Memo
import study.kotlin.memo_renewal.widget.RcViewAdapter
import study.kotlin.memo_renewal.widget.SingleLiveEvent

class MainViewModel : BaseViewModel() {

    val btn = SingleLiveEvent<Unit>()

    var memoList : MutableLiveData<ArrayList<Memo>> = MutableLiveData()

    var memoDB : DataBase? = null

    fun load(){
        memoDB?.let {
            memoList.value = ArrayList(it.dao().getAll())
        }

    }

    fun btnClick(){
        btn.call()
    }
}