package com.leonett.breakingbadcharacters.presentation.adapter

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.leonett.breakingbadcharacters.R

@EpoxyModelClass(layout = R.layout.item_loader)
abstract class LoaderModel : EpoxyModelWithHolder<LoaderHolder>() {

    override fun bind(holder: LoaderHolder) {
        // no-op
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return 2
    }
}

class LoaderHolder : EpoxyHolder() {

    override fun bindView(itemView: View) {
        // no-op
    }
}