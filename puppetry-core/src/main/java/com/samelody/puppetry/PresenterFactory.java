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
package com.samelody.puppetry;

import com.samelody.puppetry.Puppetry.Presenter;

/**
 * The factory for creating the presenter.
 *
 * @author Belin Wu
 * @param <P> The type of presenter
 */
public interface PresenterFactory<P extends Presenter> {
    /**
     * Creates the presenter.
     *
     * @return The created presenter.
     */
    P createPresenter();
}
