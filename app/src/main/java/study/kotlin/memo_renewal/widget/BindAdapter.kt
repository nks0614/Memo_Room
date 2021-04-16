package study.kotlin.memo_renewal.widget

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import study.kotlin.memo_renewal.model.Memo

@BindingAdapter("memoItems")
fun RecyclerView.setMemoItems(items : ArrayList<Memo>?){
    if(adapter == null){
        this.adapter = RcViewAdapter()
        this.layoutManager = LinearLayoutManager(context)
    }
    items?.let { (adapter as RcViewAdapter).setItems(it) }
}
