package com.example.rickandmorty.character.presentation.detail

import androidx.lifecycle.Observer
import com.example.rickandmorty.Utils.ViewModelHelper
import com.example.rickandmorty.character.domain.detail.GetCharacterDetailUseCase
import com.example.rickandmorty.character.presentation.detail.mapper.CharacterDetailDomainToCharacterDetailUiMapper
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import java.time.Duration

class CharacterDetailViewModelTest : ViewModelHelper(){
    private val getCharacterDetailUseCase = mockk<GetCharacterDetailUseCase>()
    private val mapper = mockk<CharacterDetailDomainToCharacterDetailUiMapper>()
    private lateinit var viewModel: CharacterDetailViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = CharacterDetailViewModel(getCharacterDetailUseCase, mapper)
    }

    @Test
    fun `update error value when error receive`() {
        val errorMessage = "Error message"
        every { getCharacterDetailUseCase.invoke(any()) } returns Single.error(Throwable(errorMessage))
        val errorObserver = mockk<Observer<String>>()

        viewModel.errorLiveData.observeForever(errorObserver)

        viewModel.getCharacter(1)

        Assertions.assertTimeout(Duration.ofSeconds(1)) {
            Assert.assertEquals(errorMessage, viewModel.errorLiveData.value)
        }
    }
}