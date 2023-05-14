package com.example.rickandmorty.character.presentation.list

import androidx.paging.PagingData
import com.example.rickandmorty.Utils.ViewModelHelper
import com.example.rickandmorty.character.domain.list.GetCharacterListByIdUseCase
import com.example.rickandmorty.character.domain.list.GetCharacterListUseCase
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import com.example.rickandmorty.character.domain.list.model.CharacterFilter
import com.example.rickandmorty.character.presentation.list.mapper.CharacterDomainToCharacterUiModelMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@FlowPreview
@ObsoleteCoroutinesApi
class CharacterListViewModelTest: ViewModelHelper(){

    private val characterDomainList = listOf(
        CharacterDomain(1, "Rick", "Alive", "Human", "Male", "unknown"),
        CharacterDomain(2, "Morty", "Alive", "Human", "Male", "unknown")
    )

    private val getCharacterListUseCase = mockk<GetCharacterListUseCase>()
    private val getCharacterListByIdUseCase = mockk<GetCharacterListByIdUseCase>()
    private val characterDomainToCharacterUiModelMapper = mockk<CharacterDomainToCharacterUiModelMapper>()
    private lateinit var viewModel: CharacterListViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = CharacterListViewModel(getCharacterListUseCase, getCharacterListByIdUseCase, characterDomainToCharacterUiModelMapper)
    }

    @Test
    fun `update character list when searchByFilter value changes, new request is made`() = runTest {

        val testPagingData = flowOf(PagingData.from(characterDomainList))

        coEvery { getCharacterListUseCase(any(), any(), any(), any()) } returns testPagingData

        viewModel.setSearchByFilter(CharacterFilter("initialValue"))
        delay(1000)

        viewModel.getFullListCharacter().take(1).collect{}

        val nInteraction = 5
        for (i in 1..nInteraction){
            viewModel.setSearchByFilter(CharacterFilter("updatedValue $i"))
            println("$ setSearchFilter number - $i")
            delay(1000)
        }

        coVerify(exactly = nInteraction + 1) { getCharacterListUseCase(any(), any(), any(), any()) }
    }
}