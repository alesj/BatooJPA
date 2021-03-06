/*
 * Copyright (c) 2012 - Batoo Software ve Consultancy Ltd.
 * 
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.batoo.jpa.core.test.lob;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.batoo.jpa.core.test.BaseCoreTest;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.Sets;

/**
 * @author hceylan
 * 
 * @since $version
 */
@Ignore
// FIXME: https://issues.apache.org/jira/browse/DBUTILS-90
public class LobTest extends BaseCoreTest {

	/**
	 * Tests to {@link EntityManager#persist(Object)} then {@link EntityManager#find(Class, Object)} with lob values
	 * 
	 * @since $version
	 * @author hceylan
	 */
	@Test
	public void testLob() {
		final Foo foo = new Foo();
		foo.getValues().add("Value1");
		foo.getValues().add("Value2");

		this.persist(foo);

		this.commit();

		this.close();

		final Foo foo2 = this.find(Foo.class, foo.getKey());
		Assert.assertEquals(foo.getKey(), foo2.getKey());
		Assert.assertEquals(Sets.newHashSet("Value1", "Value2"), foo2.getValues());
	}
}
