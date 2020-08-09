# High-availability and automatic fail-over with vert.x: pros and cons

The goal is to demonstrate vert.x approach to build distributed highly-available (HA) systems with automatic fail-over (AFO) and argue pros and cons.
 
## Plan
 - Self-Introduction
 - Plan announcement & goals statement: 
    - Intro to vert.x
    - Focus on vert.x way to implement high-availability (HA) and automatic fail-over (AFO) 
    - Several attempts to use vert.x HA and AFO with obstacles and workarounds 
    - Conclusions, recommendations
 - Essential intro to vert.x:
   - Reactive
   - High-performant, high-load oriented
   - Based on netty/NIO 
   - Non-blocking and async APIs 
   - Actor Model
   - Microservices natively
   - Polyglot-programming for JVM 
   - No JVM low-level concurrency   
 - vert.x fundamentals:
   - Verticals (actors)
   - Eventbus (messages exchange)
   - Cluster
 - Main use case:
   - Typical distributed system to have HA and AFO: 
     - [source system] --> 
     - [source-connector] --> 
     - [converter] --> 
     - [destination-connector] --> 
     - [destination system]
   - Vanilla demo
 - vert.x approach to HA and AFO:
   - Approach essentials: failure detection and AFO mechanics   
   - Enabling HA and AFO
 - Demos (IDEA, terminals):   
   1. obstacle 1: AFO while verticle byte code is not in the place  
   1. workaround 1.1: _hanode_ approach 
   1. obstacle 2: _poison pill_
   1. workaround 2: poison pill isolation in hagroup (+ workaround 1.2) 
   1. workaround 1.2: custom classloader approach
 - Conclusions, recommendations
 - Q/A
   
## Build hints

To build project use: `./mvnw clean package`

Each vertical is deployed as fat jar inside its own docker container. Then verticals join the cluster. 
 
To run all containers at once use: `docker-compose -f docker-compose.yml up` 
 
## Hints for docker interaction:
 - To initialize docker environment run in terminal: `eval "$(docker-machine env default)`"
 - To kill all containers at once use: `docker kill $(docker ps -q)`
 - To remove all containters at once use:
    1. `docker stop $(docker ps -a -q)`
    2. `docker rm $(docker ps -a -q)`
 - To remove all none images use: `docker rmi $(docker images | grep "^<none>" | awk "{print $3}")`
 - To remove all images use:
    1. `docker rm $(docker ps -a -q)`
    2. `docker rmi $(docker images -q)`
 
## Hints for docker-compose interaction: 
 - `docker-compose up -d`
 - `docker-compose ps`
 - `docker-compose stop`
 - `docker-compose up`
 - `docker-compose scale producer=5`
 - `docker-compose logs -f`