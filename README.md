Directions
==========

This mini project fetches driving directions using the Google Maps Directions API. It also contains custom JSON deserialization code (GSON).

Given a pair of addresses: origin & destination, you can get a nicely formatted list of driving directions.

Example
=======

Let the addresses be as follows:

    String origin = "340 University Ave, Palo Alto, CA";
    String destination = "601 Nelson Rd, Stanford, CA";

This code segment shows how you can fetch driving directions between these 2 addresses:

    HttpManager manager = new HttpManager();
    manager.setAddresses(new String[]{origin, destination});
    Route route = manager.fetchDirections(); // Time to fetch the driving directions!
    System.out.println(route.toString());

The output looks as follows:

> Summary: University Ave
    Distance: 2074; Duration: 296
    1. Head southwest on University Ave toward Florence St (Distance: 680; Duration: 123)
2: Turn left to merge onto CA-82 S/El Camino Real (Distance: 647; Duration: 76)
3: Turn right onto Galvez St (Distance: 555; Duration: 55)
4: Turn left onto Nelson Rd (Distance: 192; Duration: 42)

