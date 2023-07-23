package cl.awakelab.sprint5

data class Producto(var nombre: String, var urlImagen: String, var precio: Double) {

    companion object {
        fun returnShoeList(): MutableList<Producto> {
            val shoeList = mutableListOf<Producto>()

            val shoeItem1 = Producto("Zapato 1", "https://img.freepik.com/free-psd/shoes-sale-social-media-post-square-banner-template-design_505751-2904.jpg?w=250", 99.99)
            shoeList.add(shoeItem1)
            val shoeItem2 = Producto("Zapato 2", "https://img.freepik.com/free-psd/shoes-sale-social-media-post-square-banner-template-design_505751-3120.jpg?w=250", 79.99)
            shoeList.add(shoeItem2)
            val shoeItem3 = Producto("Zapato 3", "https://img.freepik.com/free-psd/new-collection-sneakers-social-media-template_505751-3250.jpg?w=250", 149.99)
            shoeList.add(shoeItem3)
            val shoeItem4 = Producto("Zapato 4", "https://img.freepik.com/free-psd/shoes-sale-social-media-post-square-banner-template-design_505751-2947.jpg?w=250", 129.99)
            shoeList.add(shoeItem4)
            val shoeItem5 = Producto("Zapato 5", "https://img.freepik.com/free-psd/shoes-sale-social-media-post-square-banner-template-design_505751-2944.jpg?w=250", 109.99)
            shoeList.add(shoeItem5)
            val shoeItem6 = Producto("Zapato 6", "https://img.freepik.com/free-psd/shoes-sale-social-media-post-square-banner-template-design_505751-3736.jpg?w=250", 89.99)
            shoeList.add(shoeItem6)
            val shoeItem7 = Producto("Zapato 7", "https://img.freepik.com/free-psd/shoes-sale-social-media-post-square-banner-template-design_505751-3728.jpg?w=250", 199.99)
            shoeList.add(shoeItem7)
            val shoeItem8 = Producto("Zapato 8", "https://img.freepik.com/free-psd/new-collection-sneakers-social-media-template_505751-3253.jpg?w=250", 169.99)
            shoeList.add(shoeItem8)
            val shoeItem9 = Producto("Zapato 9", "https://img.freepik.com/premium-psd/sport-shoes-sale-social-media-instagram-post-square-banner-template-design_70055-2024.jpg?w=250", 119.99)
            shoeList.add(shoeItem9)
            val shoeItem10 = Producto("Zapato 10", "https://img.freepik.com/free-psd/sneakers-shoes-social-media-template_505751-3353.jpg?w=250", 149.99)
            shoeList.add(shoeItem10)
            return shoeList
        }
    }

}

