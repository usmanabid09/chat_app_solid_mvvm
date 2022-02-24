package com.example.solidapp.presentation.coin_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.solidapp.R
import com.example.solidapp.common.Constants
import com.example.solidapp.databinding.CoinListFragmentBinding
import com.example.solidapp.domain.model.Coin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : Fragment() {

    private val coinListViewModel: CoinListViewModel by viewModels()
    private var viewBinding: CoinListFragmentBinding? = null
    private val coinsList: ArrayList<Coin> = arrayListOf()
    private lateinit var coinsListAdapter: CoinListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = CoinListFragmentBinding.inflate(inflater)
        return viewBinding?.root
    }

    override fun onDestroyView() {
        viewBinding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        subscribeObservables()
    }

    private fun initialize() {

        coinsListAdapter = CoinListAdapter(coinsList)
        coinsListAdapter.coinListItemClick = { coin ->
            findNavController().navigate(R.id.action_coinListFragment_to_coinDetailFragment, bundleOf(Constants.PARAM_COIN_ID to coin.id))
        }
        viewBinding?.recyclerViewCoinList?.adapter = coinsListAdapter

    }

    private fun subscribeObservables() {
        coinListViewModel.state.observe(viewLifecycleOwner) { state ->
            when {
                state.isLoading -> showLoadingState()
                state.error.isNotBlank() -> {
                    showLoadingState(false)
                    showErrorState(state.error)
                }
                else -> {
                    showLoadingState(false)
                    showCoinsList(state.coins)
                }
            }
        }
    }

    private fun showCoinsList(coins: List<Coin>) {
        coinsList.clear()
        coinsList.addAll(coins)
        coinsListAdapter.notifyItemRangeChanged(0, coins.lastIndex)
    }

    private fun showErrorState(error: String) {
        viewBinding?.textViewHello?.text = error
    }

    private fun showLoadingState(show: Boolean = true) {
        viewBinding?.textViewHello?.text = if (show) "Loading..." else ""
    }

}