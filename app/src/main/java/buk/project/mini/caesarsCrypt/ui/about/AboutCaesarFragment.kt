package buk.project.mini.caesarsCrypt.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import buk.project.mini.caesarsCrypt.R
import buk.project.mini.caesarsCrypt.ui.MainActivity2

class AboutCaesarFragment : Fragment() {

    private lateinit var aboutCaesarViewModel: AboutCaesarViewModel
    private lateinit var mainActivity: MainActivity2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainActivity = this.activity as MainActivity2
        aboutCaesarViewModel = ViewModelProvider(this).get(AboutCaesarViewModel::class.java)
        val root: View = inflater.inflate(R.layout.fragment_about_caesar, container, false)
        // val textView: TextView = root.findViewById(R.id.text_dashboard)

        aboutCaesarViewModel.text.observe(
            viewLifecycleOwner,
            {
                // textView.text = it
                mainActivity.supportActionBar?.title = it
            }
        )
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        fun newInstance(): AboutCaesarFragment = AboutCaesarFragment()
    }
}
