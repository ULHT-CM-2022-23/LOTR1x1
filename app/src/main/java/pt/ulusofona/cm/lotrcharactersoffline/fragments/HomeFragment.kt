package pt.ulusofona.cm.lotrcharactersoffline.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.lotrcharactersoffline.NavigationManager
import pt.ulusofona.cm.lotrcharactersoffline.R
import pt.ulusofona.cm.lotrcharactersoffline.data.LOTRRepository
import pt.ulusofona.cm.lotrcharactersoffline.databinding.FragmentHomeBinding
import pt.ulusofona.cm.lotrcharactersoffline.model.SpinnerItem

class HomeFragment : Fragment() {

  private lateinit var binding: FragmentHomeBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_home, container, false)
    binding = FragmentHomeBinding.bind(view)
    return binding.root
  }

  override fun onStart() {
    super.onStart()
    binding.getCharactersBtn.setOnClickListener {
      LOTRRepository.getInstance().getCharacters { result ->
        if(result.isSuccess) {
          val items = result.getOrDefault(mutableListOf()).map { "${it.name} - ${it.gender.name}" }
          NavigationManager.goToListFragment(parentFragmentManager, items)
        } else {
          Toast.makeText(requireContext(), result.exceptionOrNull()?.message, Toast.LENGTH_LONG).show()
        }
      }
    }
  }

}