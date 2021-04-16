package study.kotlin.memo_renewal.widget

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import study.kotlin.memo_renewal.BR
import study.kotlin.memo_renewal.R
import study.kotlin.memo_renewal.databinding.ItemBinding
import study.kotlin.memo_renewal.model.Memo
import study.kotlin.memo_renewal.view.ContentActivity

class RcViewAdapter : RecyclerView.Adapter<RcViewAdapter.Holder>() {

    var memoList = ArrayList<Memo>()

    inner class Holder(val binding : ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(memo: Memo) {
            binding.setVariable(BR.memo, memo)
            binding.constraintLayout.setOnClickListener {
                val i = Intent(binding.root.context, ContentActivity::class.java)
                i.putExtra("content", memo.content)
                i.putExtra("title", memo.title)
                i.putExtra("time", memo.time)
                i.putExtra("id", memo.id)
                binding.root.context.startActivity(i)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(memoList[position])
    }

    fun setItems(item : ArrayList<Memo>){
        this.memoList = item
        notifyDataSetChanged()
    }

}