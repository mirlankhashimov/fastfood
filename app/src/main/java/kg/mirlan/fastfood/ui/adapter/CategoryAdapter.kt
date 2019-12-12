package kg.mirlan.fastfood.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kg.mirlan.fastfood.R
import kg.mirlan.fastfood.model.Category
import kg.mirlan.fastfood.utils.diffItemCallback
import kg.mirlan.fastfood.utils.inflate
import kotlinx.android.synthetic.main.item_vertical_categories.view.*

class CategoryAdapter(
    private val onCategoryClick: (Category) -> Unit,
    private val onDeleteClick: (String) -> Unit
) :
    ListAdapter<Category, CategoryAdapter.ViewHolder>(
        diffItemCallback { it.first == it.second }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_vertical_categories))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val name: TextView = itemView.findViewById(R.id.category_name)
        private val delete: ImageView = itemView.findViewById(R.id.delete)
        fun bind(category: Category) {
            name.text = category.name
            delete.setOnClickListener {
                onDeleteClick(category.name ?: "")
            }
            itemView.setOnClickListener {
                onCategoryClick(getItem(adapterPosition))
            }
        }
    }
}