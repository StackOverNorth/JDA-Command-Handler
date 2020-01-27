[![Latest Release](https://img.shields.io/github/v/release/StackOverNorth/JDA-Command-Handler?label=latest%20release
)](https://github.com/StackOverNorth/JDA-Command-Handler/releases/latest)
![Issues](https://img.shields.io/github/issues/StackOverNorth/JDA-Command-Handler)
![Stars](https://img.shields.io/github/stars/StackOverNorth/JDA-Command-Handler)
![Downloads](https://img.shields.io/github/downloads/StackOverNorth/JDA-Command-Handler/total?color=Light)
[![License](https://img.shields.io/github/license/StackOverNorth/JDA-Command-Handler)](https://github.com/StackOverNorth/JDA-Command-Handler/blob/master/LICENSE)
[![Discord](https://img.shields.io/discord/285623631042707457?color=%23738adb&label=%20&logo=Discord&logoColor=white
)](https://discordapp.com/invite/cGsQvTs)
# JDA Command Handler
This repository strives to make your daily life using commands for the popular JDA wrapper for Discord easier.
This simple yet powerful library allows you to create commands with a few lines of code - it does everything else
 for you.
 
## And how does this fuzz work?
In order to get this library to work, you only need to follow a few simple steps.
1. Make sure you [downloaded and installed](https://github.com/DV8FromTheWorld/JDA#download) the latest version of the JDA wrapper to your project
2. Add this library to your project either by [downloading the latest version](https://github.com/StackOverNorth/JDA-Command-Handler/releases/latest) and adding the jar to your project build
 path or by adding a Maven dependency to your `pom.xml`
 
 **Maven:**
 ```xml
<dependency>
  <groupId>com.github.stackovernorth</groupId>
  <artifactId>JDA-Command-Handler</artifactId>
  <version>1.0</version>
</dependency>
```
Once you added the `jar` file or the Maven dependency to your project, you can start creating a new command handler.
```java
CommandHandler commandHandler = new CommandHandlerBuilder(yourJDAObject)
    .setPrefix("p!")
    .build();
```
The snippet above will only create a new handler with your `JDA` object (note that it is mandatory to include your
`JDA` object) with a custom prefix all your commands will listen to.

## Adding commands example
Adding new commands is just as easy as creating the handler. In order to easy create a new command, I also added a
 builder for commands. You may either use the `addCommand()` class when creating the `CommandHandler` or add it
  afterwards. The following two examples will illustrate how both methods work (but as simplified as possible).
  
**Adding a command when building a `CommandHandler`:**
```java
CommandHandler commandHandler = new CommandHandlerBuilder(yourJDAObject)
  .setPrefix("p!")
  .addCommand(
    new CommandBuilder("testCommand", theHandlerClass).build();
  ).build();
```
**Adding a command after building a `CommandHandler`:**
```java
// Requires the command handler object to be accessible
commandHandler.addCommand(
  new CommandBuilder("testCommand", theHandlerClass).build();
);
```
In both examples we created a new command called `testCommand` which will be triggered when writing a message with
 the set prefix beforehand. So the command with prefix which triggers the execution would be `p!testCommand`.
 
More examples can be found in the [example directory](https://github.com/StackOverNorth/JDA-Command-Handler/tree/master/src/examples/).
## And what the hell is `theHandlerClass`?
As you probably noticed, in order to build a command you must include a **name and handler class**. But no worries! A
 handler class is just as easily created as a command.
 
 The only thing you need to do is do `implement` the `CommandListener` class to any of your class. You'll be forced
  to add the `onCommand` method to this class. This method will be triggered once someone executes the command.
  
 ```java
public class MyListenerClass implements CommandListener {
  @Override
    public void onCommand(Member member, TextChannel textChannel, Message message) {
      // Do your desired repsonse in here.
    }
}
```
As you can see, once implemented you can do whatever you want in this method. This method will only be called when
 other tests pass.
 
## A list of available methods
Available configuration methods for `CommandHandler`:

Method | Description
------ | -----------
`setPrefix(String prefix)` | Sets the prefix which will be used in combination with the command (name).
`addCommand(Command command)` | Adds a command with the corresponding handler class.

Available configuration methods for `Command`:

Method | Description
------ | -----------
`setAlias(String commandAlias)` | Sets an alias for the command name.
`setDescription(String commandDescription)` | Adds a description for the command.
`setUsage(String commandUsage)` | Adds an usage example to your command.
`addAllowedChannel(long channelId)` | Adds a single channel where the command can be executed. <br><br><small>Note that this takes a channelId as long as parameter.<br>Example: `addAllowedChannel(123456789L)`</small>
`addPermission(Permission permissionId)` | Adds a single permission which is required to execute the command.
`allowBotReply(boolean botReply)` | Defines whether bots should be able to execute the command.
<small>* *Please note that all methods above are optional*</small>

## Any questions or enhancements?
In case you're running into any issues with my library, have any questions or just want to make a suggestion for
 further releases, please let me know.
 
 You can either open a new ticket over [here](https://github.com/StackOverNorth/JDA-Command-Handler/issues), or join
  the [Discord server](https://discordapp.com/invite/cGsQvTs) and ask your question over there.