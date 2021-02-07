package com.leonett.breakingbadcharacters.presentation.characters.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.leonett.breakingbadcharacters.R
import com.leonett.breakingbadcharacters.databinding.FragmentCharacterDetailBinding
import com.leonett.breakingbadcharacters.presentation.base.BaseFragment
import com.leonett.breakingbadcharacters.presentation.characters.model.CharacterUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding>() {

    private val characterDetailViewModel by viewModels<CharacterDetailViewModel>()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCharacterDetailBinding {
        return FragmentCharacterDetailBinding.inflate(inflater, container, false)
    }

    override fun initVars() {

    }

    override fun initViews(view: View) {
        with(binding) {
            btnBack.setOnClickListener { requireActivity().onBackPressed() }
        }
    }

    override fun observeViewModels() {
        characterDetailViewModel.screenStateLiveData.observe(
            viewLifecycleOwner, { handleScreenState(it) })
    }

    private fun handleScreenState(state: CharacterDetailScreenState?) {
        state?.let {
            when (it) {
                is CharacterDetailScreenState.Success -> populateCharacterInfo(it.character)
            }
        }
    }

    private fun populateCharacterInfo(character: CharacterUi) = with(binding) {
        Glide.with(this@CharacterDetailFragment)
            .load(character.avatarUrl)
            .apply(RequestOptions().placeholder(R.color.black))
            .transition(withCrossFade())
            .into(imgBackground)

        topBarTitle.text = character.name
        txtAlias.text = character.alias
        txtOccupation.text = character.occupation?.joinToString() ?: "N/A"
        txtStatus.text = character.status
        txtPortrayed.text = character.realName
        btnLike.setImageResource(
            when {
                character.isFavorite -> R.drawable.ic_like_on
                else -> R.drawable.ic_like_off_white
            }
        )
        btnLike.setOnClickListener { characterDetailViewModel.updateCharacterFavorite(character) }
    }

}
