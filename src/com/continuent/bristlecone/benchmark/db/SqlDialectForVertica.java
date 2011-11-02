/**
 * Bristlecone Test Tools for Databases
 * Copyright (C) 2006-2007 Continuent Inc.
 * Contact: bristlecone@lists.forge.continuent.org
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 *
 * Initial developer(s): Robert Hodges and Ralph Hannus.
 * Contributor(s):
 */

package com.continuent.bristlecone.benchmark.db;

/**
 * PostgreSQL DBMS dialect information.
 * 
 * @author rhodges
 */
public class SqlDialectForVertica extends SqlDialectForPostgreSQL
{
    /** Return the PostgreSQL driver. */
    public String getDriver()
    {
        return "com.vertica.Driver";
    }

    /** Returns true if the JDBC URL looks like a PostgreSQL URL. */
    public boolean supportsJdbcUrl(String url)
    {
        return url.startsWith("jdbc:vertica");
    }

    /**
     * PostgreSQL uses "serial" datatype for autoincrement but does not have a
     * keyword
     */
    public String implementationAutoIncrementKeyword()
    {
        // This should not be called or we will generate bad syntax.
        throw new Error(
                "Bug: Vertica dialect should not use autoincrement keyword");
    }

    /**
     * Provides general default on index support for OLTP databases.
     */
    public boolean implementationSupportsIndexes()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.bristlecone.benchmark.db.SqlDialect#implementationSupportsSupplementaryTableDdl()
     */
    public boolean implementationSupportsSupplementaryTableDdl()
    {
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.bristlecone.benchmark.db.AbstractSqlDialect#getSupplementaryTableDdl(com.continuent.bristlecone.benchmark.db.Table)
     */
    public String getSupplementaryTableDdl(Table table)
    {
        // Necessary to create default projects required for DML. 
        return "select implement_temp_design('')";
    }

}