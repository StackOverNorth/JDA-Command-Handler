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
 * The core command class which is used to retrieve all information set via
 * {@link CommandHandlerBuilder} by the user. Using the builder will create a new
 * instance of this class, which upon creating, can be used to get the needed information.
 * <p>
 * Note that this class is not directly used to actually create a new command. These methods can be used when implementing
 * {@link CommandListener}.
 * </p>
 */
public class Command {

  private CommandBuilder commandBuilder;

  public Command(CommandBuilder commandBuilder) {
	this.commandBuilder = commandBuilder;
  }

  /**
   * Returns the exact name of the command (without the prefix).
   *
   * @return The command name as String
   */
  public String getCommandName() {
	return commandBuilder.commandName;
  }

  /**
   * Returns the alias of the command.
   * <p>
   * Note that only one alias is possible thus it's returning a single alias, not multiple. If not set, it can be
   * <code>null</code>.
   * </p>
   *
   * @return The alias of the command name as String
   */
  public String getCommandAlias() {
	return commandBuilder.commandAlias;
  }

  /**
   * Returns the description of the command.
   * <p>
   * Note that this can be <code>null</code> if not set by the user.
   * </p>
   *
   * @return The description of the command
   */
  public String getCommandDescription() {
	return commandBuilder.commandDescription;
  }

  /**
   * Returns a boolean whether the command should filter bot usages of the command.
   * <p>
   * Note that this is defaulted to <code>false</code>.
   * </p>
   *
   * @return A boolean whether bots are allowed to use the command
   */
  public Boolean getBotAllowance() {
	return commandBuilder.botReply;
  }

  /**
   * Returns a list of channels where the command can be used.
   * <p>
   * Note that this list can also be empty or contains only one element.
   * </p>
   *
   * @return An {@link ArrayList} of allowed channels
   */
  public ArrayList<Long> getCommandChannels() {
	return commandBuilder.commandChannels;
  }

  /**
   * Returns a list of permissions needed to execute the command.
   * <p>
   * Note that this list can also be empty or contains only one element.
   * </p>
   *
   * @return An {@link ArrayList} of needed {@link Permission}
   */
  public ArrayList<Permission> getCommandPermissions() {
	return commandBuilder.commandPermissions;
  }

  /**
   * Returns the class which implements {@link CommandListener}.
   *
   * @return The class implementing the listener
   */
  public CommandListener getHandlerListener() {
	return commandBuilder.handlerListener;
  }
}
