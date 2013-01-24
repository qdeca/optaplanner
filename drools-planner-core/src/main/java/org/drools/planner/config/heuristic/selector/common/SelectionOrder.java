/*
 * Copyright 2012 JBoss Inc
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

package org.drools.planner.config.heuristic.selector.common;

import org.drools.planner.config.heuristic.selector.SelectorConfig;
import org.drools.planner.core.heuristic.selector.common.SelectionCacheType;

/**
 * Defines in which order the elements or a selector are selected.
 */
public enum SelectionOrder {
    /**
     * Inherit the value from the parent {@value SelectorConfig}. If the parent is cached,
     * the value is changed to {@link #ORIGINAL}.
     * <p/>
     * This is the default. If there is no such parent, then it defaults to {@link #RANDOM}.
     */
    INHERIT,
    /**
     * Select the elements in original order.
     */
    ORIGINAL,
    /**
     * Select in sorted order by sorting the elements.
     * Each element will be selected exactly once (if all elements end up being selected).
     * Requires {@link SelectionCacheType#STEP} or higher.
     */
    SORTED,
    /**
     * Select in random order, without shuffling the elements.
     * Each element might be selected multiple times.
     * Scales well because it does not require caching.
     */
    RANDOM,
    /**
     * Select in random order by shuffling the elements when a selection iterator is created.
     * Each element will be selected exactly once (if all elements end up being selected).
     * Requires {@link SelectionCacheType#STEP} or higher.
     */
    SHUFFLED,
    /**
     * Select in random order, based on the selection probability of each element.
     * Elements with a higher probability have a higher chance to be selected than elements with a lower probability.
     * Each element might be selected multiple times.
     * Requires {@link SelectionCacheType#STEP} or higher.
     */
    PROBABILISTIC;

    /**
     * @param selectionOrder sometimes null
     * @param inheritedSelectionOrder never null
     * @return never null
     */
    public static SelectionOrder resolve(SelectionOrder selectionOrder, SelectionOrder inheritedSelectionOrder) {
        if (selectionOrder == null || selectionOrder == INHERIT) {
            if (inheritedSelectionOrder == null) {
                throw new IllegalArgumentException("The inheritedSelectionOrder (" + inheritedSelectionOrder
                        + ") cannot be null.");
            }
            return inheritedSelectionOrder;
        } else {
            return selectionOrder;
        }
    }

}
