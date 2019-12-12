package kg.mirlan.fastfood.ui.shop

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import kg.mirlan.fastfood.R
import kg.mirlan.fastfood.core.BaseFragment
import kg.mirlan.fastfood.model.Shop
import kg.mirlan.fastfood.ui.adapter.ShopsAdapter
import kg.mirlan.fastfood.utils.AppPreferences
import kotlinx.android.synthetic.main.bottom_sheet_shop_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ShopDialogFragment : BaseFragment(R.layout.bottom_sheet_shop_fragment) {
    private val shopDialogViewModel: ShopDialogViewModel by viewModel()
    private val appPreferences: AppPreferences by inject()
    private val shopsAdapter by lazy { ShopsAdapter(this::onShopSelect) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shops_rv.adapter = shopsAdapter
        setClicks()
        shopDialogViewModel.shops.observe(viewLifecycleOwner, Observer {
            shopsAdapter.currentShop = appPreferences.shopNameKey.toString()
            shopsAdapter.submitList(it)
        })
    }

    private fun setClicks() {
        add.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_bottom_sheet_dialog_screen_to_add_shop_screen)

        }
    }

    private fun onShopSelect(shop: Shop) {
        appPreferences.shopNameKey = shop.name
        NavHostFragment.findNavController(this).popBackStack()
    }
}