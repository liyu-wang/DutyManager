package kevinsong.com.dutymanager

import android.arch.lifecycle.Observer
import io.reactivex.Single
import kevinsong.com.data.businessinfo.BusinessInfo
import kevinsong.com.data.businessinfo.BusinessInfoRepository
import kevinsong.com.dutymanager.shift.InfoViewModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class InfoViewModelTest {

    @Mock
    lateinit var repository: BusinessInfoRepository

    private val info: BusinessInfo = BusinessInfo("duty", "https://test/test.image")

    @Test
    fun testGetInfo() {
        val single: Single<BusinessInfo> = Single.just(info)
        Mockito.doReturn(single).`when`(repository).getBusinessInfo()
        val viewModel = InfoViewModel(repository)
        viewModel.getinfo()
        viewModel.businessInfo.observeForever {
            Observer<BusinessInfo> { t -> Assert.assertEquals(t, info) }
        }
    }
}