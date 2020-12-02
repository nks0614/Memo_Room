package study.kotlin.memo_renewal.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import study.kotlin.memo_renewal.R
import study.kotlin.memo_renewal.databinding.ActivityAddBinding
import study.kotlin.memo_renewal.model.DataBase
import study.kotlin.memo_renewal.viewmodel.AddViewModel

class AddActivity : AppCompatActivity() {

    lateinit var aBinding : ActivityAddBinding
    lateinit var aViewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        aBinding = DataBindingUtil.setContentView(this, R.layout.activity_add)
        aViewModel = ViewModelProvider(this)[AddViewModel::class.java]
        aBinding.addViewModel = aViewModel
        aBinding.lifecycleOwner = this
        aBinding.executePendingBindings()

        aViewModel.memoDB = DataBase.getInstance(this)

        with(aViewModel){
            btn.observe(this@AddActivity, Observer {
                addMemo()
                startActivity(Intent(this@AddActivity, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            })
        }

    }
    override fun onDestroy(){
        DataBase.destroyInstance()
        aViewModel.memoDB = null
        super.onDestroy()
    }

}