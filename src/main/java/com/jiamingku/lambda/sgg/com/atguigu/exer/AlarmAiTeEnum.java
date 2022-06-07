package com.jiamingku.lambda.sgg.com.atguigu.exer;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kjeakiry
 */

public enum AlarmAiTeEnum {

    /**
     * 接口相关开发
     */
    DRIVER_PUNISH("manage-rest/driverPunish", Collections.singletonList(Constant.RISK_GK)),
    DRIVER_UPGRADE_RECORD("manage-rest/gradeRecord", Collections.singletonList(Constant.RISK_GK)),
    DRIVER_UPGRADE_STRATEGY("/manage-rest/gradeStrategy", Collections.singletonList(Constant.RISK_WWL)),
    DRIVER_UPGRADE_CITY_STRATEGY("/manage-rest/gradeCityStrategy", Collections.singletonList(Constant.RISK_WWL));


    AlarmAiTeEnum(String url, List<String> phoneGroup) {
        this.phoneGroup = phoneGroup;
        this.url = url;
    }

    private String url;

    private List<String> phoneGroup;

    public static Map<String, AlarmAiTeEnum> getAlarmEnumMap() {
        return alarmEnumMap;
    }

    public static void setAlarmEnumMap(Map<String, AlarmAiTeEnum> alarmEnumMap) {
        AlarmAiTeEnum.alarmEnumMap = alarmEnumMap;
    }

    public List<String> getPhoneGroup() {
        return phoneGroup;
    }

    public void setPhoneGroup(List<String> phoneGroup) {
        this.phoneGroup = phoneGroup;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static List<String> getUrlList() {
        return urlList;
    }

    public static void setUrlList(List<String> urlList) {
        AlarmAiTeEnum.urlList = urlList;
    }

    private static class Constant {
        private final static String RISK_PHONE = "13363643828,15011571341,17090860526";
        private final static String RISK_GK = "17090860526";
        private final static String RISK_WWL = "13363643828";
    }

    private static List<String> urlList = new ArrayList<>();
    private static Map<String, AlarmAiTeEnum> alarmEnumMap = Maps.newHashMapWithExpectedSize(AlarmAiTeEnum.values().length);

    static {
        Arrays.stream(AlarmAiTeEnum.values()).forEach(e -> {
            alarmEnumMap.put(e.getUrl(), e);
            urlList.add(e.getUrl());
        });
    }

    public static List<String> ofUrl(String url) {
        return Optional.ofNullable(alarmEnumMap.get(url)).map(e -> {
            return e.getPhoneGroup().stream().flatMap(phone -> Arrays.stream(phone.split(","))).distinct().collect(Collectors.toList());
        }).orElse(null);

    }

    /**
     * url关联的手机号
     *
     * @param url
     * @return
     */
    public static List<String> relatedPhone(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        return urlList.stream().filter(url::contains).map(AlarmAiTeEnum::ofUrl)
                .filter(Objects::nonNull).flatMap(Collection::stream).distinct()
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        AlarmAiTeEnum.relatedPhone("3333333333");

        String url = "dd";
        urlList.stream().filter(t -> t.contains(url));
    }

}
