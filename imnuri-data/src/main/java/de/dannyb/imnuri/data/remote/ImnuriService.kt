package de.dannyb.imnuri.data.remote

import de.dannyb.imnuri.data.remote.dto.HymnResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ImnuriService {
    @GET("imnuri/json-{locale}")
    suspend fun getHymns(@Path("locale") locale: String): HymnResponse
}