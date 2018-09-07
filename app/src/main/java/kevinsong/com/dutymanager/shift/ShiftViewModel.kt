package kevinsong.com.dutymanager.shift

import android.arch.lifecycle.MutableLiveData
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import kevinsong.com.data.shift.Shift
import kevinsong.com.data.shift.ShiftRepository
import kevinsong.com.data.shift.ShiftRequest
import kevinsong.com.dutymanager.base.BaseViewModel
import java.util.*
import javax.inject.Inject

class ShiftViewModel
@Inject constructor(private val shiftRepository: ShiftRepository) : BaseViewModel() {
    var shiftList = MutableLiveData<List<Shift>>()

    fun getAllShifts(forceUpdate: Boolean) {
        shiftRepository.getShifts(forceUpdate)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Shift>> {
                    override fun onSuccess(list: List<Shift>) {
                        shiftList.value = list
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message ?: "Get shifts failed."
                    }
                })
    }

    fun startShift() {
        val body = ShiftRequest(Date().toString())
        shiftRepository.startShift(body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : DisposableCompletableObserver() {
            override fun onComplete() {
                message.value = "Start shift successfully."
            }

            override fun onError(e: Throwable) {
                message.value = e.message ?: "Start shift failed."
            }
        })
    }

    fun endShift() {
        val body = ShiftRequest(Date().toString())
        shiftRepository.stopShift(body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : DisposableCompletableObserver() {
            override fun onComplete() {
                message.value = "End shift successfully."
            }

            override fun onError(e: Throwable) {
                message.value = e.message ?: "End shift failed."
            }
        })
    }
}