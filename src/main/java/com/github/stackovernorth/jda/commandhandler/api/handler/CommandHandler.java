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

public class CommandHandler {

  private CommandHandlerBuilder commandHandlerBuilder;

  CommandHandler(CommandHandlerBuilder commandHandlerBuilder) {
    this.commandHandlerBuilder = commandHandlerBuilder;
    commandHandlerBuilder.jdaObject
        .addEventListener(new CommandHandlerListener(commandHandlerBuilder));
  }

  /**
   * Adds a new command.
   * <p>
   * This method can be used when you want to add a new command after already building {@link CommandHandler}.
   * </p>
   *
   * @param commandClass
   *     A class implementing {@link CommandListener} which handles the command
   */
  public void addCommand(Command commandClass) {
    this.commandHandlerBuilder.commandList.add(commandClass);
  }
}
