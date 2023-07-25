package cl.awakelab.sprint5

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import cl.awakelab.sprint5.databinding.FragmentSecondBinding
import coil.load
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
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment(), ISecondFragmentViewPresenter {

    lateinit var binding: FragmentSecondBinding
    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

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
        mSharedPreferences = this.requireActivity().getSharedPreferences("cookie", Context.MODE_PRIVATE)

        binding = FragmentSecondBinding.inflate(LayoutInflater.from(activity))

        val newUrl: String = producto.urlImagen.replace("w=250", "w=600")

        binding.imageViewSelectedProduct.load(newUrl)
        binding.textViewSelectedProductName.text = producto.nombre
        binding.textViewSelectedProductPrice.text = "$ " + producto.precio.toString()

        binding.imageViewGoBack.setOnClickListener { v ->
            val fm: FragmentManager = requireActivity().supportFragmentManager
            fm.popBackStack()
        }

        binding.cardViewAddToCart.setOnClickListener{

            editor = mSharedPreferences.edit()
            var cartList = mSharedPreferences.getString("cookie", "")

            if (cartList.isNullOrBlank()) {
                var cartListCreation= mutableListOf<Producto>()
                cartListCreation.add(producto)
                var json: String = gson.toJson(cartListCreation)
                set("cookie", json);
            } else {
                var listaProductos = mutableListOf<Producto>()
                val type: Type = object : TypeToken<List<Producto?>?>() {}.type
                listaProductos = gson.fromJson(cartList, type)
                listaProductos.add(producto)
                var json: String = gson.toJson(listaProductos)
                set("cookie", json);
            }

        }

        binding.floatingActionButtonCartFromSecondFragment.setOnClickListener {
            Navigation.findNavController(binding.getRoot()).navigate(
                R.id.action_secondFragment_to_cartFragment
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

    override fun showCartMovement(movimiento: String, nombreProducto: String) {
        Toast.makeText(context, "Haz +  ${movimiento} en tu carro ${nombreProducto}", Toast.LENGTH_SHORT).show()
    }

    operator fun set(key: String?, value: String?) {
        editor.putString(key, value)
        editor.commit()
    }


}