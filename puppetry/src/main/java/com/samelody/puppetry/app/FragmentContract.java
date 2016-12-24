/*
 * Copyright (c) 2016 Samelody.
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

package com.samelody.puppetry.app;

import android.support.annotation.StringRes;

import com.samelody.puppetry.core.Contract;
import com.samelody.puppetry.core.Model;

/**
 * THe MVP contract for fragments.
 *
 * @author Belin Wu
 */
public interface FragmentContract {

    /**
     * The passive view for fragments.
     *
     * @param <P> The type of fragments' presenter.
     */
    interface View<P extends Contract.Presenter>
            extends Contract.View<P> {

        void setTitle(@StringRes int title);
        void setTitle(@StringRes int title, Object... args);
        void setTitle(String title);
    }

    /**
     * The presenter for fragments.
     *
     * @param <V> The type of the passive view.
     * @param <M> The type of the presentation model.
     */
    interface Presenter<V extends View, M extends Model>
            extends Contract.Presenter<V, M> {

    }
}
