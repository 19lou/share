
Typesafe Console
================

Typesafe Console for development use.

If you use sbt, see the [sbt-atmos plugin] for an easy way to get started with
Typesafe Console for development.

[sbt-atmos plugin]: https://github.com/sbt/sbt-atmos


## Instrumentation

To gather information about your application, classes are instrumented with
Aspectj. You need to add a java agent to your application, and include the
aspects on the classpath.

The Aspectj Weaver is included in this distribution at:

    lib/weaver/aspectjweaver.jar

And adding this as a java agent requires something like:

    java -javaagent:lib/weaver/aspectjweaver.jar ...

The tracing aspects can be found in the [repo.typesafe.com] maven repository,
and are available for Akka 2.0, 2.1, and 2.2.

Here are the dependency details for Typesafe Console 1.2.1.
The appropriate dependency should be chosen.

Akka 2.0 and Scala 2.9:

    "com.typesafe.atmos" % "trace-akka-2.0.5" % "1.2.1"

Akka 2.1 and Scala 2.10:

    "com.typesafe.atmos" % "trace-akka-2.1.4" % "1.2.1"

Akka 2.2 and Scala 2.10:

    "com.typesafe.atmos" % "trace-akka-2.2.0_2.10" % "1.2.1"

Akka 2.2 and Scala 2.11:

    "com.typesafe.atmos" % "trace-akka-2.2.0_2.11.0-M3" % "1.2.1"

There is a simple sample project included in this distribution which shows
configuring a Maven build and an sbt build to include the Aspectj weaver and
trace dependencies.

[repo.typesafe.com]: http://repo.typesafe.com


## Trace configuration

It's possible to configure which actors in your application are traced, and at
what sampling rates. Configuration uses the [Typesafe Config] library.

The sample project in this distribution has a simple configuration that can be
used as an example, found at `src/main/resources/application.conf`.

At a minimum, tracing should be enabled, a virtual node name given, and actors
selected for tracing. For example:

    atmos {
      trace {
        enabled = true                # enable tracing
        node = AwesomeApplication     # give this node a name

        traceable {
          "/user/someActor" = on      # trace the someActor
          "/user/actors/*"  = on      # trace all actors in this subtree
          "*"               = off     # other actors are not traced
        }

        sampling {
          "/user/someActor" = 1       # sample every trace for someActor
          "/user/actors/*"  = 100     # sample every 100th trace in this subtree
        }
      }
    }

[Typesafe Config]: https://github.com/typesafehub/config


## Running Typesafe Console

There are two processes to run for Typesafe Console. One, called Atmos,
collects and analyzes the trace data. The second process is a Play application
which presents the Typesafe Console user interface.

Atmos, which collects the trace data, needs to be started before your
instrumented application is started.

To start Atmos in an environment that supports bash:

    bin/typesafe-console atmos

To start the Console UI:

    bin/typesafe-console ui

There are also simple batch scripts for using with Windows CMD:

    bin\atmos.bat
    bin\console.bat

When these processes and your instrumented application are running,
the Typesafe Console local web interface is available at
[http://localhost:9900](http://localhost:9900).


## More Information

For more information see the [documentation] for the full production version of
Typesafe Console.

[documentation]: http://resources.typesafe.com/docs/console


## License

Typesafe Console is licensed by the Typesafe Subscription Agreement, included in
this distribution. This version of Typesafe Console is free for development use.
And more information about [licenses] can be found on the Typesafe website.

[licenses]: http://typesafe.com/legal/licenses


## Feedback

We welcome your feedback and ideas for using Typesafe Console for development.
There is a `Send Feedback` link available when running the Typesafe Console web
interface. Or go to [Typesafe Support].

[Typesafe Support]: http://support.typesafe.com
