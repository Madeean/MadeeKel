package com.petros.efthymiou.dailypulse.articles.application

import com.petros.efthymiou.dailypulse.articles.data.ArticleRaw
import com.petros.efthymiou.dailypulse.articles.data.ArticlesRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticlesUseCase(private val repo: ArticlesRepository) {
  suspend fun getArticles(forceFetch:Boolean): List<Article> {
    val articleRaw = repo.getArticles(forceFetch)
    return mapArticles(articleRaw)
  }

  private fun mapArticles(articleRaw: List<ArticleRaw>): List<Article> {
    return articleRaw.map {
      Article(
        it.title,
        it.desc ?: "Click to find out more",
        getDaysAgoString(it.date),
        it.imageUrl ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080"
      )
    }
  }

  private fun getDaysAgoString(date: String): String{
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val days = today.daysUntil(
      Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
    )

    val result = when {
      abs(days) > 1 -> "${abs(days)} days ago"
      abs(days) == 1 -> "Yesterday"
      else -> "Today"
    }

    return result
  }
}