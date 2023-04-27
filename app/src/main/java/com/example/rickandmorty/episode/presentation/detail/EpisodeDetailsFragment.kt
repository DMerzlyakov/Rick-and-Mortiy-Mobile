package com.example.rickandmorty.episode.presentation.detail

import androidx.fragment.app.Fragment

class EpisodeDetailsFragment : Fragment() {
//
//    private lateinit var onNavigationListener: OnNavigationListener
//    private var _binding: FragmentCharacterDetailsBinding? = null
//    private val binding: FragmentCharacterDetailsBinding
//        get() = _binding ?: throw RuntimeException("FragmentCharacterDetailsBinding is null")
//
//    private val mId by lazy {
//        requireArguments().getInt(ARG_PARAM_CHARACTER_ID)
//    }
//    private val viewModel: CharacterDetailViewModel by lazy {
//        ViewModelProvider(this, CharacterDetailsViewModelFactory(mId))[CharacterDetailViewModel::class.java]
//    }
//
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnNavigationListener) {
//            onNavigationListener = context
//        } else {
//            throw RuntimeException("Activity must be implements OnNavigationListener")
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
//
//        setupListeners()
//
//        return binding.root
//    }
//
//    private fun setupListeners(){
//        binding.btnBack.setOnClickListener {
//            onNavigationListener.toBackStack()
//        }
//    }
//    companion object {
//        fun newInstance(mId: Int): EpisodeDetailsFragment {
//            val fragment = EpisodeDetailsFragment()
//            val args = Bundle()
//            args.putInt(ARG_PARAM_CHARACTER_ID, mId)
//            fragment.arguments = args
//
//            return fragment
//        }
//
//        const val ARG_PARAM_CHARACTER_ID = "id"
//
//    }
}