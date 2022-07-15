# working for TomEE 8

```
$ tree
.
├── META-INF
├── myjsp.jsp
└── WEB-INF
    ├── lib
    │   └── jakarta.jms-api-3.1.0-SNAPSHOT.jar
    └── web.xml

3 directories, 3 files
````

```
$ cat myjsp.jsp
<%@page import="java.util.Collection,
                javax.annotation.Resource,
                jakarta.jms.ConnectionFactory,
                jakarta.jms.Queue,
                jakarta.jms.JMSException,
                jakarta.jms.Connection,
                jakarta.jms.Session,
                jakarta.jms.MessageProducer,
                jakarta.jms.TextMessage
                "
%>
<%!
   @Resource(lookup = "jms/connectionFactory")
   static ConnectionFactory connectionFactory;
   @Resource(lookup="jms/dataQueue")
   static Queue dataQueue;

   public static void sendMessageOld (String body) throws JMSException {
      try (Connection connection = connectionFactory.createConnection()) {
         Session session = connection.createSession();
         MessageProducer producer = session.createProducer(dataQueue);
         TextMessage textMessage = session.createTextMessage(body);
         producer.send(textMessage);
      }
   }

%>
<% 
   /* String path = (String) request.getAttribute("path");
   String version = (String) request.getAttribute("version");
   ContextName cn = new ContextName(path, version);
   String submitUrl = JspHelper.escapeXml(response.encodeURL(
           ((HttpServletRequest) pageContext.getRequest()).getRequestURI() +
           "?path=" + path + "&version=" + version));
   Collection<Session> activeSessions = (Collection<Session>) request.getAttribute("activeSessions");
   */

   sendMessageOld("test");
   System.out.println("testing");
%>
<head>
    <title>Sessions Administration </title>
</head>
<body>
   hi12
</body>
</html>
```

# still TODOs
* add the connectionFactory resources
* have a consumer
* test it

