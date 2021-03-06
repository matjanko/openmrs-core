/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.util.databasechange;

import org.junit.Test;
import org.openmrs.util.ClassLoaderFileOpener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClassLoaderFileOpenerTest {
	
	@Test
	public void shouldGetSingleResourceAsStream() throws IOException {
		ClassLoader classLoader = mock(ClassLoader.class);
		
		InputStream inputStream = mock(InputStream.class);
		when(classLoader.getResourceAsStream(any())).thenReturn(inputStream);
		
		ClassLoaderFileOpener classLoaderFileOpener = new ClassLoaderFileOpener(classLoader);
		Set<InputStream> inputStreamSet = classLoaderFileOpener.getResourcesAsStream("some path");
		
		assertEquals(1, inputStreamSet.size());
		assertEquals(inputStream, inputStreamSet.iterator().next());
	}
	
	@Test
	public void shouldGetNoResourceAsStream() throws IOException {
		ClassLoader classLoader = mock(ClassLoader.class);
		
		ClassLoaderFileOpener classLoaderFileOpener = new ClassLoaderFileOpener(classLoader);
		Set<InputStream> inputStreamSet = classLoaderFileOpener.getResourcesAsStream("");
		
		assertEquals(0, inputStreamSet.size());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void shouldIndicateThatListIsNotSupported() throws IOException {
		ClassLoader classLoader = mock(ClassLoader.class);
		
		ClassLoaderFileOpener classLoaderFileOpener = new ClassLoaderFileOpener(classLoader);
		classLoaderFileOpener.list("", "", false, false, false);
	}
}
