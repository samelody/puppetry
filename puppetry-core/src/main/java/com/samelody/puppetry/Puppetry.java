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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static com.samelody.stathod.util.Objects.requireNonNull;
import static java.lang.reflect.Proxy.newProxyInstance;

/**
 * @author Belin Wu
 */
public final class Puppetry {

    /**
     * Private constructor.
     */
    private Puppetry() {
    }

    /**
     * The null invocation handler.
     */
    private static final InvocationHandler NULL_INVOCATION_HANDLER = new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    };

    /**
     * The null presenter
     */
    private static final PresenterDelegate NULL_PRESENTER_DELEGATE = newProxy(PresenterDelegate.class);

    /**
     * Gets the type of presenter interface.
     *
     * @param view The passive view
     * @param <P>  The type of presenter interface
     * @return The type of presenter interface
     */
    @SuppressWarnings("unchecked")
    private static <P extends Presenter> Class<P> getPresenterInterface(PassiveView view) {
        requireNonNull(view, "the passive view must not be null");
        Type superclass = view.getClass().getGenericSuperclass();
        if (!(superclass instanceof ParameterizedType)) {
            return null;
        }
        Type[] typeArgs = ((ParameterizedType) superclass).getActualTypeArguments();
        Type presenterType = typeArgs[0];
        if (!(presenterType instanceof Class)) {
            throw new IllegalArgumentException("The View's presenter interface must not be parameterized.");
        }
        return (Class<P>) presenterType;
    }

    /**
     * Creates the presenter delegate.
     *
     * @param view The passive view
     * @param <P> The type of presenter interface
     * @return The created presenter delegate.
     */
    @SuppressWarnings("unchecked")
    public static <P extends Presenter> PresenterDelegateImpl<P> newPresenterDelegate(PassiveView view) {
        Class<P> presenterInterface = getPresenterInterface(view);
        boolean presenterOff = presenterInterface == Presenter.class;
        if (presenterOff) {
            return (PresenterDelegateImpl<P>) NULL_PRESENTER_DELEGATE;
        }
        return new PresenterDelegateImpl<>(presenterInterface);
    }

    /**
     * Creates the new proxy.
     *
     * @param proxyInterface The proxy interface
     * @param <P> The type of proxy interface
     * @return The new proxy instance.
     */
    @SuppressWarnings("unchecked")
    static <P> P newProxy(Class<P> proxyInterface) {
        return (P) newProxyInstance(Puppetry.class.getClassLoader(),
                new Class[]{ proxyInterface }, NULL_INVOCATION_HANDLER);
    }
}
