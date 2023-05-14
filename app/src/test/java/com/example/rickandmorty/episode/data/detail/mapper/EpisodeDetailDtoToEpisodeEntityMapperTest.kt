package com.example.rickandmorty.episode.data.detail.mapper

import com.example.rickandmorty.episode.data.detail.remote.model.EpisodeDetailDTO
import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class EpisodeDetailDtoToEpisodeEntityMapperTest {

    private val mockEpisodeDetailDTO = mockk<EpisodeDetailDTO>()
    private val mapper = EpisodeDetailDtoToEpisodeEntityMapper()

    @Test
    fun `mapping of input with characters should return expected output`() {
        every { mockEpisodeDetailDTO.id } returns 1
        every { mockEpisodeDetailDTO.name } returns "Pilot"
        every { mockEpisodeDetailDTO.air_date } returns "December 2, 2013"
        every { mockEpisodeDetailDTO.episode } returns "S01E01"
        every { mockEpisodeDetailDTO.characters } returns listOf("https://rickandmortyapi.com/api/character/1", "https://rickandmortyapi.com/api/character/2")

        val result = mapper(mockEpisodeDetailDTO)

        val expected = EpisodeEntity(
            1, "Pilot", "December 2, 2013", "S01E01", listOf(1, 2)
        )
        assertEquals(expected, result)
    }

    @Test
    fun `mapping of input with empty characters should return expected output`() {
        every { mockEpisodeDetailDTO.id } returns 1
        every { mockEpisodeDetailDTO.name } returns "Pilot"
        every { mockEpisodeDetailDTO.air_date } returns "December 2, 2013"
        every { mockEpisodeDetailDTO.episode } returns "S01E01"
        every { mockEpisodeDetailDTO.characters } returns emptyList()

        val result = mapper(mockEpisodeDetailDTO)

        val expected = EpisodeEntity(
            1, "Pilot", "December 2, 2013", "S01E01", emptyList()
        )
        assertEquals(expected, result)
    }
}