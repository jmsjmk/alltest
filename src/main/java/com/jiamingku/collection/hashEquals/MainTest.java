package com.jiamingku.collection.hashEquals;

import java.awt.*;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class MainTest
{
    public static void main(String[] args)
    {
        Map<Twins, String> hashMap = new HashMap<Twins, String>();

        Map<Twins, String> identityMap = new IdentityHashMap<Twins, String>();

        // 兄弟
        Twins brother = new Twins(Color.green);

        // 哥哥
        Twins eldBrother = new Twins(Color.green);

        hashMap.put(brother, "弟弟");
        hashMap.put(eldBrother, "哥哥");

        System.out.println(hashMap);//{com.scc.Twins@ff01010f=哥哥} 结果却只有哥哥

        identityMap.put(brother, "绿色衣服的弟弟");

        //第二天弟弟换了一身蓝衣服
        brother.setColor(Color.BLUE);

        identityMap.put(brother, "蓝色衣服的弟弟");
        System.out.println(identityMap);//{com.scc.Twins@ff00030e=蓝色衣服的弟弟} 结果弟弟还是弟弟,只是颜色不同罢了

        System.out.println("===============");

        test();

    }


    public static void test() {

        IdentityHashMap<String,Object> map =new IdentityHashMap<String,Object>();
        map.put(new String("xx"),"first");
        map.put(new String("xx"),"second");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.print(entry.getKey() +"    ");
            System.out.println(entry.getValue());
        }
        System.out.println("idenMap="+map.containsKey("xx"));
        System.out.println("idenMap="+map.get("xx"));

        System.out.println(" ====================================== " );


        Map<String, Object> map1 = new HashMap<>();

        map1.put(new String("xx"), "333");
        map1.put(new String("xx"), "333");
        System.out.println("map1 = " + map1);

    }
}