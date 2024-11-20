package com.loc.newsapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.loc.newsapp.domain.manager.LocalUserManager
import com.loc.newsapp.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalManagerImpl(
    private val context: Context
): LocalUserManager {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)

    override suspend fun saveAppEntry() {
      context.dataStore.edit { preference ->
         preference[PreferencesKeys.APP_ENTRY] = true
      }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] == true  //checks if the value stored in the datastore is true and if the value is null it will default to null
        }
    }
}

private object  PreferencesKeys{
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}