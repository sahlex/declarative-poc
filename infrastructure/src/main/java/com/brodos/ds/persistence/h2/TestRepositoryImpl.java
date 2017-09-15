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
package com.brodos.ds.persistence.h2;

import com.brodos.ds.domain.entity.Article;
import com.brodos.ds.domain.persistence.TestRepository;
import java.util.List;
import javax.persistence.TypedQuery;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.transaction.control.TransactionControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alexander Sahler <alexander.sahler at brodos.de>
 */
@Component(service = TestRepository.class, immediate = true, scope = ServiceScope.SINGLETON)
public class TestRepositoryImpl extends AbstractRepositoryBase implements TestRepository {

    private static final Logger LOG = LoggerFactory.getLogger(TestRepositoryImpl.class);

    @Reference
    TransactionControl txControl;

    @Override
    public Article findArticleById(String id) {
        return em.find(Article.class, id);
    }

    @Override
    public List<Article> getAllArticles() {
        TypedQuery<Article> q = em.createQuery("from Article a", Article.class);
        return q.getResultList();
    }

    @Override
    public Article store(Article article) {
        em.persist(article);
        em.flush();

        return article;
    }

}
