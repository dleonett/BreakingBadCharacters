package com.leonett.breakingbadcharacters.presentation.characters.detail

import androidx.hilt.Assisted
import androidx.lifecycle.*
import com.leonett.breakingbadcharacters.domain.usecase.FavoriteCharacterUseCase
import com.leonett.breakingbadcharacters.domain.usecase.ObserveCharacterUseCase
import com.leonett.breakingbadcharacters.presentation.characters.model.CharacterUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val observeCharacterUseCase: ObserveCharacterUseCase,
    private val favoriteCharacterUseCase: FavoriteCharacterUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _screenStateLiveData = MutableLiveData<CharacterDetailScreenState>()
    val screenStateLiveData get() = _screenStateLiveData as LiveData<CharacterDetailScreenState>

    init {
        observeCharacter()
    }

    private fun observeCharacter() {
        Timber.d("observeCharacter")

        savedStateHandle.get<CharacterUi>("character")?.let { character ->
            viewModelScope.launch {
                observeCharacterUseCase.invoke(character.id)
                    .distinctUntilChanged()
                    .collect {
                        _screenStateLiveData.postValue(
                            CharacterDetailScreenState.Success(
                                CharacterUi(
                                    id = it.charId,
                                    avatarUrl = it.img,
                                    name = it.name,
                                    alias = it.nickname,
                                    realName = it.portrayed,
                                    status = it.status,
                                    occupation = it.occupation,
                                    isFavorite = it.isFavorite
                                )
                            )
                        )
                    }
            }
        }
    }

    fun updateCharacterFavorite(characterUi: CharacterUi) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteCharacterUseCase.invoke(characterUi.id, !characterUi.isFavorite)
        }
    }

}
