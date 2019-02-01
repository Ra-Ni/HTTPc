# HTTPC

Httpc is a java curl-like application but supports HTTP protocol only.

The Uml Diagram for this application is found in the project directory as "UMLDiagram.png".

### Getting Started

##### Requirements
- Java JRE 8/1.8+ : Mandatory
- Java JDK 8/1.8+ : Optional

##### Usage
- General Usage
    ```
    httpc (get|post|help) [arguments]

    - get: executes a HTTP GET request and prints the response.
    - post: executes a HTTP POST request and prints the response.
    - help: prints this screen.
    ```
    - Use *httpc help [request]* for more information about a request.

- GET Usage
    ```
    httpc get [-v] (-h key:value)* URL
    
    - -v: prints detail of response (protocol,status, and headers)
    - -h key:value: Associates headers to HTTP request with the format  'key:value'
    ```

- POST Usage
    ```
    httpc post [-v] (-h key:value)* [-d inline-data] [-f file] URL
    
    - -v : prints detail of response (protocol,status, and headers)
    - -h key:value : Associates headers to HTTP request with the format  'key:value'
    - -d string : Associates inline data to the body HTTP POST request
    - -f file : Associates the content of a file to the body HTTP POST request
    ```
    - Either [-d] or [-f] can be used, but not both.

### Progress
- [x] (GET|POST|HELP) and URL fields are mandatory
- [x] Arguments [-v],[-h],and [-o] are available
- [x] Additional arguments [-d] and [-f] are only available in POST
- [x] Arguments [-v],[-h],[-d],[-f],[-o] are optional
- [x] Either argument [-d] or [-f] is consumable
- [x] If either [-d] or [-f] is called, header appends *Content-Length* param
- [x] Allow multiple [-h] arguments
- [x] Print results to console, unless [-o] is called and is valid
- [x] If sending HELP query, allow options [get|post]
- [x] If any Exception, print HELP to console
- [x] HTTP reply code 3xx throws RedirectException
- [x] Catch RedirectException and implicitly perform redirects
- [x] Allow at most 5 redirects (preventative for infinite loops)
- [x] If more than 5 redirects performed, throw MethodException
- [x] Options [-o] and [-f] throws MethodException only if IOException detected
- [x] Throw MethodException only if any argument is not available


### Examples
##### GET with verbose

```
httpc get -v 'http://httpbin.org/get?course=networking&assignment=1'
HTTP/1.1 200 OK
Server: nginx
Date: Fri, 1 Sep 2017 14:52:12 GMT
Content-Type: application/json
Content-Length: 255
Connection: close
Access-Control-Allow-Origin: *
Access-Control-Allow-Credentials: true

{
"args": {
"assignment": "1",
"course": "networking"
},
"headers": {
"Host": "httpbin.org",
"User-Agent": "Concordia-HTTP/1.0"
},
"url": "http://httpbin.org/get?course=networking&assignment=1"
}
```

##### Get with query

```
httpc get 'http://httpbin.org/get?course=networking&assignment=1'
{
"args": {
"assignment": "1",
"course": "networking"
},
"headers": {
"Host": "httpbin.org",
"User-Agent": "Concordia-HTTP/1.0"
},
"url": "http://httpbin.org/get?course=networking&assignment=1"
}
```

##### POST with inline
```
httpc post -h Content-Type:application/json -d '{"Assignment": 1}' http://httpbin.org/post
{
"args": {},
"data": "{\"Assignment\": 1}",
"files": {},
"form": {},
"headers": {
"Content-Length": "17",
"Content-Type": "application/json",
"Host": "httpbin.org",
"User-Agent": "Concordia-HTTP/1.0"
},
"json": {
"Assignment": 1
},
"url": "http://httpbin.org/post"
}
```