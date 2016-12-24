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
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samelody.puppetry.core.Model;
import com.samelody.puppetry.core.PresenterDelegate;

/**
 * @author Belin Wu
 */
public abstract class PresenterFragment
        <V extends FragmentContract.View<P>, P extends FragmentContract.Presenter>
        extends DialogFragment
        implements FragmentContract.View<P> {

    /**
     * The delegate of the presenter.
     */
    private PresenterDelegate<V, P> delegate = new PresenterDelegate<>();

    /**
     * The toolbar of this fragment.
     */
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void setTitle(@StringRes int title) {
        if (hasToolbar()) {
            toolbar.setTitle(title);
        }
    }

    @Override
    public void setTitle(@StringRes int title, Object... args) {
        if (hasToolbar()) {
            toolbar.setTitle(getString(title, args));
        }
    }

    @Override
    public void setTitle(String title) {
        if (hasToolbar()) {
            toolbar.setTitle(title);
        }
    }

    private boolean hasToolbar() {
        return toolbar != null;
    }

    @Override
    public void setPresenter(P presenter) {
        delegate.setPresenter(presenter);
    }

    @Override
    public P getPresenter() {
        return delegate.getPresenter();
    }
}
