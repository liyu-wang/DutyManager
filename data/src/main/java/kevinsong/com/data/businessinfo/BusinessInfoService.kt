package kevinsong.com.data.businessinfo

import io.reactivex.Single
import retrofit2.http.GET

interface BusinessInfoService {

    @GET("business")
    fun getBussinessInfo(): Single<BusinessInfo>
}