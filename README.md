# miniREST
This library was made to create a starting point for anyone wanting to work with webAPIs without having to learn bloated libraries or having to piece togheter your own requests.
# functionality
at this time, miniREST only features basic url requests with as many stringed arguments as you want and basic support for passing one-dimensional lists.
Responses are returned as plain text. If you want to process jsons (which are most commonly used to respond to webAPI requests) I suggest you try out
the json.org library. Its really easy to use and by combining these two libraries you can get up and running and start pulling basic data or even 
writing implementations of API clients pretty quickly.
# basic usage
miniREST has one main type, that you can use to issue API requests called RESTRequester. Just instanciate it with your api url and an instance of an httpReader (basic class included in the package).
To issue a request, simply use the issueRequest method. You can pass your arguments as key value pairs in a Hashlist and your desired endpoint as a string (like this: 
path/to/endpoint/endpoint dont include the URL!). If you want to pass one dimensional Lists, use the stringToURLArray on your list of Strings and pass the result as the value
of an arguments key.
