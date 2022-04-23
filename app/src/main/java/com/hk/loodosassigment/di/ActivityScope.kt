package com.hk.loodosassigment.di

import javax.inject.Scope

// Definition of a custom scope called ActivityScope
@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope
