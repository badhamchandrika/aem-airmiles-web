package com.aem.airmiles.web.core.models;

import com.adobe.cq.dam.cfm.ContentFragment;
import com.aem.airmiles.web.core.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Map;

import static com.adobe.cq.wcm.core.components.models.contentfragment.ContentFragment.PN_PATH;
import static org.apache.sling.models.annotations.DefaultInjectionStrategy.OPTIONAL;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = OPTIONAL)
public class QuickLinksModel {

    private static final Logger LOG = LoggerFactory.getLogger(QuickLinksModel.class);

    @Inject
    ResourceResolver resourceResolver;

    @Getter
    @Setter
    @ValueMapValue(name = PN_PATH)
    private String fragmentPath;

    @Getter
    @Setter
    private QuickLinksDetailsModel quickLinksDetailsModel;

    @PostConstruct
    public void init() throws JsonProcessingException {
        if(null == resourceResolver){
            LOG.error("Unable to get resource resolver in QuickLinksModel.");
            return;
        }

        if (StringUtils.isNotEmpty(fragmentPath)) {
            final Resource fragmentResource = resourceResolver.getResource(fragmentPath);
            if (fragmentResource != null) {
                final ContentFragment contentFragment = fragmentResource.adaptTo(ContentFragment.class);
                if (null != contentFragment) {
                    final Map<String, Object> elementMap = Utils.getElements(contentFragment);
                    final ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                    quickLinksDetailsModel = objectMapper.convertValue(elementMap, QuickLinksDetailsModel.class);
                    LOG.info("QuickLinks:: {}", quickLinksDetailsModel.getQuicklinks());
                }
            }
        }
    }
}