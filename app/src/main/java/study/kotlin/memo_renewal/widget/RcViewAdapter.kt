package study.kotlin.memo_renewal.widget

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import study.kotlin.memo_renewal.R
import study.kotlin.memo_renewal.model.Memo
import study.kotlin.memo_renewal.view.ContentActivity

class RcViewAdapter(val memoList : ArrayList<Memo>) : RecyclerView.Adapter<RcViewAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv = itemView.findViewById<TextView>(R.id.title)
        val timeTv = itemView.findViewById<TextView>(R.id.time)
        fun bind(memo: Memo) {
            titleTv.text = memo.title
            timeTv.text = memo.time
            itemView.setOnClickListener {
                val i = Intent(itemView.context, ContentActivity::class.java)
                i.putExtra("content", memo.content)
                i.putExtra("title", memo.title)
                i.putExtra("time", memo.time)
                i.putExtra("id", memo.id)

                itemView.context.startActivity(i)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(memoList[position])
    }

}