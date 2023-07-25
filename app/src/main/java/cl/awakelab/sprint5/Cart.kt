package cl.awakelab.sprint5

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class Cart {

    var listaProductos = mutableListOf<Producto>()
    var precioTotal: Double = 0.0
    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var gson: Gson
    private lateinit var editor: SharedPreferences.Editor


    fun calculoPrecioTotal(): Double {

        for(producto in listaProductos) {
            precioTotal = precioTotal + producto.precio
        }
        return precioTotal
    }

    fun agregarProductoASharedPreferences(producto: Producto, contexto: Context) {

    }

    fun eliminarProductoDeSharedPreferences(producto: Producto, contexto: Context): MutableList<Producto> {

        listaProductos = extraerListaDeCarro(contexto)

        listaProductos.remove(producto)

        var json: String = convertirListaDeProductosEnJsonString(listaProductos)

        salvarEstadoDeLista("cookie", json);

    }

    fun extraerListaDeCarro(contexto: Context): MutableList<Producto> {
        mSharedPreferences = contexto.getSharedPreferences("cookie", Context.MODE_PRIVATE)
        gson = GsonBuilder().disableHtmlEscaping().create()

        var stringJson = mSharedPreferences.getString("cookie", "")

        val tipo = object : TypeToken<List<Producto?>?>() {}.type
        listaProductos = gson.fromJson(stringJson, tipo)

        return listaProductos
    }


    fun salvarEstadoDeLista(key: String?, value: String?) {
        editor = mSharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun convertirListaDeProductosEnJsonString(listaProductos: MutableList<Producto>): String {

        var json: String = gson.toJson(listaProductos)
        return json

    }


}