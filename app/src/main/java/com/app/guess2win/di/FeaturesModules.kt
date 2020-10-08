package com.app.guess2win.di

import com.feature.board.di.boardModule
import com.feature.choose_language.di.chooseLanguageModule
import com.feature.choose_theme.di.chooseThemeModule
import com.feature.contactus.di.contactUsModule
import com.feature.extrapoints.di.extraPointsModule
import com.feature.help.di.helpModule
import com.feature.leagues.di.leaguesModule
import com.feature.login.di.loginModule
import com.feature.matches.di.matchesModule
import com.feature.prizes.di.prizesModule
import com.feature.profile.di.profileModule
import com.feature.rank.di.rankContainerModule
import com.feature.settings.di.settingsModule
import org.koin.core.module.Module
import com.feature.splash.di.splashModule

var allFeatures = listOf<Module>(
    splashModule,
    chooseLanguageModule,
    chooseThemeModule,
    loginModule,
    boardModule,
    matchesModule,
    leaguesModule,
    rankContainerModule,
    profileModule,
    settingsModule,
    extraPointsModule,
    helpModule,
    prizesModule,
    contactUsModule
)
