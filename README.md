## CoffeeScript example for Java Servlet apps

Are you Java developer? No problem, you can use CoffeeScript in your Serlvet apps.

![screenshot](https://raw.github.com/seratch/ninja-coffee-example/master/screenshot.png)

### CoffeeScript

http://coffeescript.org/

CoffeeScript is a little language that compiles into JavaScript. Underneath that awkward Java-esque patina, JavaScript has always had a gorgeous heart. CoffeeScript is an attempt to expose the good parts of JavaScript in a simple way.

### wro4j

https://code.google.com/p/wro4j/

Web Resource Optimizer for Java - wro4j

https://code.google.com/p/wro4j/wiki/CoffeeScriptSuport

The coffeeScript support is provided by the CoffeeScriptProcessor. This processor can be used as both: pre & post processor. Its purpose is to read the coffeeScript content and compile it into javascript. The underlying implementation use Rhino compiler for processing, because the original coffeeScript compiler is implemented in javascript. The processor works this way:

- it tries to apply the coffeeScript compiler on the processed resource
- if the compiler fails during processing, the result will be unchanged (and a warning will be logged). This will ensure that you can use coffeeScript and javascript resources at the same time, as long as coffeeScript is used as a pre processor. When using it as a post processor will work only if the merged content of resources is a valid coffeeScript.
- if the coffeeScript compiler succeed, the compiled javascript code is used as output result.

### Ninja Framework

http://www.ninjaframework.org/

Full stack web framework for Java. Fast and productive. Runs on servlet containers - built with Maven.

## How to run?

    mvn clean jetty:run

And then, access http://localhost:8080/ from your browser.

## Files

```
.
├── README.md
├── app
│   ├── assets
│   │   ├── css
│   │   │   ├── bootstrap-responsive.min.css
│   │   │   └── bootstrap.min.css
│   │   └── js
│   │       ├── jquery.min.js
│   │       └── snippet.coffee
│   ├── conf
│   │   ├── Module.java
│   │   ├── Routes.java
│   │   ├── WEB-INF
│   │   │   ├── web.xml
│   │   │   └── wro.xml
│   │   ├── application.conf
│   │   └── messages.properties
│   ├── controllers
│   │   └── RootController.java
│   ├── models
│   │   └── Person.java
│   └── views
│       ├── RootController
│       │   └── index.ftl.html
│       └── layout
│           ├── defaultLayout.ftl.html
│           ├── footer.ftl.html
│           └── header.ftl.html
└── pom.xml
```

