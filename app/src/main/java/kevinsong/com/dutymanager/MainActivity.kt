package kevinsong.com.dutymanager

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kevinsong.com.dutymanager.shift.ShiftListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_click_me.setOnClickListener {
            startActivity(Intent(this@MainActivity, ShiftListActivity::class.java))
        }
    }
}
