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

package com.samelody.puppetry;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.samelody.puppetry.Contract.PassiveView;
import com.samelody.puppetry.Contract.Presenter;

import static java.lang.System.currentTimeMillis;

/**
 * The delegation of the presenter.
 *
 * @author Belin Wu
 */
class PresenterDelegateImpl<P extends Presenter> implements PresenterDelegate<P> {

    /**
     * Key of presenter id.
     */
    private static final String KEY_PRESENTER_ID = "puppetry_presenter_id";

    /**
     * The prefix of the presenter id.
     */
    private static final String PRESENTER_ID_PREFIX = "presenter-";

    /**
     * The presenter.
     */
    private AbstractPresenter presenter;

    /**
     * The target proxy of presenter.
     */
    private TargetProxy<P> presenterProxy = new TargetProxy<>();

    /**
     * The presenter id.
     */
    private String presenterId;

    private boolean saveStateCalled;

    private boolean presenterRemoved;

    PresenterDelegateImpl(Class<P> presenterInterface) {
        presenterProxy.setTargetInterface(presenterInterface);
    }

    @Override
    public void onViewCreate(Controller<P> controller, Bundle state) {
        if (state == null) {
            presenterId = PRESENTER_ID_PREFIX + currentTimeMillis();
        }
        else {
            presenterId = state.getString(KEY_PRESENTER_ID);
            saveStateCalled = false;
        }
        presenter = PresenterManager.getInstance().getPresenter(presenterId, controller);
        presenterProxy.setTarget((P) presenter);
        presenterRemoved = false;
        presenter.create();
    }

    @Override
    public void attachView(Controller<P> controller, boolean visible) {
        if (presenterRemoved) {
            return;
        }
        presenter.attachView(controller, controller.getRouter(), visible);
    }

    @Override
    public void detachView() {
        if (presenterRemoved) {
            return;
        }
        presenter.detachView();
    }

    @Override
    public void onViewStart() {
        if (presenterRemoved) {
            return;
        }
        presenter.start();
    }

    @Override
    public void onViewResume(boolean visible) {
        if (presenterRemoved) {
            return;
        }
        presenter.resume(visible);
    }

    @Override
    public void onViewPause() {
        if (presenterRemoved) {
            return;
        }
        presenter.pause();
    }

    @Override
    public void onViewStop() {
        if (presenterRemoved) {
            return;
        }
        presenter.stop();
    }

    @Override
    public void onSaveState(Bundle state) {
        if (presenterRemoved) {
            return;
        }
        state.putString(KEY_PRESENTER_ID, presenterId);
        saveStateCalled = true;
    }

    @Override
    public void onViewDestroy(PassiveView view) {
        if (presenterRemoved) {
            return;
        }
        if (view instanceof Fragment) {
            Fragment fragment = (Fragment) view;
            if (fragment.getActivity().isFinishing()) {
                removePresenter();
                return;
            }
            if (fragment.isRemoving() && !saveStateCalled) {
                removePresenter();
            }
            return;
        }
        if (view instanceof Activity) {
            Activity activity = (Activity) view;
            if (activity.isFinishing()) {
                removePresenter();
            }
        }
    }

    @Override
    public P createPresenterAndModel() {
        String implName = presenterProxy.getTargetInterface().getName().replace("Contract$", "");
        try {
            AbstractPresenter presenter = (AbstractPresenter) Class.forName(implName).newInstance();
            presenter.createModel();
            return (P) presenter;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the presenter.
     *
     * @return The presenter.
     */
    @Override
    public P getPresenter() {
        return presenterProxy.getTarget();
    }

    /**
     * Remove the presenter.
     */
    private void removePresenter() {
        if (presenterRemoved) {
            return;
        }
        PresenterManager.getInstance().remove(presenterId);
        presenter.destroy();
        presenter = null;
        presenterProxy.setTarget(null);
        presenterRemoved = true;
    }
}
