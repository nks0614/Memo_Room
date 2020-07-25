package study.kotlin.memo_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*
import study.kotlin.memo_room.DB.MemoDB

class DetailActivity : AppCompatActivity() {
    private var memoDB : MemoDB? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val contents = intent.getStringExtra("content")
        val title = intent.getStringExtra("title")
        val time = intent.getStringExtra("time")

        watch_edit_title.text = title
        watch_edit_content.text = contents
        
        memoDB = MemoDB.getInstance(this);
        
        val delRun = Runnable { 
            memoDB?.MemoDao()?.delete(time)
        }
        
        del_btn.setOnClickListener { 
            var delThread = Thread(delRun)
            delThread.start()

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}