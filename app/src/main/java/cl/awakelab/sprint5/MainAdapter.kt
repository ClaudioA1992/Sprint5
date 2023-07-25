package cl.awakelab.sprint5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import cl.awakelab.sprint5.databinding.ItemFirstScreenBinding
import coil.load
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var productos = mutableListOf<Producto>()

    class ViewHolder(val binding: ItemFirstScreenBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Producto) {
            binding.textViewNombreProducto.text = item.nombre
            binding.textViewPrecioProducto.text = item.precio.toString() + " $"
            binding.imageViewProducto.load(item.urlImagen)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ItemFirstScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {

        var item = productos[position]

        val binding: ItemFirstScreenBinding = ItemFirstScreenBinding.bind(holder.itemView)
        var bundle: Bundle = Bundle()
        val gson: Gson = GsonBuilder().disableHtmlEscaping().create()
        val productJsonString: String = gson.toJson(item)

        bundle.putString("product", productJsonString)

        holder.itemView.setOnClickListener { v: View? ->
            findNavController(binding.getRoot()).navigate(
                R.id.action_firstFragment_to_secondFragment,
                bundle
            )
        }

        holder.bind(item)

    }

    fun setData(returnShoeList: MutableList<Producto>) {
        this.productos = returnShoeList
    }


}

