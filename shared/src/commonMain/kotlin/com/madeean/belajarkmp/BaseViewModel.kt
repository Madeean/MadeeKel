package com.madeean.belajarkmp

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {
  val scope: CoroutineScope
}