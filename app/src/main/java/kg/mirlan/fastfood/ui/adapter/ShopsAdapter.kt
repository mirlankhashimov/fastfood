package kg.mirlan.fastfood.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kg.mirlan.fastfood.R
import kg.mirlan.fastfood.model.Category
import kg.mirlan.fastfood.model.Shop
import kg.mirlan.fastfood.utils.AppPreferences
import kg.mirlan.fastfood.utils.diffItemCallback
import kg.mirlan.fastfood.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_shop.view.*

class ShopsAdapter(private val onExperienceClick: (Shop) -> Unit) :
    ListAdapter<Shop, ShopsAdapter.ViewHolder>(
        diffItemCallback { it.first == it.second }
    ) {
    var currentShop = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_shop))
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

        fun bind(category: Shop) {
            if(currentShop == category.name){
                containerView.shop_image.visibility = View.VISIBLE
            }else{
                containerView.shop_image.visibility = View.GONE
            }
            containerView.name.text = category.name
        }
    }
}