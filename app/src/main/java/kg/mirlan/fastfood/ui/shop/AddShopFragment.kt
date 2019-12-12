package kg.mirlan.fastfood.ui.shop

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import kg.mirlan.fastfood.R
import kg.mirlan.fastfood.core.BaseFragment
import kg.mirlan.fastfood.model.Shop
import kotlinx.android.synthetic.main.add_shop_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddShopFragment : BaseFragment(R.layout.add_shop_fragment) {
    // private val categoryAdapter = CategoryAdapter(this::onSelect)
    private val addShopViewModel: AddShopViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_close.setOnClickListener { Navigation.findNavController(view).popBackStack() }
        save_shop.setOnClickListener {
            if (shop_name.text.toString().isNullOrBlank() || shop_password.text.toString().isNullOrBlank()) {
                return@setOnClickListener
            }
            addShopViewModel.addShop(Shop(shop_name.text.toString(), shop_password.text.toString()))

        }
        //category_rv.adapter = categoryAdapter
        //categoryAdapter.submitList(Constants.pddTitles)
    }

//    private fun onSelect(number: Int) {
//        view?.let {
//            val bundle  = bundleOf("pddNumber" to number)
//            Navigation.findNavController(it).navigate(R.id.action_pdd_screen_to_detail_screen, bundle) }
//    }

}