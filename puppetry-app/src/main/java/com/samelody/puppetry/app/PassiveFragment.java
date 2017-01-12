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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.samelody.puppetry.Controller;
import com.samelody.puppetry.Puppetry.PassiveView;
import com.samelody.puppetry.Puppetry.Presenter;
import com.samelody.puppetry.PuppetryDelegate;

/**
 * @author Belin Wu
 */
public abstract class PassiveFragment<P extends Presenter>
        extends DialogFragment
        implements PassiveView, Controller<P> {

    /**
     * The puppetry delegate.
     */
    private PuppetryDelegate<P> delegate = new PuppetryDelegate<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        delegate.onViewCreate(this, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        delegate.onViewStart(this, createRouter());
    }

    @Override
    public void onStop() {
        super.onStop();
        delegate.onViewStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        delegate.onViewResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        delegate.onViewPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        delegate.onViewDestroy(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        delegate.onStateSave(outState);
    }

    @Override
    public P getPresenter() {
        return delegate.getPresenter();
    }
}
