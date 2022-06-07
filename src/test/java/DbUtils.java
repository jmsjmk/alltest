import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by jiamingku on 2018/6/23.
 */
public class DbUtils {
    private  static String  s= "CREATE TABLE `policy_emptyrun_order_%02d` (\n" +
            "  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',\n" +
            "  `order_no` varchar(64) NOT NULL DEFAULT '' COMMENT '保险订单号(tradeOrderNo)',\n" +
            "  `policy_no` varchar(64) DEFAULT '' COMMENT '保单号',\n" +
            "  `report_no` varchar(64) DEFAULT '' COMMENT '受理号',\n" +
            "  `product_code` varchar(64) NOT NULL DEFAULT '' COMMENT '保险商品编号',\n" +
            "  `driver_id` varchar(64) NOT NULL DEFAULT '' COMMENT '司机ID',\n" +
            "  `driver_name` varchar(32) NOT NULL DEFAULT '0' COMMENT '司机姓名',\n" +
            "  `driver_card_no` varchar(64) NOT NULL DEFAULT '0' COMMENT '司机身份证号',\n" +
            "  `car_no` varchar(128) DEFAULT '0' COMMENT '车牌号',\n" +
            "  `policy_premium` decimal(20,2) DEFAULT NULL COMMENT '保单保费',\n" +
            "  `claim` varchar(16) NOT NULL DEFAULT '0' COMMENT '是否理赔,0=未理赔, 1=理赔',\n" +
            "  `insure_rep` varchar(512) DEFAULT '' COMMENT '投保返回参数',\n" +
            "  `insure_rep_time` varchar(64) DEFAULT '' COMMENT '投保返回时间',\n" +
            "  `claim_rep` varchar(512) DEFAULT '' COMMENT '理赔返回参数',\n" +
            "  `claim_rep_time` varchar(64) DEFAULT '' COMMENT '理赔返回时间',\n" +
            "  `claim_reason` varchar(128) DEFAULT '' COMMENT '理赔原因',\n" +
            "  `claim_time` timestamp NULL DEFAULT NULL COMMENT '出险时间',\n" +
            "  `pay_time` timestamp NULL DEFAULT NULL COMMENT '理赔时间',\n" +
            "  `mileage` varchar(16) DEFAULT '' COMMENT '空驶里程',\n" +
            "  `city` varchar(16) NOT NULL DEFAULT '' COMMENT '出险城市',\n" +
            "  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
            "  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE KEY `idx_policy_order_no` (`order_no`),\n" +
            "  KEY `idx_policy_driver_id` (`driver_id`),\n" +
            "  KEY `idx_create_date` (`create_date`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=714862 DEFAULT CHARSET=utf8mb4 COMMENT='空驶险数据库表,按照司机id进行分表';";

    static String s2 = "select * from policy_emptyrun_order_%02d f where f.order_no = 'B210614225353328001'";

    public static void main(String[] args) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 50; i++) {
            String s1 = String.format(s2,i);
            System.out.println("s1 = " + s1);
            stringBuffer.append(s1).append("union all \n");
        }
        System.out.println(stringBuffer.toString());

        FileOutputStream fileOutputStream = new FileOutputStream(new File("/Users/jiamingku/1.sql"));
        fileOutputStream.write(stringBuffer.toString().getBytes());
        fileOutputStream.flush();
    }



}
