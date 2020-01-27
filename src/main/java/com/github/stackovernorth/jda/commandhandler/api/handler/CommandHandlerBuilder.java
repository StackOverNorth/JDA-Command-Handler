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

package com.github.stackovernorth.jda.commandhandler.api.handler;

import com.github.stackovernorth.jda.commandhandler.api.command.Command;
import com.github.stackovernorth.jda.commandhandler.listener.CommandListener;
import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;

/**
 * This class is used to initialize the command handler.
 * <p>
 * This class only needs to be used once to construct a new instance of the command handler. In this class you can
 * configure the prefix and add custom commands.
 * </p>
 */
public class CommandHandlerBuilder {

  protected ArrayList<Command> commandList = new ArrayList<>();
  JDA jdaObject;
  String prefix;

  /**
   * Starts the building process of the command handler.
   *
   * @param jdaObject
   * 		Your {@link JDA} object which is mandatory for the handler to work
   *
   * @throws IllegalArgumentException
   * 		If your provided JDA object is null
   */
  public CommandHandlerBuilder(JDA jdaObject) {
	if (jdaObject == null) {
	  throw new IllegalArgumentException("Your JDA object must not be null");
	}

	this.jdaObject = jdaObject;
  }

  /**
   * Sets the prefix which will be used in combination with the command (name).
   * <p>
   * Example: Setting the prefix to <code>p!</code> means every command needs to start with <code>p!</code> followed by
   * the name of the command. Thus if you have a command named <code>help</code> you need to write <code>p!help</code>
   * in order to execute the command's class.
   * </p>
   *
   * @param prefix
   * 		A String which will be used as general prefix for the commands
   *
   * @return <code>this</code> for chaining
   */
  public CommandHandlerBuilder setPrefix(String prefix) {
	this.prefix = prefix;

	return this;
  }

  /**
   * Adds a command with the corresponding class.
   *
   * @param commandClass
   * 		The listener class which handles the output of the command and implements
   *        {@link CommandListener}
   *
   * @return <code>this</code> for chaining
   */
  public CommandHandlerBuilder addCommand(Command commandClass) {
	commandList.add(commandClass);

	return this;
  }

  /**
   * Ends the building process and creates a new instance of {@link CommandHandler} with all your set information.
   *
   * @return A new instance of the command handler
   */
  public CommandHandler build() {
	return new CommandHandler(this);
  }
}
