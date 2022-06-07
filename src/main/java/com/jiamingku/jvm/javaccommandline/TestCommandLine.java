package com.jiamingku.jvm.javaccommandline;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

/**
 * 参数分类
 * 1)jvm参数
 * <p>
 * 2)命令行的参数
 */
public class TestCommandLine {

    private static final String PARAM_DATA_SOURCE = "d";
    private static final String PARAM_EXECUTE_THREAD = "t";
    private static final String PARAM_CONSUMER_GROUP = "consumer-group";
    private static final String PARAM_MOCK = "mock";

    static String SERVER_LISTS = "server-lists";

    static String NAMESPACE = "namespace";

    static String BASE_SLEEP_TIME_MILLISECONDS = "base-sleep-time-milliseconds";

    static String MAX_SLEEP_TIME_MILLISECONDS = "max-sleep-time-milliseconds";

    static String MAX_RETRIES = "max-retries";

    static String SESSION_TIMEOUT_MILLISECONDS = "session-timeout-milliseconds";

    static String CONNECTION_TIMEOUT_MILLISECONDS = "connection-timeout-milliseconds";

    static String DIGEST = "digest";

    public static void main(String[] args) throws Exception {
        System.out.println("args = " + args);
        String nvl = System.getProperty("nvl");

        System.out.println("nvl = " + nvl);
        System.out.println();
        for (String s : args) {
            System.out.println("s = " + s);
        }
        //
        // -d share-order-binlog
        // --consumer-group shareGroup
        // --server-lists "${ZK_ADDRESS}"
        // --namespace "${NAME_SPACE}"
        // --base-sleep-time-milliseconds 1000
        // --max-sleep-time-milliseconds 3000
        // --max-retries 3
        System.out.println("ok = ");
        Options options = new Options();
//         options.addOption(PARAM_DATA_SOURCE, null, true, "data source name");
        options.addOption(PARAM_DATA_SOURCE, null, true, "zookeeper address eg. 127.0.0.1:2181, 127.0.0.1:2182");
//        options.addOption(null, PARAM_DATA_SOURCE, true, "zookeeper address eg. 127.0.0.1:2181, 127.0.0.1:2182");

//        options.addOption(PARAM_DATA_SOURCE, null, true, "data source name");
        options.addOption(new Option(PARAM_EXECUTE_THREAD, true, "thread number"));
        options.addOption(new Option(null, PARAM_MOCK, true, "mock bean name"));
        options.addOption(new Option(null, PARAM_CONSUMER_GROUP, true, "consumer group"));

        options.addOption(null, SERVER_LISTS, true, "zookeeper address eg. 127.0.0.1:2181, 127.0.0.1:2182");
        options.addOption(null, NAMESPACE, true, "zookeeper namespace eg. charge");
        options.addOption(null, BASE_SLEEP_TIME_MILLISECONDS, true, "base sleep time");
        options.addOption(null, MAX_SLEEP_TIME_MILLISECONDS, true, "max sleep time");
        options.addOption(null, MAX_RETRIES, true, "connect to zookeeper when fail retry times");
        options.addOption(null, SESSION_TIMEOUT_MILLISECONDS, true, "zookeeper session timeout");
        options.addOption(null, CONNECTION_TIMEOUT_MILLISECONDS, true, "zookeeper connection timeout");
        options.addOption(null, DIGEST, true, "zookeeper digest");

        CommandLine commandLine = new DefaultParser().parse(options, args);


        System.out.println(commandLine.getOptionValue(SERVER_LISTS));
        System.out.println(commandLine.getOptionValue(PARAM_DATA_SOURCE));
        System.out.println(commandLine.getOptionValue(PARAM_EXECUTE_THREAD));

        commandLine.hasOption(PARAM_EXECUTE_THREAD);

    }

    @Test
    public void test1() {
        String s = StringUtils.removeEnd("STERERWE-SFSDF-SDFS%d", "%d");
        System.out.println("s = " + s);

    }


    @Test
    public void test2() {
        Map map = System.getProperties();
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            Object o = map.get(next);
            if (!(next instanceof String)) {
                System.out.println(" =-============================================= ");
            }
            if (!(o instanceof String)) {
                System.out.println(" =-============================================= ");
            }

        }

//        System.out.println(" ========================== ");
//        Map<String, String> getenv = System.getenv();
//        getenv.entrySet().forEach(e -> {
//            String key = e.getKey();
//            String value = e.getValue();
//            System.out.println(key + ":" + value);
//        });
       //
    }

    @Test
    public void  ttt() {
        String a = "$1234567";
        String substring = a.substring(1,2);
        System.out.println( substring);

        String substring1 = a.substring("$1".length());
        System.out.println("substring1 = " + substring1);
    }
}
