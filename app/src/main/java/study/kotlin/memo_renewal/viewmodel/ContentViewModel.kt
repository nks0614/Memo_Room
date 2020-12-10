package study.kotlin.memo_renewal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import study.kotlin.memo_renewal.base.BaseViewModel
import study.kotlin.memo_renewal.model.DataBase
import study.kotlin.memo_renewal.widget.SingleLiveEvent
import java.text.SimpleDateFormat
import java.util.*

class ContentViewModel : BaseViewModel() {

    var memoDB : DataBase? = null
    val cTitle = MutableLiveData<String>()
    val cContents = MutableLiveData<String>()
    val updateBtn = SingleLiveEvent<Unit>()
    val delBtn = SingleLiveEvent<Unit>()

    suspend fun delete(id : Long){
        memoDB?.dao()?.delete(id)
    }

    suspend fun update(id : Long){
        var now : Long = System.currentTimeMillis()
        var date : Date = Date(now)
        var fmNow  : SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm")
        var fmDate : String = fmNow.format(date)

        memoDB?.dao()?.update(cTitle.value.toString(), fmDate, cContents.value.toString(), id)
    }

    fun upBtnClick(){
        updateBtn.call()
    }

    fun delBtnClick(){
        delBtn.call()
    }
}