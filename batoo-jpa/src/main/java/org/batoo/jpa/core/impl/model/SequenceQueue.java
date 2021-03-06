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
package org.batoo.jpa.core.impl.model;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;

import org.batoo.jpa.core.impl.jdbc.DataSourceImpl;
import org.batoo.jpa.core.jdbc.adapter.JdbcAdaptor;

/**
 * A Queue that tops up the queue by allocation size sequences when its capacity drops to 1 / 2 of allocation size.
 * 
 * @author hceylan
 * @since $version
 */
public class SequenceQueue extends IdQueue {

	private static final long serialVersionUID = 1L;
	private final JdbcAdaptor jdbcAdaptor;
	private final DataSourceImpl datasource;
	private final String sequenceName;

	/**
	 * @param jdbcAdaptor
	 *            the JDBC adaptor
	 * @param datasource
	 *            the datasource to use
	 * @param idExecuter
	 *            the executor service to submit refill tasks
	 * @param sequenceName
	 *            the physical name of the sequence
	 * @param allocationSize
	 *            the allocations size
	 * 
	 * @since $version
	 * @author hceylan
	 */
	public SequenceQueue(JdbcAdaptor jdbcAdaptor, DataSourceImpl datasource, ExecutorService idExecuter, String sequenceName, int allocationSize) {
		super(idExecuter, sequenceName, allocationSize);

		this.jdbcAdaptor = jdbcAdaptor;
		this.datasource = datasource;
		this.sequenceName = sequenceName;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	protected Long getNextId() throws SQLException {
		return this.jdbcAdaptor.getNextSequence(this.datasource, this.sequenceName);
	}
}
