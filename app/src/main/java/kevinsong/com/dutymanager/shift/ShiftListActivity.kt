package kevinsong.com.dutymanager.shift

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kevinsong.com.dutymanager.DutyManagerApplication
import kevinsong.com.dutymanager.R
import kotlinx.android.synthetic.main.activity_shift_list.*
import kotlinx.android.synthetic.main.shift_list.*
import javax.inject.Inject

class ShiftListActivity : AppCompatActivity() {

    private var twoPane: Boolean = false

    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory

    lateinit var shiftViewModel: ShiftViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as DutyManagerApplication).appComponent.inject(this)
        setContentView(R.layout.activity_shift_list)
        setSupportActionBar(toolbar)
        toolbar.title = title
        shiftViewModel = ViewModelProviders.of(this, viewModeFactory).get(ShiftViewModel::class.java)
        refreshView()
        fab.setOnClickListener {
            shiftViewModel.startShift()
        }
    }

    private fun refreshView() {
        twoPane = shift_detail_container != null
        shift_list.adapter = SimpleItemRecyclerViewAdapter(this, shiftViewModel.shiftList, twoPane)
        shiftViewModel.getAllShifts(true)
    }
}
