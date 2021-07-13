package com.github.legionarks.util;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateExtension;
import io.quarkus.qute.TemplateInstance;

@CheckedTemplate
public class Templates {

    public static native TemplateInstance index();

    public static native TemplateInstance info();

    public static native TemplateInstance properties();

    public static native TemplateInstance search();

    public static native TemplateInstance about();

    public static native TemplateInstance contact();

    public static native TemplateInstance error();

    @TemplateExtension(namespace = "utils")
    public static class Utils {

        public static int[] range(int page, int total, int range) {
            final int[] pages;

            int start = page - (range / 2);
            int end = page + (range / 2);

            if (end > total) {
                start -= (end - total);
                end = total;
            }

            if (start <= 0) {
                end += ((start - 1) * (-1));
                start = 1;
            }

            end = end > total ? total : end;

            pages = new int[end - start + 1];

            for (int index = 0, actual = start; actual <= end; index++, actual++) {
                pages[index] = actual;
            }

            return pages;
        }
    }
}
