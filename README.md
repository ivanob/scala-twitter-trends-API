# scala-twitter-trends-API

**Configuration**

You need to setup your configuration file with your own twitter credentials.
To do that, create a file in _src/main/resources_ called _application.conf_. 
The content of the file should be:

```
twitter {
   consumer {
     key = "YOUR_CONSUMER_KEY"    
     secret = "YOUR_CONSUMER_SECRET"
   }
   access {
     key = "YOUR_ACCESS_KEY"
     secret = "YOUR_ACCESS_SECRET"
   }
 }

 http{
   port=8080
   host="localhost"
 }

 ```
 
