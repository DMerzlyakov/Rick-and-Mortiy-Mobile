package com.example.rickandmorty.universal_filter

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.rickandmorty.character.domain.list.model.CharacterFilter
import com.example.rickandmorty.databinding.FragmentFilterBinding
import com.example.rickandmorty.episode.domain.list.model.EpisodeFilter
import com.example.rickandmorty.location.domain.list.model.LocationFilter
import com.google.android.material.chip.Chip


class FilterFragment : DialogFragment() {

    private var filterType: TYPE? = null
    private var onFilterResultListenerCharacter: OnFilterResultListenerCharacter? = null
    private var onFilterResultListenerLocation: OnFilterResultListenerLocation? = null
    private var onFilterResultListenerEpisode: OnFilterResultListenerEpisode? = null

    private var _binding: FragmentFilterBinding? = null
    private val binding: FragmentFilterBinding
        get() = _binding ?: throw RuntimeException("FragmentFilterCharacterBinding is null")

    fun setOnFilterResultListenerCharacter(listener: OnFilterResultListenerCharacter) {
        onFilterResultListenerCharacter = listener
    }

    fun setOnFilterResultListenerLocation(listener: OnFilterResultListenerLocation) {
        onFilterResultListenerLocation = listener
    }

    fun setOnFilterResultListenerEpisode(listener: OnFilterResultListenerEpisode) {
        onFilterResultListenerEpisode = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            filterType =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.getSerializable(KEY_TYPE, TYPE::class.java)
                } else {
                    it.getSerializable(KEY_TYPE) as TYPE
                }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        setConfirmListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        funSetupTypeFilter()
    }

    private fun funSetupTypeFilter() = when (filterType) {
        TYPE.FROM_CHARACTER_LIST -> {
            binding.thirdSearchEditText.visibility = View.GONE
        }
        TYPE.FROM_LOCATION_LIST -> {
            binding.statusChooseChip.visibility = View.GONE
            binding.statusText.visibility = View.GONE
            binding.genderChooseChip.visibility = View.GONE
            binding.genderText.visibility = View.GONE

            binding.secondSearchEditText.hint = "Search by type"
            binding.thirdSearchEditText.hint = "Search by dimension"
        }
        TYPE.FROM_EPISODE_LIST -> {
            binding.statusChooseChip.visibility = View.GONE
            binding.statusText.visibility = View.GONE
            binding.genderChooseChip.visibility = View.GONE
            binding.genderText.visibility = View.GONE
            binding.secondSearchEditText.visibility = View.GONE
        }
        null -> throw RuntimeException("Choose type filter")

    }

    private fun setConfirmListener() = with(binding) {
        confirmBtn.setOnClickListener(this@FilterFragment::confirmBtnClick)
        cancelBtn.setOnClickListener(this@FilterFragment::cancelBtnClick)
    }

    private fun confirmBtnClick(view: View) {
        when (filterType) {
            TYPE.FROM_CHARACTER_LIST -> {
                onFilterResultListenerCharacter?.confirmFilter(
                    getFilterResultCharacter()
                )
            }
            TYPE.FROM_LOCATION_LIST -> {
                onFilterResultListenerLocation?.confirmFilter(
                    getFilterResultLocation()
                )
            }
            TYPE.FROM_EPISODE_LIST -> {
                onFilterResultListenerEpisode?.confirmFilter(
                    getFilterResultEpisode()
                )
            }
            null -> throw RuntimeException("Choose type filter")
        }
        dismiss()

    }

    private fun cancelBtnClick(view: View) {
        when (filterType) {
            TYPE.FROM_CHARACTER_LIST -> {
                onFilterResultListenerCharacter?.confirmFilter(
                    CharacterFilter("")
                )
            }
            TYPE.FROM_LOCATION_LIST -> {
                onFilterResultListenerLocation?.confirmFilter(
                    LocationFilter("")
                )
            }
            TYPE.FROM_EPISODE_LIST -> {
                onFilterResultListenerEpisode?.confirmFilter(
                    EpisodeFilter("")
                )
            }
            null -> throw RuntimeException("Choose type filter")
        }
        dismiss()

    }

    private fun getFilterResultLocation() = LocationFilter(
        binding.firstSearchEditText.editText?.text.toString(),
        binding.secondSearchEditText.editText?.text.toString(),
        binding.thirdSearchEditText.editText?.text.toString(),
    )


    private fun getFilterResultEpisode() = EpisodeFilter(
        binding.firstSearchEditText.editText?.text.toString(),
        binding.thirdSearchEditText.editText?.text.toString(),
    )


    private fun getFilterResultCharacter(): CharacterFilter {

        var selectedStatus = ""
        var selectedChipId = binding.statusChooseChip.checkedChipId
        if (selectedChipId != -1) {
            val selectedChipStatus: Chip = binding.statusChooseChip.findViewById(selectedChipId)
            selectedStatus = selectedChipStatus.text.toString()
        }

        var selectedGender = ""
        selectedChipId = binding.genderChooseChip.checkedChipId
        if (selectedChipId != -1) {
            val selectedChipGender: Chip = binding.genderChooseChip.findViewById(selectedChipId)
            selectedGender = selectedChipGender.text.toString()
        }

        return CharacterFilter(
            binding.firstSearchEditText.editText?.text.toString(),
            binding.secondSearchEditText.editText?.text.toString(),
            selectedStatus,
            selectedGender
        )
    }


    companion object {

        @JvmStatic
        fun newInstance(FILTER_PARAM: TYPE) = FilterFragment().apply {
            arguments = Bundle().apply { putSerializable(KEY_TYPE, FILTER_PARAM) }
        }

        private const val KEY_TYPE = "type"

        enum class TYPE {
            FROM_CHARACTER_LIST,
            FROM_LOCATION_LIST,
            FROM_EPISODE_LIST
        }
    }
}