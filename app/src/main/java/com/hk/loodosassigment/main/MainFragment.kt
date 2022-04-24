package com.hk.loodosassigment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.hk.loodosassigment.MyApplication
import com.hk.loodosassigment.R
import com.hk.loodosassigment.adapter.CoinListAdapter
import com.hk.loodosassigment.databinding.FragmentMainBinding
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val coinsAdapter= CoinListAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MyApplication).appComponent.mainComponent().create().inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.coinRcv.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=coinsAdapter
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing=false
            viewModel.refresh()
        }
        observerViewModels()
        viewModel.loadCoins()
        startWorker()
    }

    private fun observerViewModels() {
        viewModel.items.observe(viewLifecycleOwner, Observer {coins->
            coins?.let {
                binding.coinRcv.visibility=View.VISIBLE
                coinsAdapter.updateCoins(it)
            }
        })
        viewModel.dataLoading.observe(viewLifecycleOwner, Observer {isLoading->
            isLoading?.let {
                binding.loadingView.visibility=if(it)View.VISIBLE else View.GONE
                if(it){
                    binding.listError.visibility=View.GONE
                    binding.coinRcv.visibility=View.GONE
                }
            }
        })
        viewModel.isDataLoadingError.observe(viewLifecycleOwner,{isDataLoadingError->
            isDataLoadingError?.let { binding.listError.visibility=if(it) View.VISIBLE else View.GONE }
        })
    }

    fun startWorker(){
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val request: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<CoinWorker>()
                .setConstraints(constraints)
                .addTag("reset-order")
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 30, TimeUnit.SECONDS)
                .build()
        WorkManager.getInstance(requireContext())
            .beginUniqueWork("WorkerTag", ExistingWorkPolicy.KEEP, request)
            .enqueue()
    }

}