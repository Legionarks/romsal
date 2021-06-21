package com.github.legionarks.util;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

@CheckedTemplate
public class Templates {

    public static native TemplateInstance index();

    public static native TemplateInstance info();

    public static native TemplateInstance property();

    public static native TemplateInstance search();

    public static native TemplateInstance about();

    public static native TemplateInstance contact();

}
