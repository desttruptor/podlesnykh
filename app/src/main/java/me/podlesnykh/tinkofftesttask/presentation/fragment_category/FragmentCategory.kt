package me.podlesnykh.tinkofftesttask.presentation.fragment_category

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import me.podlesnykh.tinkofftesttask.databinding.FragmentPostBinding
import me.podlesnykh.tinkofftesttask.presentation.fragment_random.FragmentRandomViewModel
import me.podlesnykh.tinkofftesttask.presentation.models.Category
import me.podlesnykh.tinkofftesttask.presentation.models.Post


class FragmentCategory(private val category: Category) : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FragmentCategoryViewModel
    private lateinit var randomViewModel: FragmentRandomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        binding.fabBackward.setOnClickListener { onBackwardClick(category) }
        binding.fabForward.setOnClickListener { onForwardClick(category) }
        binding.btnRetryRequest.setOnClickListener { onRetryRequestClick(category) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showProgress(true)
        when (category) {
            Category.RANDOM -> {
                randomViewModel = ViewModelProvider(this).get(FragmentRandomViewModel::class.java)
                randomViewModel.mutableBackButtonState.observe(viewLifecycleOwner) {
                    binding.fabBackward.isEnabled = it == true
                }
                randomViewModel.mutableErrorDialog.observe(viewLifecycleOwner) {
                    showProgress(false)
                    showError(true)
                }
                randomViewModel.mutableCurrentPost.observe(viewLifecycleOwner) { post ->
                    showError(false)
                    showProgress(false)
                    displayPost(post)
                }
                randomViewModel.loadInitialPost()
            }
            else -> {
                val factory = FragmentCategoryViewModelFactory(category)
                viewModel =
                    ViewModelProvider(this, factory).get(FragmentCategoryViewModel::class.java)
                viewModel.mutableErrorDialog.observe(viewLifecycleOwner) {
                    showProgress(false)
                    showError(true)
                }
                viewModel.mutableCurrentPost.observe(viewLifecycleOwner) { post ->
                    showError(false)
                    showProgress(false)
                    displayPost(post)
                }
                viewModel.mutableCurrentUserPosition.observe(viewLifecycleOwner) {
                    binding.fabBackward.isEnabled = it != 0
                }
                viewModel.loadInitialPage()
            }
        }
    }

    private fun showError(show: Boolean) {
        if (show) {
            binding.postItem.root.visibility = View.GONE
            binding.fabBackward.visibility = View.GONE
            binding.fabForward.visibility = View.GONE
            binding.ivErrorImage.visibility = View.VISIBLE
            binding.tvErrorMessage.visibility = View.VISIBLE
            binding.btnRetryRequest.visibility = View.VISIBLE
        } else {
            binding.postItem.root.visibility = View.VISIBLE
            binding.fabBackward.visibility = View.VISIBLE
            binding.fabForward.visibility = View.VISIBLE
            binding.ivErrorImage.visibility = View.GONE
            binding.tvErrorMessage.visibility = View.GONE
            binding.btnRetryRequest.visibility = View.GONE
        }
    }

    private fun displayPost(post: Post) {
        imageLoadingProgressBar(true)
        showImageLoadingError(false)
        Glide.with(binding.postItem.postImage.context)
            .load(post.gifURL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    showImageLoadingError(true)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageLoadingProgressBar(false)
                    showImageLoadingError(false)
                    return false
                }
            })
            .into(binding.postItem.postImage)

        binding.postItem.postText.text = post.description
    }

    private fun imageLoadingProgressBar(show: Boolean) {
        binding.postItem.postProgressBar.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun showImageLoadingError(show: Boolean) {
        binding.postItem.errorLoadingImage.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun onRetryRequestClick(category: Category) {
        when (category) {
            Category.RANDOM -> randomViewModel.onClickRetryButton()
            else -> viewModel.onClickRetryButton()
        }
    }

    private fun onBackwardClick(category: Category) {
        when (category) {
            Category.RANDOM -> randomViewModel.onClickBntBackward()
            else -> viewModel.onClickBntBackward()
        }
    }

    private fun onForwardClick(category: Category) {
        when (category) {
            Category.RANDOM -> randomViewModel.onClickBtnForward()
            else -> viewModel.onClickBtnForward()
        }
    }

    private fun showProgress(show: Boolean) {
        if (show) {
            binding.postItem.postProgressBar.visibility = View.VISIBLE
            binding.postItem.gradient.visibility = View.GONE
        } else {
            binding.postItem.postProgressBar.visibility = View.GONE
            binding.postItem.gradient.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}