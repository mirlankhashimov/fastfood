package kg.mirlan.fastfood.ui.shop

import kg.mirlan.fastfood.core.BaseViewModel
import kg.mirlan.fastfood.model.Shop
import kg.mirlan.fastfood.repository.HomeRepository

class AddShopViewModel(
    private val homeRepository: HomeRepository
) : BaseViewModel() {
    fun addShop(shop: Shop) = homeRepository.addShop(shop)
}