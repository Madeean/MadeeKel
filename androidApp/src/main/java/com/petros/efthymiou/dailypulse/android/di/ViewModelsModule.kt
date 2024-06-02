package com.petros.efthymiou.dailypulse.android.di

import com.petros.efthymiou.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelsModule = module {
  viewModel { ArticlesViewModel(get()) }
}