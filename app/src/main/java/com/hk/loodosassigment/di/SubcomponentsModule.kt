package com.hk.loodosassigment.di

import com.hk.loodosassigment.main.di.MainComponent
import dagger.Module

@Module(
    subcomponents = [
        MainComponent::class
    ]
)
class SubcomponentsModule