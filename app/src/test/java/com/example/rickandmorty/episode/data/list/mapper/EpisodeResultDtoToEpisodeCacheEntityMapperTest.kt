package com.example.rickandmorty.episode.data.list.mapper

import com.example.rickandmorty.episode.data.list.local.model.EpisodeForDetailCacheEntity
import com.example.rickandmorty.episode.data.list.remote.model.EpisodeResultsDTO
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class EpisodeResultDtoToEpisodeCacheEntityMapperTest {

    private val mapper = EpisodeResultDtoToEpisodeCacheEntityMapper()
    private val mockEpisodeResultsDTO1 = mockk<EpisodeResultsDTO>()
    private val mockEpisodeResultsDTO2 = mockk<EpisodeResultsDTO>()

    @Test
    fun `mapping of input should return list of expected output`() {

        every { mockEpisodeResultsDTO1.id } returns 1
        every { mockEpisodeResultsDTO1.name } returns "Pilot"
        every { mockEpisodeResultsDTO1.air_date } returns "December 2, 2013"
        every { mockEpisodeResultsDTO1.episode } returns "S01E01"

        every { mockEpisodeResultsDTO2.id } returns 2
        every { mockEpisodeResultsDTO2.name } returns "Lawnmower Dog"
        every { mockEpisodeResultsDTO2.air_date } returns "December 9, 2013"
        every { mockEpisodeResultsDTO2.episode } returns "S01E02"

        val episodeResultsDTOList = listOf(mockEpisodeResultsDTO1, mockEpisodeResultsDTO2)

        val expectedEpisodeForDetailCacheEntityList = listOf(
            EpisodeForDetailCacheEntity(1, "Pilot", "December 2, 2013", "S01E01"),
            EpisodeForDetailCacheEntity(2, "Lawnmower Dog", "December 9, 2013", "S01E02")
        )

        val result = mapper(episodeResultsDTOList)

        assertEquals(expectedEpisodeForDetailCacheEntityList, result)
    }
}