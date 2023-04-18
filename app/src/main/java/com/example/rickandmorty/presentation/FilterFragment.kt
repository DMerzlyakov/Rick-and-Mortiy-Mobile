package com.example.rickandmorty.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.rickandmorty.databinding.FragmentFilterCharacterBinding
import com.example.rickandmorty.domain.CharactersFilter
import com.google.android.material.chip.Chip

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class FilterFragment : DialogFragment() {

    private var nameFromFragment: String? = null
    private var fromFragment: String? = null

    private lateinit var onFilterResultListener: OnFilterResultListener

    private var _binding: FragmentFilterCharacterBinding? = null
    private val binding: FragmentFilterCharacterBinding
        get() = _binding ?: throw RuntimeException("FragmentFilterCharacterBinding is null")

    fun setOnFilterResultListener(listener: OnFilterResultListener){
        onFilterResultListener = listener
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nameFromFragment = it.getString(NAME_PARAM)
            fromFragment = it.getString(FROM_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterCharacterBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        setConfirmListener()
        return binding.root
    }

    private fun setConfirmListener() {
        with(binding) {
            confirmBtn.setOnClickListener {

                onFilterResultListener.confirmFilter(
                    getFilterResultCharacter()
                )

                dismiss()
            }

            cancelBtn.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun getFilterResultCharacter(): CharactersFilter {

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


        return CharactersFilter(
            binding.firstSearchEditText.editText?.text.toString(),
            binding.secondSearchEditText.editText?.text.toString(),
            selectedStatus,
            selectedGender
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (fromFragment) {
            FROM_CHARACTER -> {
                with(binding) {
                    thirdSearchEditText.visibility = View.GONE
                }

            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(from: String, name: String = "") =
            FilterFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME_PARAM, name)
                    putString(FROM_PARAM, from)
                }
            }

        private const val NAME_PARAM = "name"
        private const val FROM_PARAM = "from"

        const val FROM_CHARACTER = "characterFrom"
    }
}