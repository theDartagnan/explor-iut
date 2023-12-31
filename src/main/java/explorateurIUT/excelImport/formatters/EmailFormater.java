/*
 * Copyright (C) 2023 IUT Laval - Le Mans Université.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package explorateurIUT.excelImport.formatters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Remi Venant
 */
public class EmailFormater {

    private final static Pattern EMAIL_PATTERN = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", Pattern.CASE_INSENSITIVE);
    private final static Pattern EMAIL_PATTERN_ALT = Pattern.compile("^(.+?)\\s+\\[à\\]\\s+(.+?)$");
    private final static Pattern EMAIL_PATTERN_URL = Pattern.compile("^mailto:(?!\\?to=)?(?:([^<,\\?&]+(?!<))|[^<]+<([^>]+)>)(?:&.*)?");

    public static String matchesAndRetrieve(String text) {
        if (text == null) {
            return null;
        }
        final String trimedText = text.trim();
        String candidate = matchAndRetrieveStandardPattern(trimedText);
        if (candidate != null) {
            return candidate;
        }
        candidate = matchAndRetrieveAlternativePattern(trimedText);
        if (candidate != null) {
            return candidate;
        }
        candidate = matchAndRetrieveURLStyleMail(trimedText);
        if (candidate != null) {
            return candidate;
        }
        return null;
    }

    private static String matchAndRetrieveStandardPattern(String text) {
        final Matcher m = EMAIL_PATTERN.matcher(text);
        if (!m.matches()) {
            return null;
        }
        return text;
    }

    private static String matchAndRetrieveAlternativePattern(String text) {
        final Matcher m = EMAIL_PATTERN_ALT.matcher(text);
        if (!m.matches()) {
            return null;
        }
        if (m.group(1) == null || m.group(2) == null) {
            return null;
        }
        return String.format("%s@%s", m.group(1), m.group(2));
    }

    private static String matchAndRetrieveURLStyleMail(String text) {
        final Matcher m = EMAIL_PATTERN_URL.matcher(text);
        if (!m.matches()) {
            return null;
        }
        if (m.group(1) != null) {
            return m.group(1);
        }
        return m.group(2);
    }

}
