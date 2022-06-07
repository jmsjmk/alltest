package com.middleserver.binlog;

import com.alibaba.fastjson.JSON;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 同时间启动的话,一个账户-只能发送一个接收端.
 * TestBinlog--
 */
@Slf4j
public class TestBinlog2 {

    @Test
    public void test() {
        try {
            Date d = new Date(1634959439000L);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(d);
            System.out.println("format = " + format);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        BinaryLogClient client = new BinaryLogClient("10.20.10.100", 3306, "sq_payment", "sqhc_rentcar", "BzkmQM%O^U7Dy8X3");
        client.setServerId(111);
        try {
            EventDeserializer eventDeserializer = new EventDeserializer();
            //时间反序列化的格式
//        eventDeserializer.setCompatibilityMode(
//                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
//                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
//        );
            client.setEventDeserializer(eventDeserializer);
            client.registerEventListener(new BinaryLogClient.EventListener() {
                @Override
                public void onEvent(Event event) {
                    //                System.out.println("event = " + JSON.toJSONString(event, true));
                    boolean b = client.isConnected();

                    long pos = client.getBinlogPosition();
                    String s = client.getBinlogFilename();

                    EventHeaderV4 eventHeaderV4 = event.getHeader();
                    long nextPosition = eventHeaderV4.getPosition();
//                    if (pos == nextPosition) {
//                        System.out.println(" ==== ");
//                    }
//
////                    System.out.println("s = " + s);
//                    System.out.println("pos = " + pos);
////                    System.out.println("header = " + heade);
//

//                    System.out.println("b = " + b);
                    EventHeader header = event.getHeader();
                    EventType eventType = header.getEventType();
                    //                if (eventType == EventType.UPDATE_ROWS) {
                    //
                    //                }
                    if ((event.getData() instanceof UpdateRowsEventData)) {
                        System.out.println("监听的事件类型:" + eventType);

                        System.out.println("event = " + JSON.toJSONString(event, true));
                        System.out.println(event.getData().toString());

                        System.out.println();
                        System.out.println();
                        System.out.println();
                        UpdateRowsEventData data = (UpdateRowsEventData) event.getData();

                        data.getRows().stream().forEach(e -> {
                            Serializable[] keys = e.getKey();
                            for (Serializable s1 : keys) {
                                System.out.println("s1 = " + s1);
                            }
                        });

                        System.out.println();
                        System.out.println();
                        System.out.println();
                        data.getRows().stream().forEach(e -> {
                            Serializable[] keys = e.getValue();
                            for (Serializable s1 : keys) {
                                System.out.println("s2 = " + s1);
                            }
                        });

                    }

                    if (event.getData() instanceof TableMapEventData) {
                        TableMapEventData tableMapEventData = event.getData();
                        String schema = tableMapEventData.getDatabase() + "." + tableMapEventData.getTable();
                        System.out.println("schema = " + schema);
                        System.out.println("tableMapEventData = " + tableMapEventData);
                    }
                    //
                    //
                    //                }
                    //                if (EventType.isWrite(eventType)) {
                    //                    //获取事件体
                    //                    WriteRowsEventData data = event.getData();
                    //                    log.info("write:" + JSON.toJSONString(data));
                    //                } else if (EventType.isUpdate(eventType)) {
                    //                    UpdateRowsEventData data = event.getData();
                    //                    log.info("update:" + JSON.toJSONString(data));
                    //                } else if (EventType.isDelete(eventType)) {
                    //                    DeleteRowsEventData data = event.getData();
                    //                    log.info("delete:" + JSON.toJSONString(data));
                    //                }
                }
            });

            boolean b = client.isConnected();
            System.out.println("b = " + b);

            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(" finally.... ");
            if (client.isConnected()) {
                try {
                    client.disconnect();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * UpdateRowsEventData{tableId=583107320, includedColumnsBeforeUpdate={0, 1, 2, 3, 4}, includedColumns={0, 1, 2, 3, 4}, rows=[
     * {before=[quartzScheduler, 10-20-0-131635236477570, 1635245685722, 7500, 8540], after=[quartzScheduler, 10-20-0-131635236477570, 1635245693223, 7500, 8540]}
     * ]}
     */
    @Test
    public void test1() {
        Map<String, String> maps = new HashMap<>();
        maps.put("k1", "v1");
        maps.put("k2", "v2");
        maps.put("k4", "v4");

//        Map<String, String> maps1 = new HashMap<>();
//        maps1.put("k1", "v1");
//        maps1.put("k2", "v2");
//        maps1.put("k4", "v4");

        List<Map<String, String>> mapList = new ArrayList<>();
        mapList.add(maps);
//        mapList.add(maps1);

        String s = JSON.toJSONString(mapList);
        System.out.println("s = " + s);

    }
}
