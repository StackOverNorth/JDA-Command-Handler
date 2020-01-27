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

package com.github.stackovernorth.jda.commandhandler.listener;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

/**
 * In order to be able to handle incoming commands, this interface needs to be implemented.
 */
public interface CommandListener {

  /**
   * This method will be triggered once someone executes the corresponding command.
   * <p>
   * As of now, this will only be triggered if the sender passed the checks (whether the channel the message was sent
   * from is valid and whether he has the required permissions).
   * </p>
   * <p>
   * Here you can do whatever you want once someone executes the commands.
   * </p>
   *
   * @param sender
   * 		The sender of the message
   * @param channel
   * 		The channel the message was sent from
   * @param message
   * 		An object of the message
   * @param args
   * 		An array of the message split at every whitespace and prefix plus command removed.
   * 		Note that args can be empty if no other content was provided but the prefix and command.
   */
  void onCommand(Member sender, TextChannel channel, Message message, String[] args);
}
