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

        public static int[] range(int page, int total) {
            final int size = 5;
            final int[] pages;

            int start = page - 2;
            int end = page + 2;

            end = end > size ? total : end;

            if (start < 1 || (end - start) < size) {
                start = 1;
            }

            pages = new int[end - start + 1];
            System.out.println("start: " + start + "\nend: " + end + "\ntotal: " + total + "\npage: " + page);
            for (int index = 0, actual = start; actual <= end; index++, actual++) {
                pages[index] = actual;
            }

            return pages;
        }
    }
}
