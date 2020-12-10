package study.kotlin.memo_renewal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import study.kotlin.memo_renewal.base.BaseViewModel
import study.kotlin.memo_renewal.model.DataBase
import study.kotlin.memo_renewal.model.Memo
import study.kotlin.memo_renewal.widget.SingleLiveEvent
import java.text.SimpleDateFormat
import java.util.*


class AddViewModel : BaseViewModel() {

    var memoDB : DataBase? = null
    val btn = SingleLiveEvent<Unit>()
    val title = MutableLiveData<String>()
    val contents = MutableLiveData<String>()

    suspend fun addMemo(){
        var now : Long = System.currentTimeMillis()
        var date : Date = Date(now)
        var fmNow  : SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm")
        var fmDate : String = fmNow.format(date)

        val newMemo = Memo(
            0,
            title.value.toString(),
            fmDate,
            contents.value.toString()
        )
        memoDB?.dao()?.insert(newMemo)
    }

    fun btnClick(){
        btn.call()
    }

}