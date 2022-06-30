package com.example.core_utils

fun getRandomImageLink(): String {
    val imagesList = listOf(
        "https://cdn1.ozone.ru/s3/multimedia-e/wc1200/6242867246.jpg",
        "https://cdn1.ozone.ru/s3/multimedia-d/wc1200/6043549285.jpg",
        "https://cdn1.ozone.ru/s3/multimedia-5/wc1200/6256519589.jpg",
        "https://cdn1.ozone.ru/s3/multimedia-r/wc1200/6283392387.jpg",
        "https://cdn1.ozone.ru/multimedia/wc1200/1015996078.jpg"
    )
    return imagesList.random()
}

fun getDefaultImageLink(): String =
    "https://nayemdevs.com/wp-content/uploads/2020/03/default-product-image.png"

fun getRandomProductName(): String {
    val imagesList = listOf(
        "Лапша Доширак",
        "Шоколадная паста Nutella",
        "Огурцы соленые в банке",
        "Макароны Barilla",
        "Печень трески"
    )
    return imagesList.random()
}

fun getRandomDescription(): String {
    val imagesList = listOf(
        "Очень вкусный продукт. Просто пальчики оближешь.",
        "Такой вкусный продукт, что когда кушаешь, теряешь сознание.",
        "Понравился продукт? Закажите еще и не забудьте оставить отзыв!",
        "Вкусный, качественный продукт. Никогда не будет лишним у Вас дома.",
        "Заказал раз - закажешь и другой!"
    )
    return imagesList.random()
}