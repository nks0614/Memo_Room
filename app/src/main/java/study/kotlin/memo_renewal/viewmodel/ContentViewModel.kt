package study.kotlin.memo_renewal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import study.kotlin.memo_renewal.room.DataBase
import study.kotlin.memo_renewal.widget.SingleLiveEvent
import java.text.SimpleDateFormat
import java.util.*

class ContentViewModel : ViewModel() {

    var memoDB : DataBase? = null
    val cTitle = MutableLiveData<String>()
    val cContents = MutableLiveData<String>()
    val updateBtn = SingleLiveEvent<Unit>()
    val delBtn = SingleLiveEvent<Unit>()

    fun delete(title : String, time : String){
        memoDB?.dao()?.delete(title, time)
    }

    fun update(originalTime : String){
        var now : Long = System.currentTimeMillis()
        var date : Date = Date(now)
        var fmNow  : SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        var fmDate : String = fmNow.format(date)

        memoDB?.dao()?.update(cTitle.value.toString(), fmDate, cContents.value.toString(),originalTime)
    }

    fun upBtnClick(){
        updateBtn.call()
    }

    fun delBtnClick(){
        delBtn.call()
    }
}