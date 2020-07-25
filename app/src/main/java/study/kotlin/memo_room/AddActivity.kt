package study.kotlin.memo_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add.*
import study.kotlin.memo_room.DB.Memo
import study.kotlin.memo_room.DB.MemoDB
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {
    private var memoDB : MemoDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        memoDB = MemoDB.getInstance(this)

        val addRunnable = Runnable {
            var now : Long = System.currentTimeMillis()
            var date : Date = Date(now)
            var fmNow  : SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
            var fmDate : String = fmNow.format(date)
            val newMemo = Memo()

            newMemo.title = edit_title.text.toString()
            newMemo.content = edit_content.text.toString()
            newMemo.time = fmDate
            memoDB?.MemoDao()?.insert(newMemo)
        }

        add_text_btn.setOnClickListener {
            val addThread = Thread(addRunnable)
            addThread.start()

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onDestroy() {
        MemoDB.destroyInstance()
        super.onDestroy()
    }
}