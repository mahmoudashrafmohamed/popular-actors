package com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.mahmoud_ashraf.popular_actors_app.R
import com.dev.mahmoud_ashraf.popular_actors_app.databinding.HomeFragmentBinding
import com.dev.mahmoud_ashraf.popular_actors_app.presentation.core.EndlessRecyclerViewScrollListener
import com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.details.DetailsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Fragment() {

    private val homeViewModel by viewModel<HomeViewModel>()
    private val adapter by lazy { PopularActorsAdapter() }

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpVenuesRecycler()

        homeViewModel.popularActorsLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun setUpVenuesRecycler() {

        binding.popularActorsRecycler.adapter = adapter
        binding.popularActorsRecycler.itemAnimator = null
        adapter.onItemClicked = { _, actor ->
            /*
             val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
             */
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
        }

      binding.popularActorsRecycler.addOnScrollListener(object : EndlessRecyclerViewScrollListener(
        binding.popularActorsRecycler.layoutManager as LinearLayoutManager
      ){
          override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
              Timber.i("load more... ")
              homeViewModel.fetchPopularActors()
          }

      })
    }



    companion object {
        fun newInstance() = HomeFragment()
    }


}