# json-to-mongo

A simple Clojure app to load a JSON file into Mongodb.

## Usage

Get the dependencies and run with Leiningen:

    lein deps

    lein run -- -db <Database Name> -c <Collection Name> <File Location>

There are additional options that you can set, run help for those details:

	lein run -- --help

## Notes

This is heavily tailored for internal use, but you can modify the run function to parse out your JSON as you need.

I'm sure there is a much better way to parse out the JSON than what I have done, so feel free to tear it apart and let me know a better way. I'm makeing this available to serve more as a shell for your own use versus a one size for all solution. 

The current run function reads the JSON files into memory, if you are processing a large file you are going to want to modify how the app reads the file.

This was the result of a quickly needed solution and is not to be considered production quality. Further development is not likely to take place.

## License

Copyright © 2013 

Distributed under the Eclipse Public License, the same as Clojure.
