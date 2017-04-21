# Basic Service - Add list of numbers to get summation

# Http Basic Authentication - 

# Http header addition

# IP validation - to accept request from known clients only

# SOAP header addition at client end

# Add SOAP header verification at server end

# WS Security via WSS4j (on user name & password)

# WS Addressing

A health adviser system that suggests recipes based on symptoms, with two primary actors: the server that 
prescribes recipes (Eclipse Web Project HealthProcessorServer) and a client application that simulates
requests (Eclipse Java Project HealthProcessorClient). We suppose that the traditional recipes can be
provided immediately while the alternative ones require a more complex process that forces an asynchronous scenario.

How does it work?

The client sends the request with an indication (ReplyTo header) that he wants the response to a third actor 
- the callback endpoint. The server, having built-in support for WS-Addressing, replies immediately with an 
HTTP 202 status which informs the client that the request has arrived and its processing has started. 
When the task is finished the server sends a request to the callback endpoint (listening at ReplyTo address). 
This is the response that the client expects. The response will be correlated with the corresponding request
 using the RelatesTo header.
