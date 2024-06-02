package com.petros.efthymiou.dailypulse.articles.data

import petros.efthymiou.dailypulse.db.DailyPulseDatabase

class ArticlesDataSource(private val database: DailyPulseDatabase) {

  fun getAllArticles(): List<ArticleRaw> =
    database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

  fun insertArticles(articles: List<ArticleRaw>) {
    database.dailyPulseDatabaseQueries.transaction {
      articles.forEach {
        insertArticle(it)
      }
    }
  }

  fun clearArticles() = database.dailyPulseDatabaseQueries.removeAllArticles()

  private fun insertArticle(articleRaw: ArticleRaw) {
    database.dailyPulseDatabaseQueries.insertArticle(
      articleRaw.title,
      articleRaw.desc,
      articleRaw.date,
      articleRaw.imageUrl
    )
  }

  private fun mapToArticleRaw(
    title: String,
    desc: String?,
    date: String,
    url: String?
  ): ArticleRaw {
    return ArticleRaw(
      title,
      desc,
      date,
      url
    )
  }
}