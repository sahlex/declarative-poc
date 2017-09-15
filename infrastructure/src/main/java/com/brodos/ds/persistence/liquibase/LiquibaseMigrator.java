/*
 * Copyright (C) Brodos AG - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package com.brodos.ds.persistence.liquibase;

/**
 *
 * @author Alexander Sahler <alexander.sahler at brodos.de>
 */
public interface LiquibaseMigrator {

    public void prepare();
}
