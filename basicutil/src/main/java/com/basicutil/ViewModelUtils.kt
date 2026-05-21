package com.basicutil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


fun ViewModel.safeLaunch(
    dispatcher: CoroutineDispatcher,
    onLoading: suspend (isLoading: Boolean) -> Unit = {},
    onError: suspend (Exception) -> Unit = {},
    body: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(dispatcher){
        try {
            onLoading(true)
            body()
        } catch (e: CancellationException){
            throw  e
        } catch (e: Exception){
            onError(e)
        } finally {
            onLoading(false)
        }
    }
}