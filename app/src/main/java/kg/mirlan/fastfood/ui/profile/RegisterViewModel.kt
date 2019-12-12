package kg.mirlan.fastfood.ui.profile

import com.google.firebase.auth.FirebaseUser
import kg.mirlan.fastfood.core.BaseViewModel
import kg.mirlan.fastfood.repository.HomeRepository

class RegisterViewModel(
    private val homeRepository: HomeRepository
): BaseViewModel() {
    fun saveUser(user: FirebaseUser?) {
        homeRepository.saveUser(user)
    }
}