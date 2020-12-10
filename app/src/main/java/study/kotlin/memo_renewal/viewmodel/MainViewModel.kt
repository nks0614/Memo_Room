package study.kotlin.memo_renewal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import study.kotlin.memo_renewal.base.BaseViewModel
import study.kotlin.memo_renewal.model.DataBase
import study.kotlin.memo_renewal.model.Memo
import study.kotlin.memo_renewal.widget.RcViewAdapter
import study.kotlin.memo_renewal.widget.SingleLiveEvent

class MainViewModel : BaseViewModel() {

    val btn = SingleLiveEvent<Unit>()

    var memoList = ArrayList<Memo>()

    var memoDB : DataBase? = null

    fun load(){
        memoList.clear()
        memoList.addAll(memoDB?.dao()?.getAll()!!)
    }

    fun btnClick(){
        btn.call()
    }
}