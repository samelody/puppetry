/*
 * Copyright (c) 2017 Samelody.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package com.samelody.stathod;

import android.view.View;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * Utilities for views.
 *
 * @author Belin Wu
 */
public final class Views {

    /**
     * The private constructor.
     */
    private Views() {
    }

    public static boolean isGone(View view) {
        return view != null && view.getVisibility() == GONE;
    }

    public static boolean isVisible(View view) {
        return view != null && view.getVisibility() == VISIBLE;
    }

    public static boolean isInvisible(View view) {
        return view != null && view.getVisibility() == INVISIBLE;
    }
}
