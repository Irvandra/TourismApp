package com.dicoding.tourismapp.domain

import com.dicoding.tourismapp.DataDummy
import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.core.domain.repository.ITourismRepository
import com.dicoding.tourismapp.core.domain.usecase.TourismInteractor
import com.dicoding.tourismapp.core.domain.usecase.TourismUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TourismUseCaseTest {

    private lateinit var tourismUseCase: TourismUseCase

    @Mock
    private lateinit var tourismRepository: ITourismRepository

    private val dummyTourism = DataDummy.generateDummyTourism()

    @Before
    fun setUp() {
        tourismUseCase = TourismInteractor(tourismRepository)
        `when`(tourismRepository.getAllTourism()).thenReturn(flowOf(Resource.Success(dummyTourism)))
    }

    @Test
    fun `should get data from repository`() = runTest {
        val result = tourismUseCase.getAllTourism().first()

        assert(result is Resource.Success)
        val tourismData = (result as Resource.Success).data

        verify(tourismRepository).getAllTourism()
        verifyNoMoreInteractions(tourismRepository)
        assertEquals(dummyTourism.first(), tourismData?.first())
    }
}