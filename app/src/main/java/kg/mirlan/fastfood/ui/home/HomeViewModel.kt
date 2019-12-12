package kg.mirlan.fastfood.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kg.mirlan.fastfood.core.BaseViewModel
import kg.mirlan.fastfood.model.LoadingState
import kg.mirlan.fastfood.repository.HomeRepository
import kg.mirlan.fastfood.utils.AppPreferences
import java.util.*

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val appPreferences: AppPreferences
) : BaseViewModel() {
    //val categories = homeRepository.getCategory()
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    var startDate = MutableLiveData<Date>()
    var endDate = MutableLiveData<Date>()
    val cal = Calendar.getInstance()

    init {
        cal.add(Calendar.DATE, -3)
        startDate.postValue(cal.time)
        endDate.postValue(Calendar.getInstance().time)
    }

    fun setStartDate(date: Date) = startDate.postValue(date)

    fun setEndDate(date: Date) = endDate.postValue(date)

    fun getData() {
        // _loadingState.value = LoadingState.error(e.message)
    }

}

