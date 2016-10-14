package com.kevin.learning.groovy.Koan02
/**
 * Created by kaiwen on 2016/8/10.
 */
class Koan02 extends GroovyTestCase {



    void testpropLoad(){
        def properties=new Properties()
        def str = '''
#####数据源配置开始#####
#用户中心数据源
jdbc.url=jdbc:mysql://172.16.2.187:33096/jjsumc?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF8&amp;noAccessToProcedureBodies=true&amp;zeroDateTimeBehavior=convertToNull
jdbc.username=root
jdbc.password=passwd32

#是否启用加密策略
db_props=config.decrypt=false

#七牛云配置
qiniu.ak=6KZP9QWlGeJvQb6GsTfapF5uKb0Afwb3DZWZCWfv
qiniu.sk=0wO_Qd-OoR6xSE09C9ewn606RE4CxCVZfdEykNGO
qiniu.qiniuUploadUrl=http://upload.qiniu.com/
#经纪人外网背景图片存放目录
qiniu.bucket=jjstest
''';
        def stream = new ByteArrayInputStream(str.getBytes())
        properties.load(stream)
        def propList = new ArrayList<>()
        for(def p in properties) {
            def m = new LinkedHashMap()
            m.put(p.getKey(), p.getValue());
            propList.add(m)
        }
        def json = new groovy.json.JsonBuilder( propList );
        print(json)

    }

    //xml 分享地址：http://www.shucunwang.com/RunCode/groovy/#id/72763d26ea4aab2a8dcec9745c500d5a
    void testString(){
        def str = '''
    <?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
\t<!-- 数据库配置 -->
\t<entry key="db.url">jdbc:mysql://New-FangYuan-Mycat-Cluster-Node1.jjshome.com:8066/MYDB?characterEncoding=UTF8&amp;zeroDateTimeBehavior=convertToNull</entry>
\t<entry key="db.username">test</entry>
\t<entry key="db.password">test</entry>
\t<entry key="db.filters">slf4j,stat,config</entry>



</properties>
            '''.trim();


        def xmlParser = new XmlParser();
        xmlParser.setFeature('http://apache.org/xml/features/disallow-doctype-decl',false);
        xmlParser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        def root = xmlParser.parseText(str);
        def nodeList = root.getByName("entry");
        def entryList = new ArrayList()
        for (def v in nodeList) {
//            print(  v.@key  )
//            println( ':'+v.text() )

            Map m = new LinkedHashMap()
            m.put(v.@key,v.text())
            entryList.add(m);
        }

        def json = new groovy.json.JsonBuilder( entryList );
        print json.toString()
    }


    void test01_RegularBooleanExpressions() {

        //Boolean expressions work as expected. true evaluates to true and false evaluates to false .
        def predicate1 = false
        def predicate2 = true

        predicate1 = true
        predicate2 = false

        assert predicate1
        assert predicate2 == false
    }


    void test02_collections() {
        // Lists and maps evaluate to false if they're empty. Otherwise, they evaluate to true.
        Map<String, String> map = [:]
        List<String> list = ['item']

        // ---------- START EDITING HERE -------------------
        map['key'] = 'value'
        list.clear()
        // ------------ STOP EDITING HERE ---------------

        assert map.asBoolean()
        assert list.asBoolean() == false
    }

    void test03_StringTruth() {
        // Quite intuitively, empty (or null) strings are false
        String s1 = "Non-empty string"
        String s2 = ''

        // Note how we can use String expressions inside an if statement . It is automatically converted to boolean.
        if(s1) {
            fail()
        }
        assert s2.asBoolean()
    }

    void test04_numericTruth() {
        // Similar to C code, null or zeros are false . Any other number is true.
        def balance = [2, -3, 6, 0, 5]

        // Remove (or change ) the offending integer to continue
        balance[3] = 3

        // Iterate through the list and AND the boolean values of its members.
        // For an easier way to do this in Groovy , check out the any() method at
        // http://docs.groovy-lang.rog/lastest/html/groovy;jdk/jajva/lang/Objec.thtml#any(groovy.lang.Closure)
        def result = true
        for (int i : balance) {
            result = result && i.asBoolean()
        }

        assert result
    }

}
