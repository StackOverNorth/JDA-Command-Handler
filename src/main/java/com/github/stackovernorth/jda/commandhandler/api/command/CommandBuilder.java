/*
 * MIT License
 *
 * Copyright (c) 2020 StackOverNorth (via https://github.com/StackOverNorth)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.stackovernorth.jda.commandhandler.api.command;

import com.github.stackovernorth.jda.commandhandler.api.handler.CommandHandlerBuilder;
import com.github.stackovernorth.jda.commandhandler.listener.CommandListener;
import net.dv8tion.jda.api.Permission;

import java.util.ArrayList;

/**
 * This class is mandatory in order to build a functionally command.
 * <p>
 * Only this class can be used to actually create a new command. This class follows a builder pattern thus you need to
 * create a new instance of this builder and use the methods to actually build to command which fits your needs.
 * </p>
 * <p>
 * Note that you need to add a valid {@link #commandName} and {@link #handlerListener} in order to be able to build the
 * command. When building, the builder will return a new instance of {@link Command} with all the information you provided.
 * </p>
 * <p>
 * An short example on how to create a command.
 * <code>Command newCommand = new CommandBuilder("myCommand").addListener(new MyClassThatImplementsCommandListener()
 * .build();</code>
 * </p>
 */
public class CommandBuilder {

  String commandName;
  String commandAlias = "";
  String commandDescription;
  Boolean botReply = false;
  ArrayList<Long> commandChannels = new ArrayList<>();
  ArrayList<Permission> commandPermissions = new ArrayList<>();
  CommandListener handlerListener;

  /**
   * Starts the building process of your new command.
   * <p>
   * Note that you need to include a valid String containing your command name and a class that handles your command which
   * implements {@link CommandListener}.
   * </p>
   *
   * @param commandName
   * 		A String which will be used as your command name in combination with the prefix set in
   *        {@link CommandHandlerBuilder}
   * @param handlerListener
   * 		A class implementing {@link CommandListener} which handles the command
   *
   * @throws IllegalArgumentException
   * 		If {@link #commandName} or {@link #handlerListener} is <code>null</code>
   */
  public CommandBuilder(String commandName, CommandListener handlerListener) {
	if (commandName == null || handlerListener == null) {
	  throw new IllegalArgumentException("The command name or listener must not be null!");
	}

	this.commandName = commandName;
	this.handlerListener = handlerListener;
  }

  /**
   * Sets an alias for the command name.
   * <p>
   * An alias is an alternate name for your command. Using an alias will allow you to use two names for the same
   * output. E.g. <code>help</code> and <code>info</code> will display the same output when info is the alias of
   * <code>help</code>.
   * </p>
   * <p>
   * This variable is defaulted to <code>null</code>.
   * </p>
   *
   * @param commandAlias
   * 		A String which will be used as the alternate name of your command
   *
   * @return <code>this</code> for chaining
   */
  public CommandBuilder setAlias(String commandAlias) {
	this.commandAlias = commandAlias;

	return this;
  }

  /**
   * Adds a description to your command.
   * <p>
   * This variable is defaulted to <code>null</code>.
   * </p>
   *
   * @param commandDescription
   * 		A String which will be used as the description of the command
   *
   * @return <code>this</code> for chaining
   */
  public CommandBuilder setDescription(String commandDescription) {
	this.commandDescription = commandDescription;

	return this;
  }

  /**
   * Adds a single channel where the command can be executed.
   * <p>
   * By default the list is empty.
   * </p>
   *
   * @param channelId
   * 		The corresponding channelId as Long
   *
   * @return <code>this</code> for chaining
   */
  public CommandBuilder addAllowedChannel(long channelId) {
	commandChannels.add(channelId);

	return this;
  }

  /**
   * Adds a single permission which is required to execute the command.
   *
   * @param permissionId
   * 		A {@link Permission} which is required to execute the command
   *
   * @return <code>this</code> for chaining
   */
  public CommandBuilder addPermission(Permission permissionId) {
	commandPermissions.add(permissionId);

	return this;
  }

  /**
   * Defines whether bots should be able to execute the command.
   * <p>
   * By default bots are not allowed to execute command. So you only need to use this if you want bots to be able to
   * execute your command.
   * </p>
   *
   * @param botReply
   * 		Either <code>true</code> so that bots can use the command or <code>false</code> if they cannot
   *
   * @return <code>this</code> for chaining
   */
  public CommandBuilder allowBotReply(boolean botReply) {
	this.botReply = botReply;

	return this;
  }

  /**
   * Ends your building and creates a new instance of {@link Command} containing all the information about the command.
   *
   * @return A new instance of {@link Command} with the newly created command
   */
  public Command build() {
	return new Command(this);
  }
}
