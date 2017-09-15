/*
 * Copyright 2017 The Apache Software Foundation.
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
package com.brodos.ds.persistence.liquibase;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import liquibase.exception.LiquibaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alexander Sahler <alexander.sahler at brodos.de>
 */
public class DataSourceLiquibaseMigrator extends  AbstractLiquibaseMigrator {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceLiquibaseMigrator.class);
    protected DataSource dataSource;

    public DataSourceLiquibaseMigrator(DataSource dataSource, String changesetResourceFileName) {
        super(changesetResourceFileName);
        this.dataSource = dataSource;
    }

    @Override
    public void prepare() {
        try (Connection connection = dataSource.getConnection()) {
            prepare(connection);
        } catch (SQLException | LiquibaseException e) {
            LOG.error("Error migrating database structure to new version", e);
            throw new IllegalStateException(e);
        }
    }

}
