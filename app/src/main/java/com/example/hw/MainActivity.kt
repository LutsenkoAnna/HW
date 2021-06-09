package com.example.hw

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.hw.movie.MovieItem
import com.example.hw.details.DetailsFragment
import com.example.hw.movie.MovieListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), MovieListFragment.OnMovieClickListener {
    var isFavoritePanel : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openMovieList(Data.movies)

        //навигация между фрагментами
        val navView: BottomNavigationView = findViewById(R.id.navigationView)
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigationMovie -> {
                    openMovieList(Data.movies)
                    isFavoritePanel = false
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigationFavorite -> {
                    openMovieList(Data.favoriteMovies)
                    isFavoritePanel = true
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigationInviteFriend -> {
                    onInviteClick()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun openMovieDetailed(item: MovieItem) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, DetailsFragment.newInstance(item), DetailsFragment.TAG)
                .addToBackStack(null)
                .commit()
    }

    private fun openMovieList(items: MutableList<MovieItem>) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer,  MovieListFragment(items), MovieListFragment.TAG)
                .addToBackStack(null)
                .commit()
    }

    //пригласить друга
    private fun onInviteClick() {
        val share = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(
                    Intent.EXTRA_TEXT,
                    getString(R.string.inviteMessage) + "https://play.google.com/store/apps/details?id=$packageName"
            ) }, null)
        startActivity(share)
    }

    override fun onDetailsClick (item: MovieItem) {
        openMovieDetailed(item)
    }

    override fun onAddFavoriteClick(item: MovieItem) {
        val snackbarName: String = item.name
        val view : View = findViewById(R.id.recyclerView)

        val snackbarFavorite: String = if (item.isFavorite) {
            getString(R.string.snack_add)
        } else {
            getString(R.string.snack_delete)
        }
        Snackbar.make(view, "$snackbarName $snackbarFavorite", Snackbar.LENGTH_SHORT)
            .setAction(getString(R.string.snackbar_cancel)) {
                if (item.isFavorite) {
                    item.isFavorite = false
                    Data.favoriteMovies.remove(item)
                } else {
                    item.isFavorite = true
                    Data.favoriteMovies.add(item)
                }
                if (isFavoritePanel)
                    openMovieList(Data.favoriteMovies)
                else
                    openMovieList(Data.movies)
            }.show()
    }

    //Диалог на кнопку Back
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            val dialog: Dialog = ExitDialog(this,this)
            dialog.show()
        }
    }
}
