package study.kotlin.memo_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add.*
import study.kotlin.memo_room.DB.Memo
import study.kotlin.memo_room.DB.MemoDB

class AddActivity : AppCompatActivity() {
    private var memoDB : MemoDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        memoDB = MemoDB.getInstance(this)

        val addRunnable = Runnable {
            val newMemo = Memo()
            newMemo.title = edit_title.text.toString()
            newMemo.content = edit_content.text.toString()
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