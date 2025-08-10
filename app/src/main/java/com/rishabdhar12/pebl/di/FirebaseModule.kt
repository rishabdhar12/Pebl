package com.rishabdhar12.pebl.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.rishabdhar12.services.firebase.FirebaseAuthService
import com.rishabdhar12.services.firebase.FirebaseFirestoreService
import com.rishabdhar12.services.firebase.FirebaseRemoteConfigService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuthService(auth: FirebaseAuth): FirebaseAuthService {
        return FirebaseAuthService(auth)
    }

    @Provides
    @Singleton
    fun providerFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestoreService(firestore: FirebaseFirestore): FirebaseFirestoreService {
        return FirebaseFirestoreService(firestore)
    }

    @Provides
    @Singleton
    fun provideRemoteConfig(): FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseRemoteConfigService(remoteConfig: FirebaseRemoteConfig): FirebaseRemoteConfigService {
        return FirebaseRemoteConfigService(remoteConfig)
    }
}