package kg.mirlan.fastfood.ui.categories

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import kg.mirlan.fastfood.R
import kg.mirlan.fastfood.core.BaseFragment
import kg.mirlan.fastfood.model.Category
import kg.mirlan.fastfood.ui.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.categories_fragment.*
import org.jetbrains.anko.alert
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CategoriesFragment : BaseFragment(R.layout.categories_fragment) {
    private val categoriesAdapter by lazy {
        CategoryAdapter(
            this::onCategoryClick,
            this::onDeleteClick
        )
    }
    private val categoriesViewModel: CategoriesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //arguments?.getInt("pddNumber")}.txt")
        categories_rv.adapter = categoriesAdapter
        categoriesViewModel.categories.observe(viewLifecycleOwner, Observer {
            categoriesAdapter.submitList(it)
        })
        back.setOnClickListener { Navigation.findNavController(view).popBackStack() }
        save.setOnClickListener {
            if (name.text.toString().isNullOrBlank()) {
                return@setOnClickListener
            }
            categoriesViewModel.saveCategory(name.text.toString())
        }
    }

    private fun onCategoryClick(category: Category) {

    }

    private fun onDeleteClick(name: String) {
        context?.alert("Вы действительно хотите удалить $name?") {
            positiveButton("Да") {
                categoriesViewModel.deleteCategory(name)
            }
            neutralPressed("Нет") {

            }
        }?.show()
    }
}