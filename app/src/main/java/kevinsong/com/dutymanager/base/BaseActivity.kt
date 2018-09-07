package kevinsong.com.dutymanager.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kevinsong.com.dutymanager.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}
