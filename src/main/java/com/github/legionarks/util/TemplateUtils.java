package com.github.legionarks.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.quarkus.qute.TemplateExtension;

@TemplateExtension(namespace = "utils")
public class TemplateUtils {

    public static <T> List<T> subList(Set<T> set, int size) {
        return new ArrayList<>(set).subList(0, size);
    }

    public static int diff(int number, int with) {
        return number - with;
    }

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
