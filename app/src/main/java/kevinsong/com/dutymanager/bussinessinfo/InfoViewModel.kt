package kevinsong.com.dutymanager.bussinessinfo

import android.arch.lifecycle.MutableLiveData
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kevinsong.com.data.businessinfo.BusinessInfo
import kevinsong.com.data.businessinfo.BusinessInfoRepository
import kevinsong.com.dutymanager.base.BaseViewModel

class InfoViewModel(val infoRepository: BusinessInfoRepository) : BaseViewModel() {

    val businessInfo = MutableLiveData<BusinessInfo>()

    fun getinfo() {
        infoRepository.getBusinessInfo().subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<BusinessInfo> {
                    override fun onSuccess(info: BusinessInfo) {
                        businessInfo.value = info
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message ?: "Get bussiness info failed"
                    }
                })
    }
}