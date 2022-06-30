package com.example.feature_products_impl.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core_utils.R
import com.example.feature_products_impl.databinding.ProductListItemBinding
import com.example.feature_products_impl.presentation.view_objects.ProductInListVO

class ProductListAdapter(
    private val onItemClicked: (String) -> Unit
) :
    ListAdapter<ProductInListVO, ProductListAdapter.ViewHolder>(DiffUtilCallback()) {

    inner class ViewHolder(private val binding: ProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductInListVO) {
            with(binding) {
                Glide.with(root)
                    .load(product.image)
                    .placeholder(R.drawable.ic_download)
                    .error(R.drawable.ic_error)
                    .into(productImage)
                productNameText.text = product.name
                productPriceText.text = product.price
                productRating.rating = product.rating.toFloat()
                productViewsCountText.text =
                    root.context.getString(R.string.text_views, product.views)
                root.setOnClickListener {
                    onItemClicked(product.guid)
                }
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<ProductInListVO>() {
        override fun areItemsTheSame(oldItem: ProductInListVO, newItem: ProductInListVO): Boolean {
            return oldItem.guid == newItem.guid
        }

        override fun areContentsTheSame(
            oldItem: ProductInListVO,
            newItem: ProductInListVO
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ProductListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}