package cl.awakelab.sprint5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.awakelab.sprint5.databinding.ItemFirstScreenBinding
import coil.load

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
        holder.bind(item)
    }

    fun setData(returnShoeList: MutableList<Producto>) {
        this.productos = returnShoeList
    }


}

