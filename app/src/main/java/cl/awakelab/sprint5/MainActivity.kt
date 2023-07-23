package cl.awakelab.sprint5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import cl.awakelab.sprint5.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()

    }

    fun initAdapter() {
        val adapter = MainAdapter()
        adapter.setData(Producto.returnShoeList())
        val layoutManager = GridLayoutManager(this, 2)
        binding.mainRecyclerView.layoutManager = layoutManager
        binding.mainRecyclerView.adapter = adapter
    }



}


