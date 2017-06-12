package io.neoterm.preference

import android.content.Context
import android.preference.PreferenceManager
import io.neoterm.backend.TerminalSession
import io.neoterm.services.NeoTermService

/**
 * @author kiva
 */

object NeoTermPreference {
    var CURRENT_SESSION_KEY = "neoterm_current_session"

    fun storeCurrentSession(context: Context, session: TerminalSession) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(NeoTermPreference.CURRENT_SESSION_KEY, session.mHandle)
                .apply()
    }

    fun getCurrentSession(termService: NeoTermService?): TerminalSession? {
        val sessionHandle = PreferenceManager.getDefaultSharedPreferences(termService!!).getString(CURRENT_SESSION_KEY, "")
        var i = 0
        val len = termService.sessions.size
        while (i < len) {
            val session = termService.sessions[i]
            if (session.mHandle == sessionHandle) return session
            i++
        }
        return null
    }
}