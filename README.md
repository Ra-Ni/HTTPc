# COMP 445 LAB 1

## General
httpc is a curl-like application but supports HTTP protocol only.

## Usage
httpc request [arguments]

	- get: executes a HTTP GET request and prints the response.
 	- post: executes a HTTP POST request and prints the response.
	- help: prints this screen.

**Use "httpc help [request]" for more information about a request.**

### GET Usage
httpc get [-v] [-h key:value] URL

- -v: prints detail of response (protocol,status, and headers)
- -h key:value: Associates headers to HTTP request with the format  'key:value'

### POST Usage
httpc post [-v] [-h key:value] [-d inline-data] [-f file] URL

- -v : prints detail of response (protocol,status, and headers)
- -h key:value : Associates headers to HTTP request with the format  'key:value'
- -d string : Associates inline data to the body HTTP POST request
- -f file : Associates the content of a file to the body HTTP POST request

**Either [-d] or [-f] can be used, but not both.**

## Demo
### GET
**httpc get -v 'http://httpbin.org/get?course=networking&assignment=1'**
```
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

**httpc get 'http://httpbin.org/get?course=networking&assignment=1'**
```
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

### POST
**httpc post -h Content-Type:application/json -d '{"Assignment": 1}' http://httpbin.org/post**
```
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