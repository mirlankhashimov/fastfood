package kg.mirlan.fastfood.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kg.mirlan.fastfood.R
import kg.mirlan.fastfood.model.Category
import kg.mirlan.fastfood.utils.diffItemCallback
import kg.mirlan.fastfood.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_horizontal_category.view.*

class CategorysAdapter(private val onExperienceClick: (Category) -> Unit) :
    ListAdapter<Category, CategorysAdapter.ViewHolder>(
        diffItemCallback { it.first == it.second }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_horizontal_category))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        init {
            containerView.setOnClickListener {
                onExperienceClick(getItem(adapterPosition))
            }
        }

        fun bind(category: Category) {
            containerView.name.text = category.name
        }
    }
}