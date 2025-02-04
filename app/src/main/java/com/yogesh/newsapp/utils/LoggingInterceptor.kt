package com.yogesh.newsapp.utils

import okhttp3.logging.HttpLoggingInterceptor

object LoggingInterceptor {
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY) // Choose the log level (BODY, HEADERS, etc.)
        return logging
    }
}