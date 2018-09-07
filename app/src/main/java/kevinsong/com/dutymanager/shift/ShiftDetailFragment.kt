package kevinsong.com.dutymanager.shift

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kevinsong.com.data.shift.Shift
import kevinsong.com.dutymanager.R
import kotlinx.android.synthetic.main.activity_shift_detail.*
import kotlinx.android.synthetic.main.shift_detail.view.*

class ShiftDetailFragment : Fragment() {

    private var item: Shift? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(SHIFT_OBJECT)) {
                item = it.getSerializable(SHIFT_OBJECT) as Shift?
                activity?.toolbar_layout?.title = item?.id.toString()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.shift_detail, container, false)
        item?.let {
            rootView.shift_detail.text = it.start
        }

        return rootView
    }

    companion object {
        const val SHIFT_OBJECT = "shift_object"
    }
}
