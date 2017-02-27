/*
 * Copyright (c) 2016-present Samelody.com
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
package com.samelody.puppetry.sample.module.main;

import com.samelody.puppetry.lifecycle.LifecyclePresenter;
import com.samelody.puppetry.sample.module.main.MainContract.PassiveView;
import com.samelody.puppetry.sample.route.SampleRouter;

/**
 * @author Belin Wu
 */
public class MainPresenter
        extends LifecyclePresenter<PassiveView, MainModel, SampleRouter>
        implements MainContract.Presenter {

    @Override
    public void onHiClick() {
        getView().toastHi();
        getModel().getId();
    }

    @Override
    public void onExitClick() {
        getModel().getId();
        getRouter().exit();
    }
}
