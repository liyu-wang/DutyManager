package kevinsong.com.data.businessinfo

import io.reactivex.Single


class BusinessInfoRepository(private val service: BusinessInfoService, private val dao: BusinessInfoDao) {

    private var cachedBusinessInfo: BusinessInfo? = null

    fun getBusinessInfo(): Single<BusinessInfo> {
        cachedBusinessInfo?.let {
            return Single.create { cachedBusinessInfo }
        }

        var localResult = dao.getBusinessInfo().doAfterSuccess {
            cachedBusinessInfo = it
            dao.saveBusinessInfo(it)
        }
        var remoteResult = service.getBussinessInfo().doAfterSuccess {
            cachedBusinessInfo = it
        }

        return localResult.filter {
            it != null
        }.switchIfEmpty(remoteResult)
    }
}