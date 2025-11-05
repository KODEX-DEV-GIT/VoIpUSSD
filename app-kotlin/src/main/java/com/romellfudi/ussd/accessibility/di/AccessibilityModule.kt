/*
 * Copyright (c) 2021. BoostTag E.I.R.L. Romell D.Z.
 * All rights reserved
 * portfolio.romellfudi.com
 */

package com.romellfudi.ussd.accessibility.di

import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.romellfudi.permission.PermissionService
import com.romellfudi.ussd.accessibility.interactor.AccessibilityInteraction
import com.romellfudi.ussd.accessibility.interactor.AccessibilityMVPInteraction
import com.romellfudi.ussd.accessibility.presenter.AccessibilityMVPPresenter
import com.romellfudi.ussd.accessibility.presenter.AccessibilityPresenter
import com.romellfudi.ussd.accessibility.view.AccessibilityMVPView
import org.koin.dsl.module

/**
 * Accessibility module.
 *
 * @author Romell Domínguez
 * @version 1.12.a 27/09/2018
 * @since 1.12.a
 */
val accessibilityModule = module {

    single { PermissionService }

    factory { AppUpdateManagerFactory.create(get()) }

    single<AccessibilityMVPInteraction> { AccessibilityInteraction(get()) }
    single<AccessibilityMVPPresenter<AccessibilityMVPView, AccessibilityMVPInteraction>> { AccessibilityPresenter(get()) }

}
