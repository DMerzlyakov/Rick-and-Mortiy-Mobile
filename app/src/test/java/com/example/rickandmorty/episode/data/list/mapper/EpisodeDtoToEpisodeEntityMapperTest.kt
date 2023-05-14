package com.example.rickandmorty.episode.data.list.mapper

import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import com.example.rickandmorty.episode.data.list.remote.model.EpisodeDto
import com.example.rickandmorty.episode.data.list.remote.model.EpisodeResultsDTO
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class EpisodeDtoToEpisodeEntityMapperTest {

    private val mockEpisodeDto = mockk<EpisodeDto>()
    private val mapper = EpisodeDtoToEpisodeEntityMapper()

    @Test
    fun `mapping of input should return list of expected output`() {

        every { mockEpisodeDto.results } returns listOf(
            EpisodeResultsDTO(
                1,
                "Pilot",
                "2013-12-02",
                "S01E01",
                listOf("https://rickandmortyapi.com/api/character/1", "https://rickandmortyapi.com/api/character/2"),
                "https://rickandmortyapi.com/api/epsiode/1",
              "2017-11-10T12:56:33.798Z"
            ),
            EpisodeResultsDTO(
                2,
                "Pilot",
                "2013-12-02",
                "S01E01",
                emptyList(),
                "https://rickandmortyapi.com/api/epsiode/1",
                "2017-11-10T12:56:33.798Z"
            )
        )

        val result = mapper(mockEpisodeDto)

        val expected = listOf(
            EpisodeEntity(
                1,
                "Pilot",
                "2013-12-02",
                "S01E01",
                listOf(1, 2)
            ),
            EpisodeEntity(
                2,
                "Pilot",
                "2013-12-02",
                "S01E01",
                emptyList()
            )
        )
        assertEquals(expected, result)
    }
}