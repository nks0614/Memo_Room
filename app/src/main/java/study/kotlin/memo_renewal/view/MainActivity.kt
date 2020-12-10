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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import study.kotlin.memo_renewal.R
import study.kotlin.memo_renewal.base.BaseActivity
import study.kotlin.memo_renewal.databinding.ActivityMainBinding

import study.kotlin.memo_renewal.model.DataBase
import study.kotlin.memo_renewal.viewmodel.MainViewModel
import study.kotlin.memo_renewal.widget.RcViewAdapter

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {


    private lateinit var backPressHandler: OnBackPressHandler

    override val viewModel: MainViewModel = MainViewModel()

    override val layoutRes: Int
        get() = R.layout.activity_main

    val scope = CoroutineScope(Dispatchers.IO)

    val mAdapter = RcViewAdapter(viewModel.memoList)


    override fun init() {
        mRcView.adapter = mAdapter
        with(viewModel){
            memoDB = DataBase.getInstance(this@MainActivity)
            load()

        }
        mAdapter.notifyDataSetChanged()

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