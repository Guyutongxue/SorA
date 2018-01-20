# Extensions in SorA

Extensions here released under the **Apache License 2.0**.

## Build

To build these extensions, you need to clone [the source of App Inventor 2](https://github.com/mit-cml/appinventor-source) .Then you need to install **Apache-Ant** (<=1.9.9) and [**JDK** (7)](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html) .

Change working directory to the Git repo. Run the command below:

```
$ cd appinventor
$ ant MakeAuthKey
```

Copy the `cn` folder to `components/src`. At last build by typing:

```
$ ant extensions
```
The .aix file's location will be printed.


## RichListView

This file was decompiled from ColinTree's website. I change the class name and package name, fix some bugs and add some code (to compatible SorA).

The `RichListView` is a non-visible components. It only add components in a `VerticalArrangement` or `VerticalScrollArrangement`, so you should initialize it first.

The element of it is a list, which can be in 4 format:

1. { Text }
2. { IconPath, Text }
3. { IconPath, MainText, SubText }
4. { `""`, MainText, Subtext }

Also it has events of `Click`, `LongClick`, `TouchDown` and `TouchUp` in both Icon or Text.

## TinyDBConverter

The `TinyDVConverter` is a non-visible components made by Guyutongxue. It provide functions to convert DB from/to a JSON string.

There are four SimpleFunction included:

- TinyDBToJson
- JsonToTinyDB
- TinyDBToBase64String
- Base64StringToTinyDB