package kevinsong.com.dutymanager.shift

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kevinsong.com.data.businessinfo.BusinessInfo
import kevinsong.com.data.businessinfo.BusinessInfoRepository
import kevinsong.com.dutymanager.base.BaseViewModel
import javax.inject.Inject

class InfoViewModel
@Inject constructor(private val infoRepository: BusinessInfoRepository) : BaseViewModel() {

    val businessInfo = MutableLiveData<BusinessInfo>()

    fun getinfo() {
        infoRepository.getBusinessInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<BusinessInfo> {
                    override fun onSuccess(info: BusinessInfo) {
                        businessInfo.value = info
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d("InfoViewModel", d.toString())
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message ?: "Get bussiness info failed"
                    }
                })
    }
}