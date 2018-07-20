**XML 生成构建器**
#xml 生成格式如下

格式一:

````<DataRecordSet iscqueue="v0" queuecode="gN" statues="N3">
    <DataRecord table="4w">
        <batchno _v="aO"/>
        <uploadbranch _v="Fh"/>
        <uploadbranchname _v="r2"/>
        <ownerbranch _v="bl"/>
        ...
    </DataRecord>
    <DataRecord table="Bo">
        <plateno _v="bc"/>
        <cname _v="c8"/>
        ...
    </DataRecord>
        ...
</DataRecordSet>
````
格式二:
````<?xml version="1.0" encoding="utf-8" ?>
   <DataRecordSet iscqueue="FS" queuecode="es" statues="IK">
       <DataRecord table="SI">
           <batchno>"2A"</batchno>
            ...
       </DataRecord>
       <DataRecord table="gO">
           <plateno _v="oW"/>
           <cname _v="Ix"/>
           ...
       </DataRecord>
       ...
   </DataRecordSet>
   ````
 格式三:
````<?xml version="1.0" encoding="utf-8" ?>
 <DataRecordSet iscqueue="kX" queuecode="nb" statues="Uv">
     <DataRecord table="xW">
         <batchno _v="d8"/>
         <uploadbranch _v="mf"/>
         <uploadbranchname _v="B8"/>
          ...
         <remark _v="6w"/>
     </DataRecord>
     <DataRecord table="JH">
         <plateno>"xY"</plateno>
         <cname>"Pe"</cname>
        ...
     </DataRecord>
     ...
 </DataRecordSet>
 ````
格式四:
````<?xml version="1.0" encoding="utf-8" ?>
<"我是测试" iscqueue="H6" queuecode="PE" statues="mi">
    <DataRecord table="fD">
        <batchno>"Qf"</batchno>
        <uploadbranch>"NI"</uploadbranch>
        ...
    </DataRecord>
   <DataRecord table="cp">
    <plateno>"cs"</plateno>
    <cname>"DX"</cname>
    ...
   </DataRecord>
   ...
</"我是测试">`
````
#对应的 xml 对象类型
````@XMLEntity(isHavingEndTag = true)
  public class Title {
  
      @XMLClosure(name = "ABC", isHavingEndTag = true, attributes = {"iscqueue", "queuecode", "statues"})
      private String dataRecordSet;
  
      private String iscqueue = "N";
  
      private String queuecode = "12312";
  
      private String statues = "1";
  
      @XMLClosure(name = "DataRecord", isHavingEndTag = true, attributes = {"table"})
      private String dataRecord;
  
      private String table = "ts_upload_batch";
  
      private String batchno;
  
      private String uploadbranch;
  
      private String uploadbranchname;
  
      private String ownerbranch;
  
      private List<Body> bodys = new ArrayList<>();
      .
      ..
      ....
````
````@XMLEntity(isHavingEndTag = true)
    public class Body {
    
        @XMLClosure(name = "CBD", isHavingEndTag = true, attributes = {"table", "plateno"})
        private String dataRecord;
    
        private String table = "ts_upload_data";
    
        private String plateno;
    
        private String cname;
    
        private String mobile1;
        .
        ..
        ...
        
````
#解析  xml类型
````
<result>
<Result id='我是ID' cc='23423'>
<user_task_id>-1</user_task_id>
<Mobile>453453453453</Mobile>
<Message>你好</Message>
<state>成功</state>
<exec_time>2018/6/27 9:53:54</exec_time>
</Result>
...
<result>
<Result id='我是ID' cc='23423'>
<user_task_id>-1</user_task_id>
<Mobile>453453453453</Mobile>
<Message>你好</Message>
<state>成功</state>
<exec_time>2018/6/27 9:53:54</exec_time>
</Result>
````
#对应的实体
````@XMLEntity(isHavingEndTag = true)
    public class Sms {  
       @XMLClosure(name = "Result", isHavingEndTag = true, attributes = {"id", "cc"})
       private String result;
   
       @XMLClosure(name = "id")
       private String id;
   
       private String cc;
   
       @XMLClosure(name = "Mobile")
       private String mobile;
   
       @XMLClosure(name = "exec_time")
       private String execTime;
   
       @XMLClosure(name = "Message")
       private String message;
   
       private String state;
   
       @XMLClosure(name = "user_task_id")
       private String userTaskId;
       .
       ..
       ...
````