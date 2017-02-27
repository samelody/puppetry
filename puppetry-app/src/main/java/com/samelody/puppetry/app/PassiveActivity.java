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
package com.samelody.puppetry.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.samelody.puppetry.core.ViewController;
import com.samelody.puppetry.core.Contract.PassiveView;
import com.samelody.puppetry.core.Contract.Presenter;
import com.samelody.puppetry.core.PuppetryDelegate;

public abstract class PassiveActivity<P extends Presenter>
        extends AppCompatActivity
        implements PassiveView, ViewController<P> {

    private PuppetryDelegate<P> delegate = new PuppetryDelegate<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate.onViewCreate(this, savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        delegate.onViewStart(this, createRouter());
    }

    @Override
    protected void onResume() {
        super.onResume();
        delegate.onViewResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        delegate.onViewPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        delegate.onViewStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        delegate.onStateSave(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        delegate.onViewDestroy(this);
    }

    @Override
    public P getPresenter() {
        return delegate.getPresenter();
    }
}
