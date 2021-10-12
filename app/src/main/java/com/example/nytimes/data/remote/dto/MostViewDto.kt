package com.example.nytimes.data.remote.dto

import com.example.nytimes.domain.model.MostViewed
import com.google.gson.annotations.SerializedName

data class MostViewDto(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)

data class Result(
    val `abstract`: String,
    val adx_keywords: String,
    val asset_id: Long,
    val byline: String,
    val column: Any,
    val des_facet: List<String>,
    val eta_id: Int,
    val geo_facet: List<String>,
    val id: Long,
    val media: List<Media>?=null,
    val nytdsection: String,
    val org_facet: List<String>,
    val per_facet: List<String>,
    val published_date: String,
    val section: String,
    val source: String,
    val subsection: String,
    val title: String,
    val type: String,
    val updated: String,
    val uri: String,
    val url: String
)

fun Result.toMostViewed() : MostViewed {
    return if (media?.isEmpty()?.not() == true && media?.get(0)?.media_metadata?.isEmpty()?.not() == true){
        MostViewed(
            title = title,
            byline = byline,
            publishedDate = published_date,
            image = media?.get(0)?.media_metadata?.get(0)?.url ?: ""
        )
    } else {
        MostViewed(
            title = title,
            byline = byline,
            publishedDate = published_date,
            image = ""
        )
    }

}

data class Media(
    val approved_for_syndication: Int,
    val caption: String,
    val copyright: String,
    @SerializedName("media-metadata")
    val media_metadata: List<MediaMetadata>? = null,
    val subtype: String,
    val type: String
)

data class MediaMetadata(
    val format: String,
    val height: Int,
    val url: String,
    val width: Int
)

