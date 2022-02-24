package com.example.solidapp.presentation.coin_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.solidapp.databinding.FragmentCoinDetailBinding
import com.example.solidapp.domain.model.CoinDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private val viewModel: CoinDetailViewModel by viewModels()
    private var viewBinding: FragmentCoinDetailBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = FragmentCoinDetailBinding.inflate(inflater)
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
    }

    private fun subscribeObservables() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when {
                state.isLoading -> showLoadingState()
                state.error.isNotBlank() -> {
                    showLoadingState(false)
                    showErrorState(state.error)
                }
                else -> showCoinDetail(state.coinDetail)
            }
        }
    }

    private fun showCoinDetail(coinDetail: CoinDetail?) {
        coinDetail?.let { coin ->
            viewBinding?.textViewCoinName?.text = String.format("%s\n\n%s", coin.name, coin.description)
        }
    }

    private fun showLoadingState(show: Boolean = true) {
        viewBinding?.textViewCoinName?.text = if (show) "Loading..." else ""
    }

    private fun showErrorState(error: String) {
        viewBinding?.textViewCoinName?.text = error
    }

}