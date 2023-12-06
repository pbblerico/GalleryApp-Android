package com.example.galleryapp.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleryapp.shared.base.UiEffect
import com.example.galleryapp.shared.base.UiEvent
import com.example.galleryapp.shared.base.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class TestBaseViewModel <State : UiState, Event : UiEvent, Effect : UiEffect> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeEvents()
    }


    abstract fun handleEvent(event: Event)

    fun <T> launch(
        request: suspend () -> T,
        onSuccess: (T?) -> Unit = { },
        onError: ((String?) -> Unit) ? = null,
        onFinally: (() -> Unit)? = null
    ) {
        viewModelScope.launch {
            try {
                val response = request.invoke()
                onSuccess.invoke(response)
            } catch (e: Exception) {
                onError?.invoke(e.message.orEmpty())
                Log.e(">>>", e.message.orEmpty())
            } finally {
                onFinally?.invoke()
            }
        }
    }

    fun setEvent(event: Event) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }

    protected fun setState(state: State) {
//        val newState = uiState.value.reduce()
        _uiState.value = state
    }

    protected fun setEffect(effect: Effect) {
//        val effectValue = builder()
        viewModelScope.launch { _effect.send(effect) }
    }

    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }
}