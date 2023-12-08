package bangkit.android.androidfundamentalexam12.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.android.androidfundamentalexam12.databinding.FragmentFollowBinding

class FollowFragment : Fragment() {

    private lateinit var binding: FragmentFollowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt(ARG_POSITION)?: 0
        val username = arguments?.getString(ARG_USERNAME)?: ""

        if (position == 1){
            val followerAdapter = FollowerAdapter()
            val followerViewModel = ViewModelProvider(this)[FollowerViewModel::class.java]
            binding.rvFollow.layoutManager = LinearLayoutManager(requireContext())
            binding.rvFollow.adapter = followerAdapter
            followerViewModel.follower.observe(viewLifecycleOwner){follower ->
                follower?.let {
                    followerAdapter.submitList(it)
                    binding.progBar.visibility = View.GONE
                }
            }
            followerViewModel.setListFollower(username)
        } else {
            val followingAdapter = FollowingAdapter()
            val followingViewModel = ViewModelProvider(this)[FollowingViewModel::class.java]
            binding.rvFollow.layoutManager = LinearLayoutManager(requireContext())
            binding.rvFollow.adapter = followingAdapter
            followingViewModel.following.observe(viewLifecycleOwner){following ->
                following?.let {
                    followingAdapter.submitList(it)
                    binding.progBar.visibility = View.GONE
                }
            }
            followingViewModel.setListFollowing(username)
        }
    }

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }
}