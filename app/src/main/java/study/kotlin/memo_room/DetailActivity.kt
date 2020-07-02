package study.kotlin.memo_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val contents = intent.getStringExtra("content")
        val title = intent.getStringExtra("title")

        watch_edit_title.text = title
        watch_edit_content.text = contents
    }
}