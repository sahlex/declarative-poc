/*
 * Copyright (C) Brodos AG - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package com.brodos.ds.persistence.liquibase;

import java.sql.Connection;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseConnection;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

/**
 *
 * @author Alexander Sahler <alexander.sahler at brodos.de>
 */
abstract public class AbstractLiquibaseMigrator implements LiquibaseMigrator {

    protected final String changesetResourceFileName;

    public AbstractLiquibaseMigrator(String changesetResourceFileName) {
        this.changesetResourceFileName = changesetResourceFileName;
    }

    /**
     * Actually perform database changes based on config file.
     *
     * @param connection
     * @throws LiquibaseException
     */
    protected void prepare(Connection connection) throws LiquibaseException {
        DatabaseConnection databaseConnection = new JdbcConnection(connection);
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(databaseConnection);
        ClassLoader classLoader = this.getClass().getClassLoader();
        ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(classLoader);
        Liquibase liquibase = new Liquibase(changesetResourceFileName, resourceAccessor, database);
        liquibase.update(new Contexts());
    }


}
