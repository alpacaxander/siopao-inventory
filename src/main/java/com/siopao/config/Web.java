package com.siopao.config;

import com.google.common.collect.ImmutableMap;
import com.yahoo.elide.Elide;
import com.yahoo.elide.ElideSettingsBuilder;
import com.yahoo.elide.core.*;
import com.yahoo.elide.core.filter.dialect.RSQLFilterDialect;
import com.yahoo.elide.spring.config.ElideConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Configuration
public class Web {

    @Bean
    public WebMvcConfigurer WebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("*");
            }
        };
    }

    @Bean
    public Elide initializeElide(EntityDictionary dictionary, DataStore dataStore, ElideConfigProperties settings) {
        ElideSettingsBuilder builder = new ElideSettingsBuilder(dataStore)
                .withUseFilterExpressions(true)
                .withEntityDictionary(dictionary)
                .withJSONApiLinks(
                        new JSONApiLinks() { // This is basically just DefaultJSONApiLinks but getResourceUrl finds the relation name instead of using just the type
                            @Override
                            public Map<String, String> getResourceLevelLinks(PersistentResource resource) {
                                return ImmutableMap.of("self", getResourceUrl(resource));
                            }

                            @Override
                            public Map<String, String> getRelationshipLinks(PersistentResource resource, String field) {
                                String resourceUrl = getResourceUrl(resource);
                                return ImmutableMap.of(
                                        "self", String.join("/", resourceUrl, "relationships", field),
                                        "related", String.join("/", resourceUrl, field));
                            }

                            private String getResourceUrl(PersistentResource resource) {

                                StringBuilder result = new StringBuilder();
                                if (resource.getRequestScope().getBaseUrlEndPoint() != null) {
                                    result.append(resource.getRequestScope().getBaseUrlEndPoint());
                                }

                                Iterator<PersistentResource> iterator = resource.getLineage().getResourcePath().iterator();
                                PersistentResource baseResource = iterator.next();

                                result.append(String.join("/", baseResource.getType(), baseResource.getId()));
                                result.append("/");

                                PersistentResource previousResource = baseResource;

                                while (true) {
                                    PersistentResource currentResource = iterator.next();

                                    List<String> relationships = dictionary.getRelationships(previousResource.getResourceClass());

                                    result.append(String.join(findRelationship(previousResource, currentResource)));
                                    result.append("/");

                                    previousResource = currentResource;
                                    if (!iterator.hasNext()) {
                                        result.append(findRelationship(previousResource, resource));
                                        break;
                                    }
                                }
                                return result.toString();
                            }

                            private String findRelationship(PersistentResource a, PersistentResource b) {
                                String result = null;
                                List<String> relationships = dictionary.getRelationships(a.getResourceClass());
                                for (String relationship: relationships) {
                                    if (dictionary.getParameterizedType(a.getResourceClass(), relationship) == b.getResourceClass()) {
                                        if (a.getRelationshipType(relationship).isToMany()) {
                                            result = String.join(
                                                    "/",
                                                    relationship,
                                                    b.getId()
                                            );
                                        } else {
                                            result = String.join(
                                                    "/",
                                                    relationship
                                            );
                                        }
                                    }
                                }
                                return result;
                            }
                        }
                )

                .withJoinFilterDialect(new RSQLFilterDialect(dictionary))
                .withSubqueryFilterDialect(new RSQLFilterDialect(dictionary));
        return new Elide(builder.build());
    }
}
