package com.aem.airmiles.web.core.models;

import com.adobe.cq.export.json.ExporterConstants;
import lombok.Getter;
import lombok.Setter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = { Resource.class,
        SlingHttpServletRequest.class },resourceType="aem-airmiles-web/components/airmiles-core/herobanner", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class HeroBannerModel {

    @Setter
    @Getter
    @ValueMapValue
    private String titleText;

    @Setter
    @Getter
    @ValueMapValue
    private String descriptionText;

    @Setter
    @Getter
    @ValueMapValue
    private String fileReference;

    @Setter
    @Getter
    @ValueMapValue
    private String imageAlt;

    @Setter
    @Getter
    @ChildResource
    private List<HeroBannerCTA> ctaButtons;
}

