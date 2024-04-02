package com.aem.airmiles.web.core.models;

import com.adobe.cq.export.json.ExporterConstants;
import lombok.Getter;
import lombok.Setter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class AdBannersModel {

    @ValueMapValue
    @Getter
    @Setter
    private boolean adSlotEnabled;

    @ValueMapValue
    @Getter
    @Setter
    private String adID;

    @ValueMapValue
    @Getter
    @Setter
    private String id;

    @ValueMapValue
    @Getter
    @Setter
    private boolean removeStyles;

    @ValueMapValue
    @Getter
    @Setter
    private boolean enableGoogleAdSense;

    @Setter
    @Getter
    @ChildResource(name = "adDetails")
    List<AdBanners> adDetails;
}
