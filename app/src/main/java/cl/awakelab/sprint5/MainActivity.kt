package cl.awakelab.sprint5

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import cl.awakelab.sprint5.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mSharedPreferences = getSharedPreferences("cookie", Context.MODE_PRIVATE)

        window.statusBarColor = ContextCompat.getColor(this, cl.awakelab.sprint5.R.color.dark_pink)

    }


}

