/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other
 * contributors as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.infinispan.query.impl;

import org.infinispan.Cache;
import org.infinispan.factories.ComponentRegistry;
import org.infinispan.query.backend.LocalQueryInterceptor;
import org.infinispan.query.backend.QueryInterceptor;

/**
 * Component registry utilities
 *
 * @author Marko Luksa
 * @author Galder Zamarreño
 */
public class ComponentRegistryUtils {

   private ComponentRegistryUtils() {
   }

   public static <T> T getComponent(Cache<?, ?> cache, Class<T> class1) {
      ComponentRegistry componentRegistry = cache.getAdvancedCache().getComponentRegistry();
      T component = componentRegistry.getComponent(class1);
      if (component == null) {
         throw new IllegalArgumentException("Indexing was not enabled on this cache. " + class1 + " not found in registry");
      }
      return component;
   }

   public static QueryInterceptor getQueryInterceptor(Cache<?, ?> cache) {
      Class<? extends QueryInterceptor> queryType = cache.getCacheConfiguration().indexing().indexLocalOnly()
            ? LocalQueryInterceptor.class : QueryInterceptor.class;
      return getComponent(cache, queryType);
   }

}
