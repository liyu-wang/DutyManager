package kevinsong.com.data.shift

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
class ShitRepositoryTest {

    @Mock
    lateinit var service: ShiftService
    @Mock
    lateinit var dao: ShiftDao

    private var shiftList: List<Shift> = listOf(Shift(1), Shift(2))

    @Test
    fun testGetShifts() {
        Mockito.`when`(service.getAllShifts()).thenReturn(Single.create { shiftList })
        Mockito.`when`(dao.getAllShfits()).thenReturn(Single.create { emptyList<Shift>() })
        val repository: ShiftRepository = ShiftRepositoryImpl(service, dao)
        val shifts = repository.getShifts(true)
        shifts.subscribe(object : SingleObserver<List<Shift>> {
            override fun onSuccess(t: List<Shift>) {
                Assert.assertEquals(t, shiftList)
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Assert.fail("ShitRepositoryTest: " + e.message)
            }
        })
    }
}