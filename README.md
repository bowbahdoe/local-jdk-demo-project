## What is this?

I am working on a prototype of a non-maven module-focused repository and dependency procurement tooling.
This is heavily experimental, but I am working on it with the intent that it be a tool for *me* to use.

The flow is that, instead of downloading dependencies into a folder or threading them into a `--class-path`,
everything is linked together into a project-specific JDK. You can then just use that JDK directly.

The part of this I think is neatest is that dependencies can 
bring both native libraries (without jank shims) and command-line tools if they are packaged as `jmod`s.

I am sharing this to elicit feedback.

## Getting Started

```bash 
java -jar jvm.jar link
```


To set up your editor: Add the `jvm` folder as your project's JDK.

You can also add `jvm/bin` to your path to skip the prefix on the following commands.

Then try each of these paths:

### Run directly

``` 
jvm/bin/java --enable-native-access javafx.graphics src/Main.java
```

### Running via an argument file

```
jvm/bin/java @run
```

### Compiling via a "normal" Java program

```
jvm/bin/java dev/Project.java compile
jvm/bin/java dev/Project.java run
```

### Compiling via a Justfile

```
jvm/bin/just compile
jvm/bin/just run
```

## Notes:

* Right now the jar, xml file, etc. are called "jvm" - as in "jvm version manager." I don't know if that name is fine. 
"jvmvm" is the backup, but open to suggestions
* If you are reading this more than a few days out I might have broken everything behind the scenes. I am experimenting
fairly liberally.
* The idea for *distribution* is to use the jlinked image to jlink yet another image for the target platform. This is
not done yet.
* There is a whole non-maven repo in the background here, also very much not done and unpolished.
  * The `did` and `handle` on the `<provider>` are related to this. Part of the idea is to separate publishing from a particular repository (though repositories could have downstream opinions on who is allowed to publish what.)
* Dependencies are declared in the `jvm.xml` file.
* You could also just add `.jvm/modules` as a library directory, but that wouldn't work for JMODs
* A repository can be different from an index because modules are addressed by hashes. You could in principle fetch artifacts
from places like IPFS.
* I didn't actually push the JMOD javafx artifacts (though that would be a better demo)
* Downloading the whole index every time isn't ideal but also works fine for me at this point.
