package study.kotlin.memo_renewal.viewmodel

import androidx.lifecycle.ViewModel
import study.kotlin.memo_renewal.room.DataBase
import study.kotlin.memo_renewal.room.Memo
import study.kotlin.memo_renewal.widget.RcViewAdapter
import study.kotlin.memo_renewal.widget.SingleLiveEvent

class MainViewModel : ViewModel() {

    val btn = SingleLiveEvent<Unit>()

    private var memoList = ArrayList<Memo>()

    val mAdapter = RcViewAdapter()

    init { mAdapter.setList(memoList) }

    var memoDB : DataBase? = null

    fun load(){
        memoList.clear()
        memoList.addAll(memoDB?.dao()?.getAll()!!)
        mAdapter.notifyDataSetChanged()
    }

    fun btnClick(){
        btn.call()
    }
}