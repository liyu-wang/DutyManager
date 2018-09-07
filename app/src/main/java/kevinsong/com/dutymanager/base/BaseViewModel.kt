package kevinsong.com.dutymanager.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val message = MutableLiveData<String>()
}