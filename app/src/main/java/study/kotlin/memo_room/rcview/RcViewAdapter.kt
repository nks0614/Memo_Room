package study.kotlin.memo_room.rcview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_memo.view.*
import study.kotlin.memo_room.DB.Memo
import study.kotlin.memo_room.R

class RcViewAdapter(val context: Context, val memos: List<Memo>) : RecyclerView.Adapter<RcViewAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView?.findViewById<TextView>(R.id.item_title_text)

        fun bind(memo: Memo) {
            title?.text = memo.title
        }
    }


    override fun getItemCount(): Int {
        return memos.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_memo, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(memos[position])
    }


}