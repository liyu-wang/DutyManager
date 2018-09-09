package kevinsong.com.dutymanager.shift

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kevinsong.com.data.businessinfo.BusinessInfo
import kevinsong.com.dutymanager.DutyManagerApplication
import kevinsong.com.dutymanager.LocationProvider
import kevinsong.com.dutymanager.R
import kevinsong.com.dutymanager.databinding.ActivityShiftListBinding
import kotlinx.android.synthetic.main.activity_shift_list.*
import kotlinx.android.synthetic.main.shift_list.*
import javax.inject.Inject

const val LOCATION_PERMISSION_REQUEST_CODE = 1000

class ShiftListActivity : AppCompatActivity() {

    private var twoPane: Boolean = false

    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory

    private lateinit var shiftViewModel: ShiftViewModel

    private lateinit var infoViewModel: InfoViewModel
    private lateinit var binding: ActivityShiftListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as DutyManagerApplication).appComponent.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shift_list)
        setSupportActionBar(toolbar)
        shiftViewModel = ViewModelProviders.of(this, viewModeFactory)
                .get(ShiftViewModel::class.java)
        infoViewModel = ViewModelProviders.of(this, viewModeFactory).get(InfoViewModel::class.java)

        fab.setOnClickListener { checkLocationPermission() }
        refreshList()
        refreshToolBar()

    }

    private fun refreshList() {
        twoPane = shift_detail_container != null
        shift_list.adapter = SimpleItemRecyclerViewAdapter(this, shiftViewModel.shiftList, twoPane)
        shiftViewModel.getAllShifts(false)
        shiftViewModel.message.observe(this, Observer<String> { t ->
            t?.let {
                Snackbar.make(this@ShiftListActivity.findViewById(android.R.id.content), it, Snackbar.LENGTH_LONG).show()
            }
        })
        shiftViewModel.inMiddleOfShift.observe(this, Observer<Boolean> { t -> fab.isSelected = t ?: false })

    }

    private fun refreshToolBar() {
        infoViewModel.businessInfo.observe(this, Observer<BusinessInfo> { info ->
            info?.let { binding.info = it }
        })
        title = ""
        infoViewModel.getinfo()
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
                if (grantResults.isNotEmpty()
                        && permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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
