/*
 * Copyright (c) 2017-present Samelody.com
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

import com.samelody.puppetry.Contract.PassiveView;
import com.samelody.puppetry.Contract.Presenter;
import com.samelody.puppetry.Contract.Router;

/**
 * Represents the controller of Android views.
 *
 * @author Belin Wu
 * @param <P> The type of the presenter.
 */
public interface Controller<P extends Presenter> extends PassiveView {

    /**
     * Gets the router.
     *
     * @return The created router.
     */
    Router getRouter();

    /**
     * Creates the presenter.
     *
     * @return The created presenter.
     */
    P createPresenter();

    /**
     * Gets the presenter.
     *
     * @return The presenter.
     */
    P getPresenter();
}
