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

import static com.samelody.puppetry.Puppetry.newProxy;

/**
 * The target proxy.
 *
 * @author Belin Wu
 */
public class TargetProxy<T> {

    /**
     * The target
     */
    private T target;

    /**
     * The target proxy.
     */
    private T proxy;

    /**
     * The type of target interface.
     */
    private Class<T> targetInterface;

    public void setTargetInterface(Class<T> targetInterface) {
        this.targetInterface = targetInterface;
    }

    public Class<T> getTargetInterface() {
        return targetInterface;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    public T getTarget() {
        if (target != null) {
            return target;
        }
        if (proxy == null) {
            proxy = newProxy(targetInterface);
        }
        return proxy;
    }
}
