/*
 * Copyright (C) Brodos AG - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package com.brodos.ds.persistence.h2;

import java.util.HashMap;
import javax.persistence.EntityManager;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jpa.EntityManagerFactoryBuilder;
import org.osgi.service.transaction.control.TransactionControl;
import org.osgi.service.transaction.control.jpa.JPAEntityManagerProviderFactory;

/**
 *
 * @author Alexander Sahler <alexander.sahler at brodos.de>
 */
abstract public class AbstractRepositoryBase {

    protected JPAEntityManagerProviderFactory providerFactory;
    protected EntityManagerFactoryBuilder emfBuilder;
    protected EntityManager em;
    private TransactionControl txControl;

//    @Reference(target = "(osgi.unit.name=DSContext)")
//    public void setEmf(EntityManagerFactory emf) {
//        this.emf = emf;
//    }

    @Reference
    public void setProviderFactory(JPAEntityManagerProviderFactory providerFactory) {
        this.providerFactory = providerFactory;
    }

    @Reference(target = "(osgi.unit.name=DSContext)")
    public void setProviderManagerFactoryBuilder(EntityManagerFactoryBuilder emfBuilder) {
        this.emfBuilder = emfBuilder;
    }

    @Reference
    public void setTxControl(TransactionControl txControl) {
        this.txControl = txControl;
    }

    @Activate
    void start() {
//        em = providerFactory
        em = providerFactory.getProviderFor(emfBuilder, new HashMap<>(), null).getResource(txControl);
//        txControl.getCurrentContext().
        // em = providerFactory.getProviderFor(emf, null).getResource(txControl);
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}
