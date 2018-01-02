package com.hdi.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * string公用类
 * @author wanghuidong
 * @date 2017/12/27.
 * @version 1.0
 */
public class StringUtil{
    public static final String sdf_time_pattern = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat sdf_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat sdf_timeonly = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat sdf_time_digit = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat sdf_time_yyyyMM = new SimpleDateFormat("yyyyMM");
    public static final SimpleDateFormat sdf_date_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
    public static final String df_number_pattern = "#.#";
    public static final DecimalFormat df = new DecimalFormat("#.#");
    public static Integer PAGE_START = Integer.valueOf(0);
    public static Integer PAGE_NUM = Integer.valueOf(10);
    public static Integer MAX_PAGE_NUM = Integer.valueOf(100);
    public static final String CHARSET = "UTF-8";
    public static final boolean DEFAULT_BOOLEAN = false;
    public static final boolean[] DEFAULT_BOOLEAN_VALUES = new boolean[0];
    public static final Double DEFAULT_DOUBLE = null;
    public static final Double[] DEFAULT_DOUBLE_VALUES = new Double[0];
    public static final Float DEFAULT_FLOAT = null;
    public static final Float[] DEFAULT_FLOAT_VALUES = new Float[0];
    public static final Integer DEFAULT_INTEGER = null;
    public static final Integer[] DEFAULT_INTEGER_VALUES = new Integer[0];
    public static final Long DEFAULT_LONG = null;
    public static final Long[] DEFAULT_LONG_VALUES = new Long[0];
    public static final Short DEFAULT_SHORT = null;
    public static final Short[] DEFAULT_SHORT_VALUES = new Short[0];
    public static final String BLANK = "";
    public static String[] BOOLEANS = new String[]{"true", "t", "y", "on", "1"};
    private static final String[] hex = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E", "7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F", "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF", "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF"};
    private static final byte[] val = new byte[]{(byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)0, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5, (byte)6, (byte)7, (byte)8, (byte)9, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)10, (byte)11, (byte)12, (byte)13, (byte)14, (byte)15, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)10, (byte)11, (byte)12, (byte)13, (byte)14, (byte)15, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63, (byte)63};
    public static final Integer CURRENT_YEAR = Integer.valueOf(Calendar.getInstance().get(1));
    public static final Integer CURRENT_MONTH = Integer.valueOf(Calendar.getInstance().get(2) + 1);
    public static final Integer CURRENT_DATE = Integer.valueOf(Calendar.getInstance().get(5));
    private static final String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public StringUtil() {
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
    }

    public static boolean emptyOrNull(String str) {
        return empty(str) || "null".equals(str);
    }

    public static boolean empty(String str) {
        return str == null || "".equals(str);
    }

    public static String formatTimeOnlyDigit(Date time) {
        return sdf_time_digit.format(time);
    }

    public static String formatTime(Date time) {
        return sdf_time.format(time);
    }

    public static String formatDate(Date date) {
        return sdf_date.format(date);
    }

    public static String print(Object[] c) {
        return isEmpty(c)?"":concatWithDelimiter(c, ",", "[", "]", "");
    }

    public static String print(byte[] c) {
        return isEmpty((Object)c)?"":concatWithDelimiter(c, ",", "[", "]", "");
    }

    public static String print(Collection c) {
        return isEmpty(c)?"":print(c.toArray(new Object[0]));
    }

    public static ArrayList<String> methodArrayToStringArrayList(Method[] s) {
        if(isEmpty((Object[])s)) {
            return null;
        } else {
            ArrayList a = new ArrayList();

            try {
                Method[] e = s;
                int len$ = s.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Method i = e[i$];
                    a.add(String.valueOf(i.getName()));
                }
            } catch (NumberFormatException var6) {
                var6.printStackTrace();
            }

            return a;
        }
    }

    public static ArrayList<String> objectArrayToStringArrayList(Object[] s) {
        if(isEmpty(s)) {
            return null;
        } else {
            ArrayList a = new ArrayList();

            try {
                Object[] e = s;
                int len$ = s.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Object i = e[i$];
                    a.add(String.valueOf(i));
                }
            } catch (NumberFormatException var6) {
                var6.printStackTrace();
            }

            return a;
        }
    }

    public static ArrayList<Integer> objectArrayToIntegerArrayList(Object[] s) {
        if(isEmpty(s)) {
            return null;
        } else {
            ArrayList a = new ArrayList();

            try {
                Object[] e = s;
                int len$ = s.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Object i = e[i$];
                    a.add(Integer.valueOf(Integer.parseInt(String.valueOf(i))));
                }
            } catch (NumberFormatException var6) {
                var6.printStackTrace();
            }

            return a;
        }
    }

    public static Integer[] objectArrayToIntegerArray(Object[] s) {
        return (Integer[])objectArrayToIntegerArrayList(s).toArray(new Integer[0]);
    }

    public static ArrayList<Integer> stringArrayToIntegerArrayList(String[] s) {
        if(isEmpty((Object[])s)) {
            return null;
        } else {
            ArrayList a = new ArrayList();

            try {
                String[] e = s;
                int len$ = s.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    String i = e[i$];
                    a.add(Integer.valueOf(Integer.parseInt(i)));
                }
            } catch (NumberFormatException var6) {
                var6.printStackTrace();
            }

            return a;
        }
    }

    public static Integer[] stringArrayToIntegerArray(String[] s) {
        return (Integer[])stringArrayToIntegerArrayList(s).toArray(new Integer[0]);
    }

    public static ArrayList<Long> stringArrayToLongArrayList(String[] s) {
        if(isEmpty((Object[])s)) {
            return null;
        } else {
            ArrayList a = new ArrayList();

            try {
                String[] e = s;
                int len$ = s.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    String i = e[i$];
                    a.add(Long.valueOf(Long.parseLong(i)));
                }
            } catch (NumberFormatException var6) {
                var6.printStackTrace();
            }

            return a;
        }
    }

    public static Long[] stringArrayToLongArray(String[] s) {
        return (Long[])stringArrayToLongArrayList(s).toArray(new Long[0]);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || "".equals(trim_(str)) || "undefined".equalsIgnoreCase(trim_(str)) || "null".equalsIgnoreCase(trim_(str));
    }

    public static boolean isEmpty(Object obj) {
        return obj == null?true:(obj instanceof String?isEmpty((String)obj):(obj instanceof Collection?isEmpty((Collection)obj):(obj instanceof Object[]?isEmpty((Object[])((Object[])obj)):(obj instanceof Map ?isEmpty((Map)obj):(obj instanceof StringBuffer?isEmpty((StringBuffer)obj):false)))));
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isEmpty(Collection c) {
        return c == null || c.size() == 0 || c.size() == 1 && c.toArray()[0] == null;
    }

    public static boolean isEmpty(Map m) {
        return m == null || m.size() == 0;
    }

    public static boolean isEmpty(Object[] c) {
        return c == null || c.length == 0 || c.length == 1 && c[0] == null;
    }

    public static String trim(String str) {
        String dest = "";
        if(str != null) {
            dest = str.replaceAll("[ |　]", " ").trim();
            return dest;
        } else {
            return null;
        }
    }

    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts)?null:ts;
    }

    public static boolean isNotEmpty(Collection c) {
        return !isEmpty(c);
    }

    public static boolean isNotEmpty(Object[] c) {
        return !isEmpty(c);
    }

    public static String concatWithDelimiter(Object[] objectIds, String delimiter, String beginDelimiter, String endDelimiter, String defaultValue) {
        if(isEmpty(objectIds)) {
            return defaultValue;
        } else {
            StringBuffer s = new StringBuffer(beginDelimiter != null?beginDelimiter:"");
            Object[] arr$ = objectIds;
            int len$ = objectIds.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object id = arr$[i$];
                s.append(id).append(delimiter);
            }

            return endDelimiter == null?s.toString().substring(0, s.toString().length() - 1):s.toString().substring(0, s.toString().length() - 1) + endDelimiter;
        }
    }

    public static String concatWithDelimiter(byte[] objectIds, String delimiter, String beginDelimiter, String endDelimiter, String defaultValue) {
        if(isEmpty((Object)objectIds)) {
            return defaultValue;
        } else {
            StringBuffer s = new StringBuffer(beginDelimiter != null?beginDelimiter:"");
            byte[] arr$ = objectIds;
            int len$ = objectIds.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                byte id = arr$[i$];
                s.append(id).append(delimiter);
            }

            return endDelimiter == null?s.toString().substring(0, s.toString().length() - 1):s.toString().substring(0, s.toString().length() - 1) + endDelimiter;
        }
    }

    public static String concatWithComma(Object[] objectIds) {
        return concatWithDelimiter((Object[])objectIds, ",", (String)null, (String)null, (String)null);
    }

    public static String concatWithCommaEmbraced(Object[] objectIds) {
        return concatWithDelimiter((Object[])objectIds, ",", ",", ",", (String)null);
    }

    public static String concatWithComma(Collection objectIds) {
        return concatWithDelimiter((Collection)objectIds, ",", (String)null, (String)null, (String)null);
    }

    public static String concatWithCommaEmbraced(Collection objectIds) {
        return concatWithDelimiter((Collection)objectIds, ",", ",", ",", (String)null);
    }

    public static String concatWithDelimiter(Collection c, String delimiter, String beginDelimiter, String endDelimiter, String defaultValue) {
        return c != null && !c.isEmpty()?concatWithDelimiter(c.toArray(), delimiter, beginDelimiter, endDelimiter, defaultValue):defaultValue;
    }

    public static boolean getBoolean(String value) {
        return getBoolean(value, false);
    }

    public static boolean getBoolean(String value, boolean defaultValue) {
        return get(value, defaultValue);
    }

    public static boolean[] getBooleanValues(String[] values) {
        return getBooleanValues(values, DEFAULT_BOOLEAN_VALUES);
    }

    public static boolean[] getBooleanValues(String[] values, boolean[] defaultValue) {
        if(isEmpty((Object[])values)) {
            return defaultValue;
        } else {
            boolean[] booleanValues = new boolean[values.length];

            for(int i = 0; i < values.length; ++i) {
                booleanValues[i] = getBoolean(values[i]);
            }

            return booleanValues;
        }
    }

    public static Date getDate(String value, DateFormat df) {
        return getDate(value, df, new Date());
    }

    public static Date getDate(String value, DateFormat df, Date defaultValue) {
        return get(value, df, defaultValue);
    }

    public static Double getDouble(String value) {
        return getDouble(value, DEFAULT_DOUBLE);
    }

    public static Double getDouble(String value, Double defaultValue) {
        return get(value, defaultValue);
    }

    public static Double[] getDoubleValues(String[] values) {
        return getDoubleValues(values, DEFAULT_DOUBLE_VALUES);
    }

    public static Double[] getDoubleValues(String[] values, Double[] defaultValue) {
        if(isEmpty((Object[])values)) {
            return defaultValue;
        } else {
            Double[] DoubleValues = new Double[values.length];

            for(int i = 0; i < values.length; ++i) {
                DoubleValues[i] = getDouble(values[i]);
            }

            return DoubleValues;
        }
    }

    public static Float getFloat(String value) {
        return getFloat(value, DEFAULT_FLOAT);
    }

    public static Float getFloat(String value, Float defaultValue) {
        return get(value, defaultValue);
    }

    public static Float[] getFloatValues(String[] values) {
        return getFloatValues(values, DEFAULT_FLOAT_VALUES);
    }

    public static Float[] getFloatValues(String[] values, Float[] defaultValue) {
        if(isEmpty((Object[])values)) {
            return defaultValue;
        } else {
            Float[] FloatValues = new Float[values.length];

            for(int i = 0; i < values.length; ++i) {
                FloatValues[i] = getFloat(values[i]);
            }

            return FloatValues;
        }
    }

    public static Integer getInteger(String value) {
        return getInteger(value, DEFAULT_INTEGER);
    }

    public static Integer getInteger(String value, Integer defaultValue) {
        return get(value, defaultValue);
    }

    public static Long getLong(String value) {
        return getLong(value, DEFAULT_LONG);
    }

    public static Long getLong(String value, Long defaultValue) {
        return get(value, defaultValue);
    }

    public static Long[] getLongValues(String[] values) {
        return getLongValues(values, DEFAULT_LONG_VALUES);
    }

    public static Long[] getLongValues(String[] values, Long[] defaultValue) {
        if(isEmpty((Object[])values)) {
            return defaultValue;
        } else {
            Long[] LongValues = new Long[values.length];

            for(int i = 0; i < values.length; ++i) {
                LongValues[i] = getLong(values[i]);
            }

            return LongValues;
        }
    }

    public static Short getShort(String value) {
        return getShort(value, DEFAULT_SHORT);
    }

    public static Short getShort(String value, Short defaultValue) {
        return get(value, defaultValue);
    }

    public static Short[] getShortValues(String[] values) {
        return getShortValues(values, DEFAULT_SHORT_VALUES);
    }

    public static Short[] getShortValues(String[] values, Short[] defaultValue) {
        if(isEmpty((Object[])values)) {
            return defaultValue;
        } else {
            Short[] ShortValues = new Short[values.length];

            for(int i = 0; i < values.length; ++i) {
                ShortValues[i] = getShort(values[i]);
            }

            return ShortValues;
        }
    }

    public static String getString(Object value) {
        return getString(value, "");
    }

    public static String getString(Object value, String defaultValue) {
        return get(value, defaultValue);
    }

    public static String[] getStringValues(Object[] values) {
        if(isEmpty(values)) {
            return null;
        } else {
            String[] stringValues = new String[values.length];

            for(int i = 0; i < values.length; ++i) {
                stringValues[i] = getString(stringValues[i]);
            }

            return stringValues;
        }
    }

    public static boolean getBoolean(Object value) {
        return getBoolean(toString(value), false);
    }

    public static boolean getBoolean(Object value, boolean defaultValue) {
        return get(toString(value), defaultValue);
    }

    public static boolean[] getBooleanValues(Object[] values) {
        return getBooleanValues(values, DEFAULT_BOOLEAN_VALUES);
    }

    public static boolean[] getBooleanValues(Object[] values, boolean[] defaultValue) {
        if(isEmpty(values)) {
            return defaultValue;
        } else {
            boolean[] booleanValues = new boolean[values.length];

            for(int i = 0; i < values.length; ++i) {
                booleanValues[i] = getBoolean(values[i]);
            }

            return booleanValues;
        }
    }

    public static Date getDate(Object value, DateFormat df) {
        return getDate(toString(value), df, new Date());
    }

    public static Date getDate(Object value, DateFormat df, Date defaultValue) {
        return get(toString(value), df, defaultValue);
    }

    public static Double getDouble(Object value) {
        return getDouble(toString(value), DEFAULT_DOUBLE);
    }

    public static Double getDouble(Object value, Double defaultValue) {
        return get(toString(value), defaultValue);
    }

    public static Double[] getDoubleValues(Object[] values) {
        return getDoubleValues(values, DEFAULT_DOUBLE_VALUES);
    }

    public static Double[] getDoubleValues(Object[] values, Double[] defaultValue) {
        if(isEmpty(values)) {
            return defaultValue;
        } else {
            Double[] DoubleValues = new Double[values.length];

            for(int i = 0; i < values.length; ++i) {
                DoubleValues[i] = getDouble(values[i]);
            }

            return DoubleValues;
        }
    }

    public static Float getFloat(Object value) {
        return getFloat(toString(value), DEFAULT_FLOAT);
    }

    public static Float getFloat(Object value, Float defaultValue) {
        return get(toString(value), defaultValue);
    }

    public static Float[] getFloatValues(Object[] values) {
        return getFloatValues(values, DEFAULT_FLOAT_VALUES);
    }

    public static Float[] getFloatValues(Object[] values, Float[] defaultValue) {
        if(isEmpty(values)) {
            return defaultValue;
        } else {
            Float[] FloatValues = new Float[values.length];

            for(int i = 0; i < values.length; ++i) {
                FloatValues[i] = getFloat(values[i]);
            }

            return FloatValues;
        }
    }

    public static Integer getInteger(Object value) {
        return getInteger(toString(value), DEFAULT_INTEGER);
    }

    public static Integer getInteger(Object value, Integer defaultValue) {
        return get(toString(value), defaultValue);
    }

    public static Integer[] getIntegerValues(Object[] values) {
        return getIntegerValues(values, DEFAULT_INTEGER_VALUES);
    }

    public static Integer[] getIntegerValues(Object[] values, Integer[] defaultValue) {
        if(isEmpty(values)) {
            return defaultValue;
        } else {
            Integer[] intValues = new Integer[values.length];

            for(int i = 0; i < values.length; ++i) {
                intValues[i] = getInteger(values[i]);
            }

            return intValues;
        }
    }

    public static Long getLong(Object value) {
        return getLong(toString(value), DEFAULT_LONG);
    }

    public static Long getLong(Object value, Long defaultValue) {
        return get(toString(value), defaultValue);
    }

    public static Long[] getLongValues(Object[] values) {
        return getLongValues(values, DEFAULT_LONG_VALUES);
    }

    public static Long[] getLongValues(Object[] values, Long[] defaultValue) {
        if(isEmpty(values)) {
            return defaultValue;
        } else {
            Long[] LongValues = new Long[values.length];

            for(int i = 0; i < values.length; ++i) {
                LongValues[i] = getLong(values[i]);
            }

            return LongValues;
        }
    }

    public static Short getShort(Object value) {
        return getShort(toString(value), DEFAULT_SHORT);
    }

    public static Short getShort(Object value, Short defaultValue) {
        return get(toString(value), defaultValue);
    }

    public static Short[] getShortValues(Object[] values) {
        return getShortValues(values, DEFAULT_SHORT_VALUES);
    }

    public static Short[] getShortValues(Object[] values, Short[] defaultValue) {
        if(isEmpty(values)) {
            return defaultValue;
        } else {
            Short[] ShortValues = new Short[values.length];

            for(int i = 0; i < values.length; ++i) {
                ShortValues[i] = getShort(values[i]);
            }

            return ShortValues;
        }
    }

    private static boolean get(String value, boolean defaultValue) {
        if(value != null) {
            try {
                value = trim_(value);
                if(!value.equalsIgnoreCase(BOOLEANS[0]) && !value.equalsIgnoreCase(BOOLEANS[1]) && !value.equalsIgnoreCase(BOOLEANS[2]) && !value.equalsIgnoreCase(BOOLEANS[3]) && !value.equalsIgnoreCase(BOOLEANS[4])) {
                    return false;
                }

                return true;
            } catch (Exception var3) {
                ;
            }
        }

        return defaultValue;
    }

    private static Date get(String value, DateFormat df, Date defaultValue) {
        try {
            Date date = df.parse(trim_(value));
            if(date != null) {
                return date;
            }
        } catch (Exception var4) {
            ;
        }

        return defaultValue;
    }

    private static Double get(String value, Double defaultValue) {
        try {
            return Double.valueOf(Double.parseDouble(trim_(value)));
        } catch (Exception var3) {
            return defaultValue;
        }
    }

    private static Float get(String value, Float defaultValue) {
        try {
            return Float.valueOf(Float.parseFloat(trim_(value)));
        } catch (Exception var3) {
            return defaultValue;
        }
    }

    private static Integer get(String value, Integer defaultValue) {
        try {
            return Integer.valueOf(Integer.parseInt(trim_(value)));
        } catch (Exception var3) {
            return defaultValue;
        }
    }

    private static Long get(String value, Long defaultValue) {
        try {
            return Long.valueOf(Long.parseLong(trim_(value)));
        } catch (Exception var3) {
            return defaultValue;
        }
    }

    private static Short get(String value, Short defaultValue) {
        try {
            return Short.valueOf(Short.parseShort(trim_(value)));
        } catch (Exception var3) {
            return defaultValue;
        }
    }

    private static String get(Object value, String defaultValue) {
        String strvalue = toString(value);
        return isNotEmpty(value)?(value instanceof Timestamp ?(new SimpleDateFormat("yyyy-MM-dd")).format((Timestamp)value):(value instanceof Date?(new SimpleDateFormat("yyyy-MM-dd")).format((Date)value):(!"null".equals(strvalue) && !"undefined".equals(strvalue)?trim_(strvalue):null))):defaultValue;
    }

    private static String trim_(String str) {
        return str != null?trim(str):"";
    }

    public static String camelToUnderline(String param) {
        Pattern p = Pattern.compile("[A-Z]");
        if(param != null && !param.equals("")) {
            StringBuilder builder = new StringBuilder(param);
            Matcher mc = p.matcher(param);

            for(int i = 0; mc.find(); ++i) {
                builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
            }

            if(95 == builder.charAt(0)) {
                builder.deleteCharAt(0);
            }

            return builder.toString();
        } else {
            return "";
        }
    }

    public static String underlineToCamel(String param) {
        Pattern p = Pattern.compile("[a-z]");
        if(param != null && !param.equals("")) {
            StringBuilder builder = new StringBuilder(param);
            Matcher mc = p.matcher(param);

            for(int i = 0; mc.find(); ++i) {
                builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
            }

            if(95 == builder.charAt(0)) {
                builder.deleteCharAt(0);
            }

            return builder.toString();
        } else {
            return "";
        }
    }

    public static String toStringDefaultNull(Object obj) {
        String result = toString(obj);
        return result.equals("")?null:result;
    }

    public static String toString(Object obj) {
        if(isEmpty(obj)) {
            return "";
        } else {
            try {
                if(obj instanceof String) {
                    return (String)obj;
                } else if(obj instanceof Date) {
                    SimpleDateFormat e1 = new SimpleDateFormat();
                    e1.applyPattern("yyyy-MM-dd");
                    return e1.format(obj);
                } else if(obj instanceof BigDecimal) {
                    String e = ((BigDecimal)obj).stripTrailingZeros().toPlainString();
                    return ((BigDecimal)obj).compareTo(new BigDecimal(e)) == 0?e:((BigDecimal)obj).toPlainString();
                } else {
                    return obj.toString();
                }
            } catch (Exception var2) {
                return "";
            }
        }
    }

    public static String fmtNum(BigDecimal amount) {
        String result = "";
        if(isNotEmpty((Object)amount)) {
            DecimalFormat df = new DecimalFormat();
            df.applyPattern("0,000");
            String number = amount.toString();
            if(number.length() > 3) {
                result = df.format(Double.parseDouble(number));
            } else {
                result = number;
            }

            if(!result.contains(".")) {
                result = result + ".00";
            }
        } else {
            result = "";
        }

        return result;
    }

    public static int chineseLength(String value) {
        int valueLength = 0;
        String chinese = "[Α-￥]";
        if(!isEmpty(value)) {
            for(int i = 0; i < value.length(); ++i) {
                String temp = value.substring(i, i + 1);
                if(temp.matches(chinese)) {
                    valueLength += 2;
                } else {
                    ++valueLength;
                }
            }
        }

        return valueLength;
    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();

        for(int i = 0; i < ch.length; ++i) {
            char c = ch[i];
            if(isChinese(c)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isChineseByREG(String str) {
        if(str == null) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
            return pattern.matcher(str.trim()).find();
        }
    }

    public static boolean isChineseByName(String str) {
        if(str == null) {
            return false;
        } else {
            String reg = "\\p{InCJK Unified Ideographs}&&\\P{Cn}";
            Pattern pattern = Pattern.compile(reg);
            return pattern.matcher(str.trim()).find();
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("\\d*");
    }

    public static boolean isNumeric(char c) {
        return isNumeric(c + "");
    }

    public static boolean isMatch(String regex, String orginal) {
        if(orginal != null && !orginal.trim().equals("")) {
            Pattern pattern = Pattern.compile(regex);
            Matcher isNum = pattern.matcher(orginal);
            return isNum.matches();
        } else {
            return false;
        }
    }

    public static boolean isPositiveInteger(String orginal) {
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
    }

    public static boolean isNegativeInteger(String orginal) {
        return isMatch("^-[1-9]\\d*", orginal);
    }

    public static boolean isInteger(String orginal) {
        return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal) || isNegativeInteger(orginal);
    }

    public static boolean isPositiveDecimal(String orginal) {
        return isMatch("^\\d+(\\.\\d+)?$", orginal);
    }

    public static boolean isNegativeDecimal(String orginal) {
        return isMatch("^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$", orginal);
    }

    public static boolean isDecimal(String orginal) {
        return isMatch("^(-?\\d+)(\\.\\d+)?$", orginal);
    }

    public static boolean isDigital(String str) {
        return isEmpty(str)?false:str.matches("^[0-9]*$");
    }

    public static boolean isLetter(String str) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher m = pattern.matcher(str);
        return m.matches();
    }

    public static boolean isLetter(char c) {
        return isLetter(c + "");
    }

    public static String escapeUTF8(String s) {
        StringBuffer sbuf = new StringBuffer();
        int len = s.length();

        for(int i = 0; i < len; ++i) {
            char ch = s.charAt(i);
            if(ch == 32) {
                sbuf.append('+');
            } else if(65 <= ch && ch <= 90) {
                sbuf.append((char)ch);
            } else if(97 <= ch && ch <= 122) {
                sbuf.append((char)ch);
            } else if(48 <= ch && ch <= 57) {
                sbuf.append((char)ch);
            } else if(ch != 45 && ch != 95 && ch != 46 && ch != 33 && ch != 126 && ch != 42 && ch != 39 && ch != 40 && ch != 41) {
                if(ch <= 127) {
                    sbuf.append('%');
                    sbuf.append(hex[ch]);
                } else {
                    sbuf.append('%');
                    sbuf.append('u');
                    sbuf.append(hex[ch >>> 8]);
                    sbuf.append(hex[255 & ch]);
                }
            } else {
                sbuf.append((char)ch);
            }
        }

        return sbuf.toString();
    }

    public static String unescapeUTF8(String s) {
        if(isEmpty(s)) {
            return null;
        } else {
            StringBuffer sbuf = new StringBuffer();
            int i = 0;

            for(int len = s.length(); i < len; ++i) {
                char ch = s.charAt(i);
                if(ch == 43) {
                    sbuf.append(' ');
                } else if(65 <= ch && ch <= 90) {
                    sbuf.append((char)ch);
                } else if(97 <= ch && ch <= 122) {
                    sbuf.append((char)ch);
                } else if(48 <= ch && ch <= 57) {
                    sbuf.append((char)ch);
                } else if(ch != 45 && ch != 95 && ch != 46 && ch != 33 && ch != 126 && ch != 42 && ch != 39 && ch != 40 && ch != 41) {
                    if(ch == 37) {
                        byte cint = 0;
                        int var6;
                        if(117 != s.charAt(i + 1)) {
                            var6 = cint << 4 | val[s.charAt(i + 1)];
                            var6 = var6 << 4 | val[s.charAt(i + 2)];
                            i += 2;
                        } else {
                            var6 = cint << 4 | val[s.charAt(i + 2)];
                            var6 = var6 << 4 | val[s.charAt(i + 3)];
                            var6 = var6 << 4 | val[s.charAt(i + 4)];
                            var6 = var6 << 4 | val[s.charAt(i + 5)];
                            i += 5;
                        }

                        sbuf.append((char)var6);
                    }
                } else {
                    sbuf.append((char)ch);
                }
            }

            return sbuf.toString();
        }
    }

    public static String concatWithDelimiter(Object[] objectIds, String delimiter, boolean beginWithDelimiter, boolean endWithDelimiter, String defaultValue) {
        if(isEmpty(objectIds)) {
            return defaultValue;
        } else {
            StringBuffer s = new StringBuffer(beginWithDelimiter?delimiter:"");
            Object[] arr$ = objectIds;
            int len$ = objectIds.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object id = arr$[i$];
                s.append(id).append(delimiter);
            }

            return endWithDelimiter?s.toString():s.toString().substring(0, s.toString().length() - 1);
        }
    }

    public static String concatWithDelimiter(Collection c, String delimiter, boolean beginWithDelimiter, boolean endWithDelimiter, String defaultValue) {
        return c != null && !c.isEmpty()?concatWithDelimiter(c.toArray(), delimiter, beginWithDelimiter, endWithDelimiter, defaultValue):defaultValue;
    }

    public static String filteNull(String inStr) {
        return inStr != null && !"null".equals(inStr)?inStr:"";
    }

    public static String filteNull(Number in) {
        return in == null?"":(in instanceof Double?"" + in.doubleValue():(in instanceof Float?"" + in.floatValue():(in instanceof Long?"" + in.longValue():"" + in.intValue())));
    }

    public static boolean isEmpty(StringBuffer sbf) {
        return sbf == null || sbf.length() == 0;
    }

    public static String getExceptionTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();

        for(int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if(b < 0) {
            n = b + 256;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin) {
        return MD5Encode(origin, "UTF-8");
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;

        try {
            resultString = new String(origin);
            MessageDigest exception = MessageDigest.getInstance("MD5");
            if(charsetname != null && !"".equals(charsetname)) {
                resultString = byteArrayToHexString(exception.digest(resultString.getBytes(charsetname)));
            } else {
                resultString = byteArrayToHexString(exception.digest(resultString.getBytes()));
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return resultString;
    }

    public static String buildRandom(int length) {
        String chars = "ABCDEFGHJKLMNPQRSTUVWXYZ";
        String charAndDigit = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            int number;
            if(i == 0) {
                number = random.nextInt(chars.length());
                sb.append(chars.charAt(number));
            } else {
                number = random.nextInt(charAndDigit.length());
                sb.append(charAndDigit.charAt(number));
            }
        }

        return sb.toString();
    }

    public static int buildRandomDigit(int length) {
        int num = 1;
        double random = Math.random();
        if(random < 0.1D) {
            random += 0.1D;
        }

        for(int i = 0; i < length; ++i) {
            num *= 10;
        }

        return (int)(random * (double)num);
    }

    public static String toFirstLetterUpperCase(String str) {
        if(str != null && str.length() >= 2) {
            String firstLetter = str.substring(0, 1).toUpperCase();
            return firstLetter + str.substring(1, str.length());
        } else {
            return str;
        }
    }

    public static String dealMobile(String mobile) {
        if(mobile != null && mobile.length() > 4) {
            String last4 = mobile.substring(mobile.length() - 4);
            if(mobile.length() <= 8) {
                return ("****" + last4).substring(8 - mobile.length());
            } else {
                String pre = mobile.substring(0, mobile.length() - 8);
                return pre + "****" + last4;
            }
        } else {
            return mobile;
        }
    }

    public static String dealLastRealName(String name) {
        return isEmpty(name)?"":name.substring(0, 1) + "*";
    }

    public static String dealCardId(String cardId) {
        if(emptyOrNull(cardId)) {
            return "";
        } else {
            int length = cardId.length();
            if(length <= 8) {
                return cardId;
            } else {
                String start = cardId.substring(0, 4);
                String end = cardId.substring(length - 4);
                return start + " **** **** " + end;
            }
        }
    }

    public static String dealCardId2(String cardId) {
        if(emptyOrNull(cardId)) {
            return "";
        } else {
            int length = cardId.length();
            if(length <= 8) {
                return cardId;
            } else {
                String end = cardId.substring(length - 4);
                return "尾号 " + end;
            }
        }
    }

    public static boolean isMobile(String mobile) {
        if(!isEmpty(mobile) && mobile.length() == 11) {
            Pattern pattern = Pattern.compile("^[1][0-9]{10}$");
            return pattern.matcher(mobile).matches();
        } else {
            return false;
        }
    }

    public static String timestampToHHMMSS(BigDecimal _timestamp) {
        boolean isNegative = false;
        if(_timestamp.compareTo(BigDecimal.ZERO) < 0) {
            _timestamp = _timestamp.negate();
            isNegative = true;
        }

        BigDecimal hh = _timestamp.divide(new BigDecimal("3600"), 0, 3);
        BigDecimal mm = _timestamp.subtract(hh.multiply(new BigDecimal("3600"))).divide(new BigDecimal("60"), 0, 3);
        return (isNegative?"负":"") + (hh.compareTo(BigDecimal.ZERO) > 0?hh + "时":"") + (mm.compareTo(BigDecimal.ZERO) > 0?mm + "分":"") + _timestamp.subtract(hh.multiply(new BigDecimal("3600"))).subtract(mm.multiply(new BigDecimal("60"))) + "秒";
    }

    public static String formatYYYYMM(Date time) {
        return sdf_time_yyyyMM.format(time);
    }

    public static String fillDigit(Integer num, int digit) {
        if(num == null) {
            num = Integer.valueOf(0);
        }

        return String.format("%0" + digit + "d", new Object[]{num});
    }

    public static String fillDigitOrTruncate(Integer num, int digit) {
        if(num == null) {
            num = Integer.valueOf(0);
        }

        return String.format("%0" + digit + "d", new Object[]{Integer.valueOf(num.toString().length() > digit?Integer.parseInt(num.toString().substring(0, digit)):num.intValue())});
    }



    public static TreeMap<String, Object> objectToMap(Object obj, boolean serializeNullValue) throws Exception {
        TreeMap map = new TreeMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        PropertyDescriptor[] arr$ = propertyDescriptors;
        int len$ = propertyDescriptors.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            PropertyDescriptor property = arr$[i$];
            String key = property.getName();
            if(key.compareToIgnoreCase("class") != 0) {
                Method getter = property.getReadMethod();
                Object value = getter != null?getter.invoke(obj, new Object[0]):null;
                if(value != null || serializeNullValue) {
                    map.put(key, value);
                }
            }
        }

        return map;
    }


    public static boolean compareTwo(Object object1, Object object2) {
        return object1 == null && object2 == null?true:(object1 == null && object2 != null?false:object1.equals(object2));
    }

    public static Date addTimeUnit(Date date, Integer timeUnit, Integer amount) {
        if(date != null && timeUnit != null && amount != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(timeUnit.intValue(), amount.intValue());
            return c.getTime();
        } else {
            return new Date();
        }
    }

    public static void VOConverter(Object src, Object target) {
        if(src != null && target != null) {
            Class srcClassType = src.getClass();
            Class targetClassType = target.getClass();
            Field[] srcFields = srcClassType.getDeclaredFields();
            Field[] arr$ = srcFields;
            int len$ = srcFields.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Field srcField = arr$[i$];

                try {
                    String name = srcField.getName();
                    String firstLetter = name.substring(0, 1).toUpperCase();
                    String getMethodName = "get" + firstLetter + name.substring(1);
                    if("boolean".equalsIgnoreCase(srcField.getType().getName())) {
                        getMethodName = "is" + firstLetter + name.substring(1);
                    }

                    String setMethodName = "set" + firstLetter + name.substring(1);
                    Method getMethod = srcClassType.getMethod(getMethodName, new Class[0]);
                    Method setMethod = targetClassType.getMethod(setMethodName, new Class[]{srcField.getType()});
                    Object value = getMethod.invoke(src, new Object[0]);
                    setMethod.invoke(target, new Object[]{value});
                } catch (Exception var16) {
                    ;
                }
            }

        }
    }

    private static String random(String chars, int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(chars.length());
            sb.append(chars.charAt(number));
        }

        return sb.toString();
    }

    public static String randomChar(int length) {
        return random("ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjklmnpqrstuvwxyz", length);
    }

    public static String randomCharAndDigit(int length) {
        return random("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghjklmnpqrstuvwxyz0123456789", length);
    }

    public static String randomDigit(int length) {
        return random("0123456789", length);
    }

    public static String getDisplayNameFuzzy(String nickName, String username, String mobile) {
        if(isNotEmpty(nickName)) {
            try {
                return nickName.getBytes().length <= 4?(nickName.getBytes().length != nickName.length()?nickName.substring(0, 1) + "***":nickName.substring(0, 2) + "***" + nickName.substring(2)):(nickName.getBytes().length != nickName.length()?nickName.substring(0, 1) + "***" + nickName.substring(nickName.length() - 1):nickName.substring(0, 2) + "***" + nickName.substring(nickName.length() - 2));
            } catch (Exception var4) {
                return "******";
            }
        } else if(isNotEmpty(username)) {
            try {
                return username.substring(0, 2) + "**@*" + username.split("@")[1].substring(username.split("@")[1].lastIndexOf("."));
            } catch (Exception var5) {
                return "********";
            }
        } else if(isNotEmpty(mobile)) {
            try {
                return mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
            } catch (Exception var6) {
                return "***********";
            }
        } else {
            return "*******";
        }
    }

    public static String getMobileFuzzy(String mobile) {
        if(isNotEmpty(mobile)) {
            try {
                return mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
            } catch (Exception var2) {
                return "***********";
            }
        } else {
            return "*******";
        }
    }

    public static String getEmailFuzzy(String email) {
        if(isNotEmpty(email)) {
            try {
                return email.substring(0, 2) + "**@*" + email.split("@")[1].substring(email.split("@")[1].lastIndexOf("."));
            } catch (Exception var2) {
                return "********";
            }
        } else {
            return "*******";
        }
    }

    public static String getIdcardFuzzy(String idcard) {
        if(isNotEmpty(idcard)) {
            try {
                return idcard.substring(0, 6) + "***" + idcard.substring(idcard.length() - 1, idcard.length());
            } catch (Exception var2) {
                return "********";
            }
        } else {
            return "*******";
        }
    }

    public static String getBankcardFuzzy(String bankcard) {
        if(isNotEmpty(bankcard)) {
            try {
                return bankcard.substring(0, 4) + "***" + bankcard.substring(bankcard.length() - 4, bankcard.length());
            } catch (Exception var2) {
                return "********";
            }
        } else {
            return "*******";
        }
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static String buildSign(Map requestParams, String privateKey) {
        HashMap params = new HashMap();

        String content;
        String value;
        for(Iterator properties = requestParams.keySet().iterator(); properties.hasNext(); params.put(content, value)) {
            content = (String)properties.next();
            String[] signBefore = (String[])((String[])requestParams.get(content));
            value = "";

            for(int e = 0; e < signBefore.length; ++e) {
                value = e == signBefore.length - 1?value + signBefore[e]:value + signBefore[e] + ",";
            }

            try {
                value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException var8) {
                var8.printStackTrace();
            }
        }

        Properties var9 = new Properties();
        Iterator var10 = params.keySet().iterator();

        String var11;
        while(var10.hasNext()) {
            var11 = (String)var10.next();
            Object var12 = params.get(var11);
            if(var11 != null && !var11.equalsIgnoreCase("sign") && !var11.equalsIgnoreCase("sign_type")) {
                var9.setProperty(var11, var12.toString());
            }
        }

        content = getSignatureContent(var9);
        if(privateKey == null) {
            return null;
        } else {
            var11 = content + privateKey;
            return MD5Encode(var11, "UTF-8");
        }
    }

    public static String getSignatureContent(Properties properties) {
        StringBuffer content = new StringBuffer();
        ArrayList keys = new ArrayList(properties.keySet());
        Collections.sort(keys);

        for(int i = 0; i < keys.size(); ++i) {
            String key = (String)keys.get(i);
            String value = properties.getProperty(key);
            content.append((i == 0?"":"&") + key + "=" + value);
        }

        return content.toString();
    }

    public static String createNickName() {
        StringBuilder nickName = new StringBuilder("WE");
        Random random = new Random();
        int randomIndex = 0;

        do {
            int randomNum;
            do {
                randomNum = random.nextInt(10);
            } while(randomNum == 4);

            ++randomIndex;
            nickName.append(randomNum);
        } while(randomIndex != 8);

        return nickName.toString();
    }

    public static String createNickName(String partners) {
        StringBuilder nickName = new StringBuilder(partners);
        Random random = new Random();
        int randomIndex = 0;

        do {
            int randomNum;
            do {
                randomNum = random.nextInt(10);
            } while(randomNum == 4);

            ++randomIndex;
            nickName.append(randomNum);
        } while(randomIndex != 8);

        return nickName.toString();
    }

    public static byte[] serialize(Serializable obj) {
        try {
            ByteArrayOutputStream e = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(e);
            os.writeObject(obj);
            return e.toByteArray();
        } catch (IOException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static Serializable deserialize(byte[] data) {
        try {
            ByteArrayInputStream e = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(e);
            return (Serializable)is.readObject();
        } catch (IOException var3) {
            var3.printStackTrace();
        } catch (ClassNotFoundException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public static Integer getNumNew(String num, Integer def, Integer max) {
        try {
            return Integer.parseInt(num) > max.intValue()?max:(Integer.parseInt(num) < 1?def:Integer.valueOf(Integer.parseInt(num)));
        } catch (Exception var4) {
            return def;
        }
    }

    public static Integer getStart(Integer start) {
        if(start == null || start.intValue() < 0) {
            start = PAGE_START;
        }

        return start;
    }

    public static Integer getNum(Integer num) {
        if(num == null || num.intValue() < 0) {
            num = PAGE_NUM;
        }

        return num;
    }
}
