package study.kotlin.memo_renewal.viewmodel

import androidx.lifecycle.ViewModel
import study.kotlin.memo_renewal.room.DataBase
import study.kotlin.memo_renewal.room.Memo
import study.kotlin.memo_renewal.widget.RcViewAdapter
import study.kotlin.memo_renewal.widget.SingleLiveEvent

class MainViewModel : ViewModel() {

    val btn = SingleLiveEvent<Unit>()

    private var memos = listOf<Memo>()
    private val memoList = ArrayList<Memo>()

    val mAdapter = RcViewAdapter()

    init {
        mAdapter.setList(memoList)
    }

    var memoDB : DataBase? = null

    fun load(){
        memoList.clear()
        memos = memoDB?.dao()?.getAll()!!
        memoList.addAll(memos)
        mAdapter.notifyDataSetChanged()
    }

    fun btnClick(){
        btn.call()
    }
}