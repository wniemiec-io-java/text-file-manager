![](https://github.com/wniemiec-io-java/text-file-manager/blob/master/docs/img/logo/logo.jpg)

<h1 align='center'>Text File Manager</h1>
<p align='center'>Read and write text files.</p>
<p align="center">
	<a href="https://github.com/wniemiec-io-java/text-file-manager/actions/workflows/windows.yml"><img src="https://github.com/wniemiec-io-java/text-file-manager/actions/workflows/windows.yml/badge.svg" alt=""></a>
	<a href="https://github.com/wniemiec-io-java/text-file-manager/actions/workflows/macos.yml"><img src="https://github.com/wniemiec-io-java/text-file-manager/actions/workflows/macos.yml/badge.svg" alt=""></a>
	<a href="https://github.com/wniemiec-io-java/text-file-manager/actions/workflows/ubuntu.yml"><img src="https://github.com/wniemiec-io-java/text-file-manager/actions/workflows/ubuntu.yml/badge.svg" alt=""></a>
	<a href="https://codecov.io/gh/wniemiec-io-java/text-file-manager"><img src="https://codecov.io/gh/wniemiec-io-java/text-file-manager/branch/master/graph/badge.svg?token=R2SFS4SP86" alt="Coverage status"></a>
	<a href="http://java.oracle.com"><img src="https://img.shields.io/badge/java-11+-D0008F.svg" alt="Java compatibility"></a>
	<a href="https://mvnrepository.com/artifact/io.github.wniemiec-io-java/text-file-manager"><img src="https://img.shields.io/maven-central/v/io.github.wniemiec-io-java/text-file-manager" alt="Maven Central release"></a>
	<a href="https://github.com/wniemiec-io-java/text-file-manager/blob/master/LICENSE"><img src="https://img.shields.io/github/license/wniemiec-io-java/text-file-manager" alt="License"></a>
</p>
<hr />

## ❇ Introduction
Text File Manager performs operations with text files simply and easily.

## ❓ How to use
1. Add one of the options below to the pom.xml file: 

#### Using Maven Central:
```
<dependency>
  <groupId>io.github.wniemiec-io-java</groupId>
  <artifactId>text-file-manager</artifactId>
  <version>LATEST</version>
</dependency>
```

2. Run
```
$ mvn install
```

3. Use it
```
[...]

import wniemiec.io.java.TextFileManager;

[...]

List<String> content = List.of("hello", "world!");
Path targetFile = Path.of("example-file.txt");

TextFileManager txtFileManager = new TextFileManager(targetFile, StandardCharsets.ISO_8859_1);
txtFileManager.writeLines(content);

System.out.println( txtFileManager.readLines() );

[...]
```


## 📖 Documentation
|        Property        |Parameter type|Return type|Description|Default parameter value|
|----------------|-------------------------------|-----|------------------------|--------|
|readLines |`void`|`List<String>`|Gets all lines from a file and puts them in a list| - |
|writeLines |`lines: List<String>`|`void`|Writes all items in a string list to a file| - |
|getFileLineThatContains |`sequence: CharSequence`|`String`|Searches for a line that contains a sequence| - |

## 🚩 Changelog
Details about each version are documented in the [releases section](https://github.com/williamniemiec/wniemiec-io-java/text-file-manager/releases).

## 🤝 Contribute!
See the documentation on how you can contribute to the project [here](https://github.com/wniemiec-io-java/text-file-manager/blob/master/CONTRIBUTING.md).

## 📁 Files

### /
|        Name        |Type|Description|
|----------------|-------------------------------|-----------------------------|
|dist |`Directory`|Released versions|
|docs |`Directory`|Documentation files|
|src     |`Directory`| Source files|
