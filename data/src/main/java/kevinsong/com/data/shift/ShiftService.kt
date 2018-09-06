package kevinsong.com.data.shift

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ShiftService {

    @GET("shifts")
    fun getAllShifts(): Single<List<Shift>>

    @POST("shift/start")
    fun startShift(@Body startRequest: ShiftRequest): Completable

    @POST("shift/end")
    fun endShift(@Body endRequest: ShiftRequest): Completable
}