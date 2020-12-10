package study.kotlin.memo_renewal.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import study.kotlin.memo_renewal.R
import study.kotlin.memo_renewal.base.BaseActivity
import study.kotlin.memo_renewal.databinding.ActivityAddBinding
import study.kotlin.memo_renewal.model.DataBase
import study.kotlin.memo_renewal.viewmodel.AddViewModel

class AddActivity : BaseActivity<ActivityAddBinding, AddViewModel>() {


    override val viewModel: AddViewModel = AddViewModel()

    override val layoutRes: Int
        get() = R.layout.activity_add

    val scope = CoroutineScope(Dispatchers.IO)

    override fun init() {
        with(viewModel){
            memoDB = DataBase.getInstance(this@AddActivity)
        }
    }

    override fun observerViewModel() {
        with(viewModel){
            btn.observe(this@AddActivity, Observer {
                scope.launch {
                    addMemo()
                }

                startActivity(Intent(this@AddActivity, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            })
        }

    }

    override fun onDestroy(){
        DataBase.destroyInstance()
        viewModel.memoDB = null
        super.onDestroy()
    }


}