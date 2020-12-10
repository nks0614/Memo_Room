package study.kotlin.memo_renewal.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import study.kotlin.memo_renewal.R
import study.kotlin.memo_renewal.base.BaseActivity
import study.kotlin.memo_renewal.databinding.ActivityContentBinding
import study.kotlin.memo_renewal.model.DataBase
import study.kotlin.memo_renewal.viewmodel.ContentViewModel

class ContentActivity : BaseActivity<ActivityContentBinding, ContentViewModel>() {

    override val viewModel: ContentViewModel = ContentViewModel()

    override val layoutRes: Int
        get() = R.layout.activity_content

    var id : Long = 0
    lateinit var contents : String
    lateinit var title : String

    val scope = CoroutineScope(Dispatchers.IO)

    override fun init() {
        id = intent.getLongExtra("id", 0)
        contents = intent.getStringExtra("content")
        title = intent.getStringExtra("title")

        with(viewModel){
            memoDB = DataBase.getInstance(this@ContentActivity)
            cTitle.value = title
            cContents.value = contents
        }
    }

    override fun observerViewModel() {
        with(viewModel){
            delBtn.observe(this@ContentActivity, Observer {
                scope.launch {
                    delete(id)
                }
                startActivity(Intent(this@ContentActivity, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            })

            updateBtn.observe(this@ContentActivity, Observer {
                scope.launch {
                    update(id)
                }
                startActivity(Intent(this@ContentActivity, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
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