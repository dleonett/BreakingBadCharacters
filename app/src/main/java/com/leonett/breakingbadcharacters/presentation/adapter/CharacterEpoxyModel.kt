package com.leonett.breakingbadcharacters.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.leonett.breakingbadcharacters.R
import com.leonett.breakingbadcharacters.presentation.characters.model.CharacterUi
import com.leonett.breakingbadcharacters.presentation.common.setRoundCorners

@EpoxyModelClass(layout = R.layout.item_character)
abstract class CharacterEpoxyModel : EpoxyModelWithHolder<CharacterHolder>() {

    @EpoxyAttribute
    var character: CharacterUi? = null

    @EpoxyAttribute
    var onItemClickListener: ((View) -> Unit)? = null

    @EpoxyAttribute
    var onLikeClickListener: ((View) -> Unit)? = null

    override fun bind(holder: CharacterHolder) {
        character?.let {
            Glide.with(holder.imgAvatar.context)
                .load(it.avatarUrl)
                .apply(RequestOptions().placeholder(R.color.gray_light))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imgAvatar)

            holder.txtName.text = it.name
            holder.txtAlias.text = it.alias
            holder.btnLike.setImageResource(
                when {
                    it.isFavorite -> R.drawable.ic_like_on
                    else -> R.drawable.ic_like_off
                }
            )
            holder.btnLike.setOnClickListener(onLikeClickListener)
            holder.container.setOnClickListener(onItemClickListener)
            holder.imgAvatar.setRoundCorners(R.dimen.spacing_xxxs)
        }
    }
}

class CharacterHolder : EpoxyHolder() {

    lateinit var container: View
    lateinit var imgAvatar: ImageView
    lateinit var btnLike: ImageView
    lateinit var txtName: TextView
    lateinit var txtAlias: TextView

    override fun bindView(itemView: View) {
        container = itemView
        imgAvatar = itemView.findViewById(R.id.imgAvatar)
        btnLike = itemView.findViewById(R.id.btnLike)
        txtName = itemView.findViewById(R.id.txtName)
        txtAlias = itemView.findViewById(R.id.txtAlias)
    }
}