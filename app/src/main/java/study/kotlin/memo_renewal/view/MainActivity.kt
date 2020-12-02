package study.kotlin.memo_renewal.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import study.kotlin.memo_renewal.R
import study.kotlin.memo_renewal.databinding.ActivityMainBinding

import study.kotlin.memo_renewal.model.DataBase
import study.kotlin.memo_renewal.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mBinding : ActivityMainBinding
    lateinit var mViewModel : MainViewModel

    private lateinit var backPressHandler: onBackPressHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mBinding.mainViewModel = mViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()

        mViewModel.memoDB = DataBase.getInstance(this)

        mViewModel.load()

        backPressHandler = onBackPressHandler(this@MainActivity)

        with(mViewModel){
            btn.observe(this@MainActivity, Observer {
                startActivity(Intent(this@MainActivity, AddActivity::class.java))
                finish()
            })
        }

    }

    override fun onDestroy(){
        DataBase.destroyInstance()
        mViewModel.memoDB = null
        super.onDestroy()
        finish()
    }

    override fun onBackPressed() {
        backPressHandler.onBackPressed()
    }

    inner class onBackPressHandler(var activity : Activity){
        private var backPressHandler: Long = 0
        fun onBackPressed(){
            if(System.currentTimeMillis() > backPressHandler+2000){
                backPressHandler = System.currentTimeMillis()
                Toast.makeText(activity,"한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
                return
            }
            else{
                ActivityCompat.finishAffinity(activity)
            }
        }
    }


}