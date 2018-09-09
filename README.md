States Docker Demo
==================

A demo of using multiple Docker containers to stand up  
an nginx website running PHP with a Redis data store backend.

To start
--------
    docker-compose up -d

To view
-------
Visit http://localhost/.

To populate the data store
--------------------------
    cat states.txt | redis-cli --pipe

To stop and reset the containers
--------------------------------
    docker-compose stop
    docker-compose rm -f

To interactively connect without the redis-cli installed
--------------------------------------------------------
    docker run -it --link states_redis_1:cli --net states_default --rm redis redis-cli -h redis -p 6379

