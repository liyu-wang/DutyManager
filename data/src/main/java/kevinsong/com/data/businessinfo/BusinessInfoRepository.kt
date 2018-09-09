package kevinsong.com.data.businessinfo

import io.reactivex.Single


open class BusinessInfoRepository(private val service: BusinessInfoService, private val dao: BusinessInfoDao) {

    private var cachedBusinessInfo: BusinessInfo? = null

    open fun getBusinessInfo(): Single<BusinessInfo> {
        cachedBusinessInfo?.let {
            return Single.just(cachedBusinessInfo)
        }

        var localResult = dao.getBusinessInfo().doAfterSuccess {
            it?.let {
                cachedBusinessInfo = it
            }
        }
        var remoteResult = service.getBussinessInfo().doAfterSuccess {
            cachedBusinessInfo = it
            dao.saveBusinessInfo(it)
        }

        return localResult.filter {
            it != null
        }.switchIfEmpty(remoteResult)
    }
}