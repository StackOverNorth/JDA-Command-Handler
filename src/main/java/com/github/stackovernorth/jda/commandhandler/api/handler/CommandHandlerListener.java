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
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * This class superclasses JDA's {@link ListenerAdapter} in order to handle incoming commands.
 */
class CommandHandlerListener extends ListenerAdapter {

  private final CommandHandlerBuilder commandHandlerBuilder;

  CommandHandlerListener(CommandHandlerBuilder commandHandlerBuilder) {
    this.commandHandlerBuilder = commandHandlerBuilder;
  }

  @Override
  public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
    String[] args = event.getMessage().getContentRaw().split("\\s+");
    String commandPrefix = commandHandlerBuilder.prefix;
    if (!args[0].startsWith(commandPrefix)) {
      // If the messages does not start with the set prefix, simply do nothing.
      return;
    }

    // If the message does start with the set prefix, we need to check whether the command was registered.
    // If the command was registered, the command will be handled and checked in the handleCommand() method.
    String command = args[0].replace(commandPrefix, "");
    commandHandlerBuilder.commandList.forEach(c -> {
      if (c.getCommandName().equals(command) || c.getCommandAlias().equals(command)) {
        handleCommand(c, event.getMember(), event.getChannel(), event.getMessage(), args);
      }
    });
  }

  /**
   * This method handles commands and redirects them to the corresponding handler class.
   * <p>
   * Additionally this method checks for the configuration set in {@link CommandHandler} and won't execute the command
   * if anything does not match the configuration.
   * </p>
   *
   * @param command
   *     The command which is will be executed
   * @param sender
   *     The sender of the text message
   * @param channel
   *     The channel the text message was sent from
   * @param message
   *     The message as object
   * @param args
   *     An array of the message split at each whitespace
   */
  private void handleCommand(Command command, Member sender, TextChannel channel, Message message, String[] args) {
    // If set hat bot's should not be allowed to use the command, do nothing.
    if (!command.getBotAllowance() && message.getAuthor().isBot()) {
      return;
    }

    // Afterwards we need to check whether the message was sent from an allowed channel and whether the sender has the
    // permissions to actually execute the command.
    ArrayList<Long> allowedChannels = command.getCommandChannels();
    if (!allowedChannels.isEmpty() && !allowedChannels.contains(channel.getIdLong())) {
      return;
    }

    ArrayList<Permission> neededPermissions = command.getCommandPermissions();
    if (!neededPermissions.isEmpty() && !sender.getPermissions().containsAll(neededPermissions)) {
      return;
    }

    // Once we checked everything, the can redirect the command to the corresponding class.
    command.getHandlerListener().onCommand(sender, channel, message, Arrays.copyOfRange(args, 1, args.length));
  }
}
