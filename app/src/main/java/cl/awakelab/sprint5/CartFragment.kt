package cl.awakelab.sprint5

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cl.awakelab.sprint5.databinding.FragmentCartBinding
import cl.awakelab.sprint5.databinding.FragmentFirstBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding
    private lateinit var mSharedPreferences: SharedPreferences
    private var valorTotal = 0.0
    private var cartModel = Cart()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCartBinding.inflate(LayoutInflater.from(activity))
        initAdapter()
        binding.textViewCartTotal.text = "Total: " + valorTotal.toString() + " $"
        binding.imageViewGoBackCart.setOnClickListener { v ->
            val fm: FragmentManager = requireActivity().supportFragmentManager
            fm.popBackStack()
        }
        binding.imageViewEmptyCart.setOnClickListener {
            mSharedPreferences.edit().clear().apply()
            print(mSharedPreferences.getString("cookie", ""))
        }
        binding.imageViewHome.setOnClickListener {
            Navigation.findNavController(binding.getRoot()).navigate(
                R.id.action_cartFragment_to_firstFragment
            )
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }

    fun initAdapter() {
        mSharedPreferences = requireContext().getSharedPreferences("cookie", Context.MODE_PRIVATE)
        val adapter = CartAdapter(requireContext())
        adapter.setData()
        val layoutManager = LinearLayoutManager(this.context)
        binding.recyclerViewCart.setLayoutManager(layoutManager);
        binding.recyclerViewCart.adapter = adapter
        valorTotal = cartModel.calculoPrecioTotal(adapter.listaProductos)
    }

}

