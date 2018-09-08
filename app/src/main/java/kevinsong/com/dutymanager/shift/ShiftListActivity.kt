package kevinsong.com.dutymanager.shift

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kevinsong.com.dutymanager.DutyManagerApplication
import kevinsong.com.dutymanager.LocationProvider
import kevinsong.com.dutymanager.R
import kotlinx.android.synthetic.main.activity_shift_list.*
import kotlinx.android.synthetic.main.shift_list.*
import javax.inject.Inject

const val LOCATION_PERMISSION_REQUEST_CODE = 1000

class ShiftListActivity : AppCompatActivity() {

    private var twoPane: Boolean = false

    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory

    private lateinit var shiftViewModel: ShiftViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as DutyManagerApplication).appComponent.inject(this)
        setContentView(R.layout.activity_shift_list)
        setSupportActionBar(toolbar)
        toolbar.title = title
        shiftViewModel = ViewModelProviders.of(this, viewModeFactory)
                .get(ShiftViewModel::class.java)
        refreshView()
        fab.setOnClickListener { checkLocationPermission() }
        shiftViewModel.message.observe(this, Observer<String> { t ->
            t?.let {
                Snackbar.make(this@ShiftListActivity.findViewById(android.R.id.content), it, Snackbar.LENGTH_LONG).show()
            }
        })

    }

    private fun refreshView() {
        twoPane = shift_detail_container != null
        shift_list.adapter = SimpleItemRecyclerViewAdapter(this, shiftViewModel.shiftList, twoPane)
        shiftViewModel.getAllShifts(true)
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            startShiftWithLocation()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startShiftWithLocation()

                } else {
                    shiftViewModel.operateShift(null)
                }
            }
        }
    }

    private fun startShiftWithLocation() {
        val locationProvider = LocationProvider()
        locationProvider.getLocation(this)
        locationProvider.location.observe(this, Observer {
            shiftViewModel.operateShift(it)
        })
    }
}
