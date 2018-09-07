package kevinsong.com.dutymanager.shift

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kevinsong.com.dutymanager.R
import kotlinx.android.synthetic.main.activity_shift_detail.*

class ShiftDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shift_detail)
        setSupportActionBar(detail_toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = ShiftDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ShiftDetailFragment.SHIFT_OBJECT,
                            intent.getSerializableExtra(ShiftDetailFragment.SHIFT_OBJECT))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.shift_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    navigateUpTo(Intent(this, ShiftListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
