package study.kotlin.memo_renewal.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import study.kotlin.memo_renewal.MainActivity
import study.kotlin.memo_renewal.R
import study.kotlin.memo_renewal.databinding.ActivityContentBinding
import study.kotlin.memo_renewal.room.DataBase
import study.kotlin.memo_renewal.viewmodel.ContentViewModel

class ContentActivity : AppCompatActivity() {

    lateinit var cBinding : ActivityContentBinding
    lateinit var cViewModel : ContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cBinding = DataBindingUtil.setContentView(this, R.layout.activity_content)
        cViewModel = ViewModelProvider(this)[ContentViewModel::class.java]
        cBinding.contentViewModel = cViewModel
        cBinding.lifecycleOwner = this
        cBinding.executePendingBindings()

        cViewModel.memoDB = DataBase.getInstance(this)

        val contents = intent.getStringExtra("content")
        val title = intent.getStringExtra("title")
        val time = intent.getStringExtra("time")

        with(cViewModel){
            cTitle.value = title
            cContents.value = contents

            delBtn.observe(this@ContentActivity, Observer {
                delete(title, time)
                startActivity(Intent(this@ContentActivity, MainActivity::class.java))
                finish()
            })

            updateBtn.observe(this@ContentActivity, Observer {
                update(time)
                startActivity(Intent(this@ContentActivity, MainActivity::class.java))
                finish()
            })
        }

    }
    override fun onDestroy(){
        DataBase.destroyInstance()
        cViewModel.memoDB = null
        super.onDestroy()
    }
}