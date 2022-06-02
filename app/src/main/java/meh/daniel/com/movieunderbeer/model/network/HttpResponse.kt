package meh.daniel.com.movieunderbeer.model.network

interface HttpResponse {

    val statusCode: Int

    val statusMessage: String?

    val url: String?

}
