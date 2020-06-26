package study.kotlin.memo_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import study.kotlin.memo_room.DB.Memo
import study.kotlin.memo_room.DB.MemoDB
import study.kotlin.memo_room.rcview.RcViewAdapter
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var memoDB : MemoDB? = null
    private var memoList = listOf<Memo>()
    lateinit var mAdapter : RcViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memoDB = MemoDB.getInstance(this)
        mAdapter = RcViewAdapter(this, memoList)

        val r = Runnable {
            try {
                memoList = memoDB?.MemoDao()?.getAll()!!
                mAdapter = RcViewAdapter(this,memoList)
                mAdapter.notifyDataSetChanged()

                rcView.adapter = mAdapter
                rcView.layoutManager = LinearLayoutManager(this)
                rcView.setHasFixedSize(true)

            } catch (e : Exception){
                Log.d("checkk", "Error : $e")
            }
        }

        val thread = Thread(r)
        thread.start()

        write_text_btn.setOnClickListener {
            val i = Intent(this, AddActivity::class.java)
            startActivity(i)
        }
    }

    override fun onDestroy() {
        MemoDB.destroyInstance()
        memoDB = null
        super.onDestroy()
    }
}