/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.protocol.http;

import junit.framework.Assert;

import org.apache.wicket.Session;
import org.apache.wicket.WicketTestCase;
import org.apache.wicket.util.io.PageA;
import org.apache.wicket.util.io.PageB;

/**
 * @author jcompagner
 */
public class FilePageStoreTest extends WicketTestCase
{
	/**
	 * @throws Exception
	 */
	public void testPageSerialization() throws Exception
	{
		PageB b = new PageB("test");
		PageA a = new PageA(b);
		b.setA(a);
		
		tester.setupRequestAndResponse();
		
		Session session = Session.get();
		
		a.getPageMap().put(a);
		a.getPageMap().put(b);
		
		PageA a2 = (PageA)a.getPageMap().get(a.getNumericId(), a.getCurrentVersionNumber());
		
		Assert.assertEquals(a, a2);
		
		Assert.assertSame(a2, a2.getB().getA());
	}
}
