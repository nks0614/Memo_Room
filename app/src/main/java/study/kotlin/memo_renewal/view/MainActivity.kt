package study.kotlin.memo_renewal.view

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import study.kotlin.memo_renewal.R
import study.kotlin.memo_renewal.base.BaseActivity
import study.kotlin.memo_renewal.databinding.ActivityMainBinding

import study.kotlin.memo_renewal.model.DataBase
import study.kotlin.memo_renewal.viewmodel.MainViewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private lateinit var backPressHandler: OnBackPressHandler

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this)[MainViewModel::class.java]

    override val layoutRes: Int
        get() = R.layout.activity_main


    override fun init() {
        with(viewModel){
            memoDB = DataBase.getInstance(this@MainActivity)
            load()
        }
        backPressHandler = OnBackPressHandler(this)
    }

    override fun observerViewModel() {
        with(viewModel){
            btn.observe(this@MainActivity, Observer {
                startActivity(Intent(this@MainActivity, AddActivity::class.java))
            })
        }
    }

    override fun onDestroy(){
        DataBase.destroyInstance()
        viewModel.memoDB = null
        super.onDestroy()
    }

    override fun onBackPressed() {
        backPressHandler.onBackPressed()
    }

    inner class OnBackPressHandler(var activity : Activity){
        private var backPressHandler: Long = 0
        fun onBackPressed(){
            if(System.currentTimeMillis() > backPressHandler + 2000){
                backPressHandler = System.currentTimeMillis()
                Toast.makeText(activity,"한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
                return
            }
            else{
              finish()
            }
        }
    }




}