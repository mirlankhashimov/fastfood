package kg.mirlan.fastfood.ui.categories

import kg.mirlan.fastfood.core.BaseViewModel
import kg.mirlan.fastfood.repository.HomeRepository

class CategoriesViewModel(
    private val homeRepository: HomeRepository
) : BaseViewModel() {
    val categories = homeRepository.getCategory()
    fun saveCategory(name: String) {
        homeRepository.saveCategory(name)
    }
    fun deleteCategory(name: String){
        homeRepository.deleteCategory(name)
    }
}