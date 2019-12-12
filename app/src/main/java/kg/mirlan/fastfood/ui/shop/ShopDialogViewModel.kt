package kg.mirlan.fastfood.ui.shop

import kg.mirlan.fastfood.core.BaseViewModel
import kg.mirlan.fastfood.repository.HomeRepository

class ShopDialogViewModel(
    private val homeRepository: HomeRepository
): BaseViewModel() {
    val shops = homeRepository.getShops()
}