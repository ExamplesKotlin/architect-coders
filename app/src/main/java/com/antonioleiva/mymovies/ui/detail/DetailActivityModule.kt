package com.antonioleiva.mymovies.ui.detail

import android.app.Activity
import com.antonioleiva.data.repository.MoviesRepository
import com.antonioleiva.usecases.FindMovieById
import com.antonioleiva.usecases.ToggleMovieFavorite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Named

/**
 * We need a couple of modules here.
 *
 * The first one will gets the movieId from the intent, and as it needs the activity
 * to recover it, the required component is the ActivityComponent.
 *
 * On the other hand, the rest of the dependencies can survive orientation changes and,
 * as such, we can use the ActivityRetainedComponent instead.
 */

@Module
@InstallIn(ActivityComponent::class)
class DetailActivityModule {

    @Provides
    @Named("movieId")
    fun getMovieId(activity: Activity) = activity.intent.getIntExtra(DetailActivity.MOVIE, -1)

}

@Module
@InstallIn(ActivityRetainedComponent::class)
class DetailActivityRetainedModule {

    @Provides
    fun findMovieByIdProvider(moviesRepository: MoviesRepository) = FindMovieById(moviesRepository)

    @Provides
    fun toggleMovieFavoriteProvider(moviesRepository: MoviesRepository) =
        ToggleMovieFavorite(moviesRepository)
}