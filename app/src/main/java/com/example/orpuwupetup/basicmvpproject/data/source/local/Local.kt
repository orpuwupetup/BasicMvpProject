package com.example.orpuwupetup.basicmvpproject.data.source.local

import javax.inject.Qualifier

// Annotation to differentiate between local and remote data source during dependency injection inside repositories
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Local