/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brodos.ds.service.impl;

import com.brodos.ds.domain.entity.Article;
import com.brodos.ds.domain.persistence.TestRepository;
import com.brodos.ds.domain.service.TestService;
import java.util.List;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.transaction.control.TransactionControl;

/**
 *
 * @author saalexander
 */
@Component(service = TestService.class, immediate = true)
public class TestServiceImpl implements TestService {

    @Reference
    private TestRepository repo;
    @Reference
    private TransactionControl txControl;

    @Override
    public void createNewArticle(Article article) {
        txControl.build()
                .required(
                        () -> repo.store(article));
    }

    @Override
    public Article findArticle(String id) {
        return txControl
                .build()
//                .readOnly()
                .required(
                        () -> repo.findArticleById(id));
    }

    @Override
    public List<Article> getAllArticles() {
        return txControl
                .build()
//                .readOnly()
                .required(
                        () -> repo.getAllArticles());
    }

}
