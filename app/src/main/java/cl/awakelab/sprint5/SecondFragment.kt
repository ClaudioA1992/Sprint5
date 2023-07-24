package cl.awakelab.sprint5

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.awakelab.sprint5.databinding.FragmentFirstBinding
import cl.awakelab.sprint5.databinding.FragmentSecondBinding
import coil.load
import com.google.gson.Gson
import com.google.gson.GsonBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    lateinit var binding: FragmentSecondBinding
    private lateinit var mSharedPreferences: SharedPreferences

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("product")
            param2 = it.getString(ARG_PARAM2)
        }
        println("Creation")
        println(param1)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val gson: Gson = GsonBuilder().disableHtmlEscaping().create()
        var producto: Producto = gson.fromJson(param1, Producto::class.java)

        binding = FragmentSecondBinding.inflate(LayoutInflater.from(activity))

        val newUrl: String = producto.urlImagen.replace("250", "600")

        mSharedPreferences = this.requireActivity().getSharedPreferences("cookie", Context.MODE_PRIVATE)

        binding.imageViewSelectedProduct.load(newUrl)
        binding.textViewSelectedProductName.text = producto.nombre
        binding.textViewSelectedProductPrice.text = "$ " + producto.precio.toString()

        binding.cardViewAddToCart.setOnClickListener{



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
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}