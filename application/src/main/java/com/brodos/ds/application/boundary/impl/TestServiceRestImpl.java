package com.brodos.ds.application.boundary.impl;

import com.brodos.ds.application.boundary.TestServiceRest;
import com.brodos.ds.domain.entity.Article;
import com.brodos.ds.domain.service.TestService;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import java.net.URI;
import static java.util.Arrays.asList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import org.apache.cxf.dosgi.common.api.IntentsProvider;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author saalexander
 */
@Component(service = TestServiceRest.class, immediate = true,
        property = {
            "service.exported.interfaces=*",
            "service.exported.configs=org.apache.cxf.rs",
            "org.apache.cxf.rs.address=/" //
        })
public class TestServiceRestImpl implements TestServiceRest, IntentsProvider {

    static Logger LOG = LoggerFactory.getLogger(TestServiceRestImpl.class);

    @Reference
    TestService service;

    public TestServiceRestImpl() {
        LOG.info("TestServiceFacadeImpl created");
    }

    @Override
    public List<?> getIntents() {
        return asList(new JacksonJaxbJsonProvider());
    }

    @PostConstruct
    public void onActivate() {
        LOG.info("TestServiceFacadeImpl init-method");
    }

    @Override
    public Response createNewArticle(Article article, UriInfo uri) {

        service.createNewArticle(article);
        URI articleURI = uri.getRequestUriBuilder().path(TestServiceRest.class, "getArticle").build(article.getId());
        return Response.created(articleURI).build();
    }

    @Override
    public Response getArticle(String id) {
        try {
            // send to producer
            Article article = service.findArticle(id);
            return article == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(article).build();
        } catch (Exception ex) {
            throw new WebApplicationException(ex);
        }
    }

    public void setService(TestService service) {
        this.service = service;
    }

    public TestService getService() {
        return service;
    }

}
