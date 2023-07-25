package cl.awakelab.sprint5

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.awakelab.sprint5.databinding.ItemCartBinding
import coil.load

class CartAdapter: RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    var productos = mutableListOf<Producto>()

    class ViewHolder(val binding: ItemCartBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Producto) {
            binding.textViewNombreProducto.text = item.nombre
            binding.textViewPrecioProducto.text = item.precio.toString() + " $"
            binding.imageViewProducto.load(item.urlImagen)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}