/*
 * Copyright (c) 2020. BoostTag E.I.R.L. Romell D.Z.
 * All rights reserved
 * porfile.romellfudi.com
 */

package com.romellfudi.ussd.accessibility.presenter

import com.romellfudi.ussd.accessibility.interactor.AccessibilityMVPInteraction
import com.romellfudi.ussd.accessibility.view.AccessibilityMVPView


class AccessibilityPresenter<V : AccessibilityMVPView, I : AccessibilityMVPInteraction>
         internal constructor(var integrator: I?) : AccessibilityMVPPresenter<V, I>