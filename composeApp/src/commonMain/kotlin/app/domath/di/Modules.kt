package app.domath.di

import app.domath.data.provider.audio.AudioProvider
import app.domath.data.provider.audio.impl.AudioProviderImpl
import app.domath.domain.GenerateSearchColorTaskUseCase
import app.domath.domain.GetCongratulationAudioUseCase
import app.domath.domain.GetFailedAudioUseCase
import app.domath.domain.GetSearchColorAudioUseCase
import app.domath.domain.impl.GenerateSearchColorTaskUseCaseImpl
import app.domath.domain.impl.GetCongratulationAudioUseCaseImpl
import app.domath.domain.impl.GetFailedAudioUseCaseImpl
import app.domath.domain.impl.GetSearchColorAudioUseCaseImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::GenerateSearchColorTaskUseCaseImpl) bind GenerateSearchColorTaskUseCase::class
    singleOf(::AudioProviderImpl) bind AudioProvider::class
    singleOf(::GetSearchColorAudioUseCaseImpl) bind GetSearchColorAudioUseCase::class
    singleOf(::GetCongratulationAudioUseCaseImpl) bind GetCongratulationAudioUseCase::class
    singleOf(::GetFailedAudioUseCaseImpl) bind GetFailedAudioUseCase::class
}