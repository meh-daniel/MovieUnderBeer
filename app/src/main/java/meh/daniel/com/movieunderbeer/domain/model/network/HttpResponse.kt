package meh.daniel.com.movieunderbeer.domain.model.network

interface HttpResponse {

    val statusCode: Int

    val statusMessage: String?

    val url: String?

}
