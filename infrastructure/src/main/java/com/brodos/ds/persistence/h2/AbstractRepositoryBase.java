/*
 * Copyright (C) Brodos AG - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package com.brodos.ds.persistence.h2;

import javax.persistence.EntityManager;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.transaction.control.TransactionControl;
import org.osgi.service.transaction.control.jpa.JPAEntityManagerProvider;

/**
 *
 * @author Alexander Sahler <alexander.sahler at brodos.de>
 */
abstract public class AbstractRepositoryBase {

    private JPAEntityManagerProvider provider;
    private EntityManager em;
    private TransactionControl txControl;

    @Reference(target = "(osgi.unit.name=DSContext)")
    public void setProviderFactory(JPAEntityManagerProvider provider) {
        this.provider = provider;
    }

    @Reference
    public void setTxControl(TransactionControl txControl) {
        this.txControl = txControl;
    }

    @Activate
    void start() {
        em = provider.getResource(txControl);
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}
