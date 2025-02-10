package com.api.helper;

import java.util.logging.Logger;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.*;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;


import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

public class CommonUtils
{
    private static final Logger LOGGER = Logger.getLogger(CommonUtils.class.getName());
    public static void runTests(String tag)
    {
        LOGGER.info("Running Parallel Runner with tags : " + tag);
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .filters(EngineFilter.includeEngines("cucumber"))
                .filters(TagFilter.includeTags(tag))
                .selectors(DiscoverySelectors.selectClasspathResource("Features"))
                .configurationParameter(GLUE_PROPERTY_NAME, "com/api/stepdefinition")
                .configurationParameter(PLUGIN_PROPERTY_NAME, "pretty, html:target/cucumber-report/report.html")
                .build();
        Launcher launcher = LauncherFactory.create();
        launcher.execute(request);
    }
}
