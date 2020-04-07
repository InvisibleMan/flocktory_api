# flocktory_api

Task description: https://gist.github.com/sherpc/22409f4184e039ebbd0ebddd3ee59122

## Usage
Launch the application by issuing one of the following commands:

```shell
SERVER_HOST=localhost SERVER_PORT=8080 lein run
```

You can generate a standalone jar and run it:

```shell   
lein uberjar
java -Dserver.host=localhost -Dserver.port=8080 -jar target/flocktory_api-0.1.0-SNAPSHOT-standalone.jar
```

You can also generate a war to deploy on a server like Tomcat, Jboss...

```shell
lein ring uberwar
```

## Dev
Launch the REPL the following commands:
```shell
lein repl
(require '[flocktory_api.model.queue :as q])

```

Then run server:
```shell
(user/go)
(def feed (slurp "./fixtures/scala02.xml"))
```

## License

Copyright © 2020 Denis Larionov
