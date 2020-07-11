package com.dev.mahmoud_ashraf.popular_actors_app.domain.usecases

import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.Actor
import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.ActorInfo
import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.PopularsActorsResponse
import com.dev.mahmoud_ashraf.popular_actors_app.domain.core.RxImmediateSchedulerRule
import com.dev.mahmoud_ashraf.popular_actors_app.domain.repositories.PopularActorsRepository
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FetchPopularActorsUnitTest {

    @Mock
    lateinit var popularActorsRepository: PopularActorsRepository

    // to test rx java with different schedulers
    @Rule
    @JvmField
    var rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @Test
    fun `fetchPopularActors() with pageNumber will return Popular Actors List in this page`() {

        // arrange
        val page = 1
        val actorsList = mutableListOf<Actor>()
        val actorInfoList = mutableListOf<ActorInfo>()

        actorInfoList.add(ActorInfo("image1.jpg", 11))
        actorsList.add(Actor(1, 100, "dev", "Mahmoud", "test.png", actorInfoList))



        Mockito.`when`(popularActorsRepository.requestPopularActors(anyInt()))
            .thenReturn(
                Single.just(
                    PopularsActorsResponse(
                        1,
                        actorsList,
                        100,
                        120
                    )
                )
            )

        // act
        val resultObserver = fetchPopularActors(page, popularActorsRepository).test()

        // assert
        val expected = mutableListOf<Actor>()
        val expectedActorInfoList = mutableListOf<ActorInfo>()
        expectedActorInfoList.add(ActorInfo("image1.jpg", 11))
        expected.add(Actor(1, 100, "dev", "Mahmoud", POSTER_BASE_URL+"test.png", actorInfoList))

        resultObserver.assertValue(expected)
        resultObserver.dispose()
    }

     @Test
      fun `fetchPopularActors() with non null page will call repo`() {
          // arrange
          val page = 1

          // act
          val testObserver = fetchPopularActors(page, popularActorsRepository).test()

          // assert
         verify(popularActorsRepository).requestPopularActors(page)

          testObserver.dispose()
      }

}