/*
 * Copyright 2015 Google Inc.
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
package com.mmt.shubh.expensemanager.ui.component.outlineprovider;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * Abstract class to create round outlines for views.
 */
public abstract class RoundOutlineProvider extends ViewOutlineProvider {

    @Override
    public final void getOutline(View view, Outline outline) {
        final int size = view.getResources().getDimensionPixelSize(getSizeResourceId());
        outline.setOval(0, 0, size, size);
    }

    /**
     * Implementations need to provide a dimension resource which is used as the outline's size.
     *
     * @return The dimension resource id.
     */
    public abstract int getSizeResourceId();
}
