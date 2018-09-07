package kevinsong.com.data.shift

import io.reactivex.Single

class ShiftRepositoryImpl(private val service: ShiftService, private val dao: ShiftDao) : ShiftRepository {

    var cachedResult: List<Shift>? = null

    override fun getShifts(forceUpdate: Boolean): Single<List<Shift>> {
        var remoteResult: Single<List<Shift>> = service.getAllShifts().doAfterSuccess {
            it?.let {
                cachedResult = it
                dao.saveShifts(it)
            }
        }

        if (forceUpdate) return remoteResult

        cachedResult?.let { return Single.create { cachedResult } }

        var localResult: Single<List<Shift>> = dao.getAllShfits()
        return localResult.filter {
            !it.isEmpty()
        }.switchIfEmpty(remoteResult)
    }

    override fun startShift(shiftBody: ShiftRequest) = service.startShift(shiftBody)
    override fun stopShift(shiftBody: ShiftRequest) = service.endShift(shiftBody)

}