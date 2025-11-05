/*
 * Copyright (c) 2020. BoostTag E.I.R.L. Romell D.Z.
 * All rights reserved
 * porfile.romellfudi.com
 */

package com.romellfudi.ussd.main.statehood

sealed class UssdState {
    object Successful : UssdState()
    data class Progress(val progress: Int) : UssdState()
    data class Error(val errorMessage: String = "Not complete the whole path") : UssdState()
}