package com.leonett.breakingbadcharacters.presentation.characters.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonett.breakingbadcharacters.domain.usecase.FavoriteCharacterUseCase
import com.leonett.breakingbadcharacters.domain.usecase.FetchCharactersUseCase
import com.leonett.breakingbadcharacters.domain.usecase.ObserveCharactersUseCase
import com.leonett.breakingbadcharacters.presentation.characters.model.CharacterUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val observeCharactersUseCase: ObserveCharactersUseCase,
    private val favoriteCharacterUseCase: FavoriteCharacterUseCase
) : ViewModel() {

    private val _screenStateLiveData = MutableLiveData<CharactersScreenState>()
    val screenStateLiveData get() = _screenStateLiveData as LiveData<CharactersScreenState>

    init {
        _screenStateLiveData.value = CharactersScreenState.Loading()

        observeCharacters()
        fetchCharacters()
    }

    private fun observeCharacters() {
        Timber.d("observeCharacters")

        viewModelScope.launch {
            observeCharactersUseCase.invoke()
                .distinctUntilChanged()
                .collect { list ->
                    _screenStateLiveData.postValue(CharactersScreenState.Success(list.map {
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
                    }))
                }
        }
    }

    private fun fetchCharacters() {
        Timber.d("fetchCharacters")

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    fetchCharactersUseCase.invoke()
                } catch (error: Throwable) {
                    Timber.e(error.message ?: "Error")
                    _screenStateLiveData.postValue(CharactersScreenState.Error(errorMessage = "Error"))
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
