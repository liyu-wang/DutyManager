package kevinsong.com.data.shift

import io.reactivex.Completable
import io.reactivex.Single

interface ShiftRepository {

    fun getShifts(forceUpdate: Boolean): Single<List<Shift>>

    fun startShift(shiftBody: ShiftRequest): Completable

    fun stopShift(shiftBody: ShiftRequest): Completable
}