package com.github.legionarks.util;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

@CheckedTemplate
public class Templates {

    public static native TemplateInstance index();
}
