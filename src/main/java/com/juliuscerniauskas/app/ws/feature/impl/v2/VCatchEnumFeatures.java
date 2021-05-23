package com.juliuscerniauskas.app.ws.feature.impl.v2;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum VCatchEnumFeatures implements Feature {

    @EnabledByDefault
    @Label("First feature")
    FEATURE_ONE,

    @Label("Second feature")
    FEATURE_TWO;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }

}


