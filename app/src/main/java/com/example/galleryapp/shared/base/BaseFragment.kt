package com.example.galleryapp.shared.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
abstract class BaseFragment<VB: ViewBinding, VM: ViewModel>(@LayoutRes protected val contentLayoutId: Int)
    : Fragment() {

    protected lateinit var binding: VB
    protected abstract fun getViewBinding(): VB


    protected lateinit var viewModel: VM
    protected abstract fun getViewModelClass(): Class<VM>

    init {
        binding = getViewBinding()
        viewModel = ViewModelProvider(this)[getViewModelClass()]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeView()
    }

    abstract fun setUpViews()
    abstract fun observeView()


}

//typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
//abstract class BaseFragment<VB: ViewBinding>(private val inflate: Inflate<VB>): Fragment() {
//
//    private var _binding: VB? = null
//    val binding get() = _binding ?: throw RuntimeException()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        try {
//            onInit()
//            onBindView()
//            bindViewModel()
//        } catch (e: Exception) {
//            Log.e("OnViewCreated", "Exception by view binding: ${e.message}")
//        }
//    }
//
//    open fun onInit() { }
//    open fun onBindView() {}
//    open fun bindViewModel() {}
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}