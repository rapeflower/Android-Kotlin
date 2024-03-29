package com.lily.kotlin.ui.edittext

import android.text.Editable
import android.text.TextWatcher

/**
 * Base text watcher
 */
abstract class BaseTextWatcher : TextWatcher {

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}