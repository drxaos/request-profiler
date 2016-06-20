# request-profiler

Self-profiling web server example

## Output
```
### /example/url ###
>com/github/drxaos/profiler/example/ExampleServlet::doGet - 3000ms
>>com/github/drxaos/profiler/example/Sleeper::exec - 2000ms
>>com/github/drxaos/profiler/example/ExampleService::handle - 1000ms
>>>com/github/drxaos/profiler/example/Sleeper::exec - 1000ms
----------
```
