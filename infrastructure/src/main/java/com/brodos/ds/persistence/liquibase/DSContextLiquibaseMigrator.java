/*
 * Copyright (C) Brodos AG - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package com.brodos.ds.persistence.liquibase;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.ops4j.pax.jdbc.hook.PreHook;
import org.osgi.service.component.annotations.Component;

/**
 *
 * @author Alexander Sahler <alexander.sahler at brodos.de>
 */
@Component(property="name=DSContext", immediate = true)
public class DSContextLiquibaseMigrator implements PreHook {

    @Override
    public void prepare(DataSource ds) throws SQLException {
        DataSourceLiquibaseMigrator mig = new DataSourceLiquibaseMigrator(ds, "com/brodos/ds/persistence/liquibase/changesets.xml");
        mig.prepare();
    }

}
