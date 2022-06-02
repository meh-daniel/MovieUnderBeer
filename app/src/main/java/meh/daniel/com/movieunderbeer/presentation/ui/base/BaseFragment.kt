package meh.daniel.com.movieunderbeer.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import javax.inject.Inject

abstract class BaseFragment<B : ViewBinding> : MvpAppCompatFragment(){

    @Inject
    protected lateinit var router: Router

    private var _viewBinding: B ?= null
    protected val binding get() = checkNotNull(_viewBinding)

    protected  abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?) : B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = initBinding(inflater, container)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }

    abstract fun injectDependency()


}