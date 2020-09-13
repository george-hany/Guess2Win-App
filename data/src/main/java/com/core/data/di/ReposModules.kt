package com.core.data.di

import org.koin.dsl.module
import com.core.data.network.ApiFactory
import com.core.data.repos.*
import com.core.network.MockedNetwork
import com.core.network.NetworkFactory
import com.core.prefrence.secured_shared_preferences.SecuredSharedPreference
import com.core.prefrence.shared_preferences.AppSharedPreference
import com.core.utils.FileManager

val reposModules = module {

    single { ApiFactory(get()) }
    fun provideMainRepo(
        apiFactory: ApiFactory,
        sharedPreference: SecuredSharedPreference,
        mockedNetwork: MockedNetwork
    ): MainRepo {
        return MainRepo(
            apiFactory,
            sharedPreference,
            mockedNetwork
        )
    }
    factory { provideMainRepo(get(), get(), get()) }

    fun provideSplashRepo(
        sharedPreference: SecuredSharedPreference,
        networkFactory: NetworkFactory
    ): SplashRepo {
        return SplashRepo(sharedPreference, networkFactory)
    }
    factory { provideSplashRepo(get(), get()) }

    fun provideChooseLanguageRepo(
        sharedPreference: SecuredSharedPreference,
        networkFactory: NetworkFactory
    ): ChooseLanguageRepo {
        return ChooseLanguageRepo(sharedPreference, networkFactory)
    }
    factory { provideChooseLanguageRepo(get(), get()) }

    fun provideChooseThemeRepo(
        sharedPreference: SecuredSharedPreference,
        networkFactory: NetworkFactory
    ): ChooseThemeRepo {
        return ChooseThemeRepo(sharedPreference, networkFactory)
    }
    factory { provideChooseThemeRepo(get(), get()) }

    fun provideLoginRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: NetworkFactory
    ): LoginRepo {
        return LoginRepo(apiFactory, sharedPreference, networkFactory)
    }
    factory { provideLoginRepo(get(), get(), get()) }

    fun provideBoardRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: NetworkFactory
    ): BoardRepo {
        return BoardRepo(apiFactory, sharedPreference, networkFactory)
    }
    factory { provideBoardRepo(get(), get(), get()) }

    fun provideMatchesRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: NetworkFactory,
        fileManager: FileManager
    ): MatchesRepo {
        return MatchesRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { provideMatchesRepo(get(), get(), get(), get()) }

    fun provideMatchDetailsRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: NetworkFactory,
        fileManager: FileManager
    ): MatchDetailsRepo {
        return MatchDetailsRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { provideMatchDetailsRepo(get(), get(), get(), get()) }

    fun provideLeaguesRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: NetworkFactory,
        fileManager: FileManager
    ): LeaguesRepo {
        return LeaguesRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { provideLeaguesRepo(get(), get(), get(), get()) }

    fun provideLeaguesDetailsRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: MockedNetwork,
        fileManager: FileManager
    ): LeaguesDetailsRepo {
        return LeaguesDetailsRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { provideLeaguesDetailsRepo(get(), get(), get(), get()) }

    fun provideLeaguesRankRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: NetworkFactory,
        fileManager: FileManager
    ): LeaguesRankRepo {
        return LeaguesRankRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { provideLeaguesRankRepo(get(), get(), get(), get()) }

    fun provideRankContainerRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: MockedNetwork,
        fileManager: FileManager
    ): RankContainerRepo {
        return RankContainerRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { provideRankContainerRepo(get(), get(), get(), get()) }

    fun provideRankRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: NetworkFactory,
        fileManager: FileManager
    ): RankRepo {
        return RankRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { provideRankRepo(get(), get(), get(), get()) }

    fun provideProfileRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: NetworkFactory,
        fileManager: FileManager
    ): ProfileRepo {
        return ProfileRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { provideProfileRepo(get(), get(), get(), get()) }

    fun provideSettingsRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: MockedNetwork,
        fileManager: FileManager
    ): SettingsRepo {
        return SettingsRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { provideSettingsRepo(get(), get(), get(), get()) }

    fun provideExtraPointsRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: NetworkFactory,
        fileManager: FileManager
    ): ExtraPointsRepo {
        return ExtraPointsRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { provideExtraPointsRepo(get(), get(), get(), get()) }

    fun providePrizesRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: NetworkFactory,
        fileManager: FileManager
    ): PrizesRepo {
        return PrizesRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { providePrizesRepo(get(), get(), get(), get()) }

    fun provideHelpRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: NetworkFactory,
        fileManager: FileManager
    ): HelpRepo {
        return HelpRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { provideHelpRepo(get(), get(), get(), get()) }

    fun provideContactUsRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: MockedNetwork,
        fileManager: FileManager
    ): ContactUsRepo {
        return ContactUsRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { provideContactUsRepo(get(), get(), get(), get()) }

    fun provideChangeThemeRepo(
        sharedPreference: SecuredSharedPreference,
        networkFactory: NetworkFactory
    ): ChangeThemeRepo {
        return ChangeThemeRepo(sharedPreference, networkFactory)
    }
    factory { provideChangeThemeRepo(get(), get()) }
}
