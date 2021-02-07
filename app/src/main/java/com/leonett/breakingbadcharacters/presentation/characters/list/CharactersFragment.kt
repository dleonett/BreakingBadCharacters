package com.leonett.breakingbadcharacters.presentation.characters.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonett.breakingbadcharacters.databinding.FragmentCharactersBinding
import com.leonett.breakingbadcharacters.presentation.base.BaseFragment
import com.leonett.breakingbadcharacters.presentation.characters.model.CharacterUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding>(),
    CharactersEpoxyController.OnInteractionListener {

    private val charactersViewModel by viewModels<CharactersViewModel>()

    private val charactersEpoxyController = CharactersEpoxyController(this)

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCharactersBinding {
        return FragmentCharactersBinding.inflate(inflater, container, false)
    }

    override fun initVars() {

    }

    override fun initViews(view: View) {
        setupCharactersList()
    }

    private fun setupCharactersList() = with(binding) {
        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            setController(charactersEpoxyController)
        }
    }

    override fun observeViewModels() {
        charactersViewModel.screenStateLiveData.observe(
            viewLifecycleOwner, { handleScreenState(it) })
    }

    private fun handleScreenState(state: CharactersScreenState?) {
        state?.let {
            when (it) {
                is CharactersScreenState.Loading -> {
                    charactersEpoxyController.setData(it.characters, true)
                }
                is CharactersScreenState.Success -> {
                    charactersEpoxyController.setData(it.characters, false)
                }
                is CharactersScreenState.Error -> {
                    charactersEpoxyController.setData(it.characters, false)
                    showSnackbar(binding.root, it.errorMessage)
                }
            }
        }
    }

    override fun onCharacterItemClick(characterUi: CharacterUi) {
        findNavController().navigate(CharactersFragmentDirections.actionDetail(characterUi))
    }

    override fun onCharacterLikeClick(characterUi: CharacterUi) {
        charactersViewModel.updateCharacterFavorite(characterUi)
    }

}
