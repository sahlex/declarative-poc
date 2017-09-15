# declarative-poc
proof of concept for project all with DS in karaf

After build with
```
mvn install
```
  
you can use docker to build the container (in docker subfolder) under linux with the build.sh script.

Or just untar the karaf assembly (in corresponding assembly/target folder)

# Usage
Starting the context: In directory where you untared the assembly:
```
DSContext-assembly-1.0.0-SNAPSHOT/bin/start-context
```

Store a test object
```
curl -X POST \
  http://localhost:8181/test/test/ \
  -H 'content-type: application/json' \
  -d '{ "id": "3", "name" : "Test 3"}'
```

Get the object
```
curl -X GET \
  http://localhost:8181/test/test/3
```
