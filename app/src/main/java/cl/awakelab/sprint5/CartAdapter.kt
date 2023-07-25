package cl.awakelab.sprint5

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.awakelab.sprint5.databinding.ItemCartBinding
import coil.load
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class CartAdapter(var context: Context): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private lateinit var mSharedPreferences: SharedPreferences
    lateinit var gson: Gson
    var listaProductos = mutableListOf<Producto>()

    class ViewHolder(val binding: ItemCartBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Producto) {
            binding.textViewProductNameCart.text = item.nombre
            binding.textViewProductPriceCart.text = item.precio.toString() + " $"
            binding.imageViewProductImageCart.load(item.urlImagen)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        Log.d("MyAdapter", "OnCreate")
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        var item = listaProductos[position]
        val binding: ItemCartBinding = ItemCartBinding.bind(holder.itemView)
        holder.bind(item)
        binding.imageViewTrash.setOnClickListener {
            listaProductos.remove(item)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return this.listaProductos.size
    }

    fun setData() {
        mSharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE)
        gson = GsonBuilder().disableHtmlEscaping().create()

        var stringJson = mSharedPreferences.getString("cookie", "")
        val tipo = object : TypeToken<List<Producto?>?>() {}.type

        listaProductos = gson.fromJson(stringJson, tipo)

    }

}

