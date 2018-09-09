package kevinsong.com.data.businessinfo

import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BusinessInfoRepositoryTest {

    @Mock
    private lateinit var service: BusinessInfoService

    @Mock
    private lateinit var dao: BusinessInfoDao

    private val mockBusinessInfo = BusinessInfo("deputymock", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/1200px-Google_2015_logo.svg.png")

    @Test
    fun testGetBusinessInfo() {
        Mockito.`when`(service.getBussinessInfo()).thenReturn(Single.create { mockBusinessInfo })
        Mockito.`when`(dao.getBusinessInfo()).thenReturn(Maybe.just(mockBusinessInfo))
        val repository = BusinessInfoRepository(service, dao)
        val bussinessInfo = repository.getBusinessInfo()
        bussinessInfo.subscribe(object : SingleObserver<BusinessInfo> {
            override fun onSuccess(t: BusinessInfo) {
                Assert.assertEquals(t, mockBusinessInfo)
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Assert.fail("BusinessInfoRepositoryTest: ${e.message}")
            }

        })

    }

}