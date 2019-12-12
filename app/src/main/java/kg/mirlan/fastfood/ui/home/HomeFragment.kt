package kg.mirlan.fastfood.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kg.mirlan.fastfood.R
import kg.mirlan.fastfood.core.BaseFragment
import kg.mirlan.fastfood.custom.StartSnapHelper
import kg.mirlan.fastfood.model.Category
import kg.mirlan.fastfood.ui.adapter.CategorysAdapter
import kg.mirlan.fastfood.utils.AppPreferences
import kotlinx.android.synthetic.main.home_fragment.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : BaseFragment(R.layout.home_fragment), DatePickerDialog.OnDateSetListener {
    private val homeViewModel: HomeViewModel by viewModel()
    private val appPreferences: AppPreferences by inject()
    private var isStartDateDialog = true
    val now = Calendar.getInstance()
    private val categoryAdapter by lazy { CategorysAdapter(this::onCategoryClick) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        categories.adapter = categoryAdapter
//        val snapHelper = StartSnapHelper()
//        snapHelper.attachToRecyclerView(categories)
//        val divider = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL).apply {
//            setDrawable(ContextCompat.getDrawable(context!!, R.drawable.divider_16dp)!!)
//        }
//        categories.addItemDecoration(divider)
        setClicks()
        setData()
    }

    private fun setClicks() {
        edit_profile.setOnClickListener {

        }
        current_shop.setOnClickListener {
            view?.let { it1 ->
                Navigation.findNavController(it1)
                    .navigate(R.id.action_home_screen_to_bottom_sheet_dialog_screen)
            }
        }
        add_category.setOnClickListener {
            if(appPreferences.shopNameKey.isNullOrBlank()){
                context?.toast("Выберите заведение или добавьте")
                return@setOnClickListener
            }
            view?.let { it1 ->
                Navigation.findNavController(it1)
                    .navigate(R.id.action_home_screen_to_categories_screen)
            }
        }
        filter_date_start.setOnClickListener {
            isStartDateDialog = true
            now.time = homeViewModel.startDate.value ?: now.time
            val dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )
            fragmentManager?.let { it1 -> dpd.show(it1, "Calendar") }
        }
        filter_date_end.setOnClickListener {
            isStartDateDialog = false
            now.time = homeViewModel.endDate.value ?: now.time
            val dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )
            dpd.maxDate = Calendar.getInstance()
            fragmentManager?.let { it1 -> dpd.show(it1, "Calendar") }
        }
        today.setOnClickListener {
            homeViewModel.setStartDate(Calendar.getInstance().time)
            homeViewModel.setEndDate(Calendar.getInstance().time)
        }
        week.setOnClickListener {
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE, -7)
            homeViewModel.setStartDate(cal.time)
            homeViewModel.setEndDate(Calendar.getInstance().time)
        }
        month.setOnClickListener {
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE, -30)
            homeViewModel.setStartDate(cal.time)
            homeViewModel.setEndDate(Calendar.getInstance().time)
        }
    }
    private fun onCategoryClick(category: Category) {

    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        now.set(year, monthOfYear, dayOfMonth)
        if (isStartDateDialog)
            homeViewModel.setStartDate(now.time)
        else
            homeViewModel.setEndDate(now.time)
    }

    private fun setData() {
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.US)
//        homeViewModel.categories?.observe(viewLifecycleOwner, Observer {
//            //categoryAdapter.submitList(it)
//        })
        appPreferences.shopNameKeyLive.observe(viewLifecycleOwner, Observer {
            if(it.isNullOrBlank())return@Observer
            current_shop.text = it
        })
        homeViewModel.startDate.observe(viewLifecycleOwner, Observer {
            filter_date_start.setText(simpleDateFormat.format(it))
        })
        homeViewModel.endDate.observe(viewLifecycleOwner, Observer {
            filter_date_end.setText(simpleDateFormat.format(it))
        })
    }

}