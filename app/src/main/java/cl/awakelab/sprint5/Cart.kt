package cl.awakelab.sprint5

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Cart {

    var listaProductos = mutableListOf<Producto>()
    var precioTotal: Double = 0.0
    private lateinit var mSharedPreferences: SharedPreferences
    private var gson = GsonBuilder().disableHtmlEscaping().create()
    private lateinit var editor: SharedPreferences.Editor


    fun calculoPrecioTotal(listaProductos: MutableList<Producto>): Double {

        for(producto in listaProductos) {
            precioTotal = precioTotal + producto.precio
        }
        return precioTotal
    }

    fun agregarProductoASharedPreferences(producto: Producto, contexto: Context, cartList: String) {
        if (cartList.isNullOrBlank()) {
            almacenarPrimeraVez(producto, contexto)
        } else {
            almacenarSegundaVez(producto, cartList, contexto)
        }
    }

    fun eliminarProductoDeSharedPreferences(producto: Producto, contexto: Context) {

        listaProductos = extraerListaDeCarro(contexto)

        listaProductos.remove(producto)

        var json: String = convertirListaDeProductosEnJsonString(listaProductos)

        salvarEstadoDeLista("cookie", json, contexto)

        print(mSharedPreferences.getString("cookie", ""))

    }

    fun extraerListaDeCarro(contexto: Context): MutableList<Producto> {
        mSharedPreferences = contexto.getSharedPreferences("cookie", Context.MODE_PRIVATE)
        gson = GsonBuilder().disableHtmlEscaping().create()

        var stringJson = mSharedPreferences.getString("cookie", "")

        val tipo = object : TypeToken<List<Producto?>?>() {}.type
        listaProductos = gson.fromJson(stringJson, tipo)

        return listaProductos
    }


    fun salvarEstadoDeLista(key: String?, value: String?, contexto: Context) {
        mSharedPreferences = contexto.getSharedPreferences("cookie", Context.MODE_PRIVATE)
        editor = mSharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun convertirListaDeProductosEnJsonString(listaProductos: MutableList<Producto>): String {

        var json: String = gson.toJson(listaProductos)
        return json

    }

    fun almacenarPrimeraVez(producto: Producto, contexto: Context) {
        var cartListCreation= mutableListOf<Producto>()
        cartListCreation.add(producto)
        var json: String = gson.toJson(cartListCreation)
        println("Json string en primera vez: ${json}")
        salvarEstadoDeLista("cookie", json, contexto);
    }

    fun almacenarSegundaVez(producto: Producto, jsonList: String, contexto: Context) {
        var listaProductos = mutableListOf<Producto>()
        val type: Type = object : TypeToken<List<Producto?>?>() {}.type
        listaProductos = gson.fromJson(jsonList, type)
        listaProductos.add(producto)
        var json: String = convertirListaDeProductosEnJsonString(listaProductos)
        salvarEstadoDeLista("cookie", json, contexto);
    }


}