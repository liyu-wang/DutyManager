package kevinsong.com.dutymanager.shift

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kevinsong.com.data.shift.Shift
import kevinsong.com.data.shift.isInProgress
import kevinsong.com.dutymanager.R
import kevinsong.com.dutymanager.databinding.ShiftListContentBinding

class SimpleItemRecyclerViewAdapter(private val parentActivity: ShiftListActivity, liveList: MutableLiveData<List<Shift>>,
                                    private val twoPane: Boolean) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {
    var shiftList: List<Shift> = ArrayList()
    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Shift
            if (twoPane) {
                val fragment = ShiftDetailFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ShiftDetailFragment.SHIFT_OBJECT, item)
                    }
                }
                parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.shift_detail_container, fragment)
                        .commit()
            } else {
                val intent = Intent(v.context, ShiftDetailActivity::class.java).apply {
                    putExtra(ShiftDetailFragment.SHIFT_OBJECT, item)
                }
                v.context.startActivity(intent)
            }
        }

        liveList.observe(parentActivity, Observer<List<Shift>> {
            it?.let {
                shiftList = it.asReversed()
                notifyDataSetChanged()
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ShiftListContentBinding>(LayoutInflater.from(parent.context), R.layout.shift_list_content, parent, false)
        return ViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding: ShiftListContentBinding? = DataBindingUtil.getBinding(holder.itemView)
        val item = shiftList[position]
        binding?.shift = item
        if (item.isInProgress()) {
            binding?.inProgressVisibility = View.VISIBLE
        } else {
            binding?.inProgressVisibility = View.GONE
        }


        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = shiftList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}