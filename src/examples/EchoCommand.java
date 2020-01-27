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

import com.github.stackovernorth.jda.commandhandler.api.command.CommandBuilder;
import com.github.stackovernorth.jda.commandhandler.api.handler.CommandHandler;
import com.github.stackovernorth.jda.commandhandler.api.handler.CommandHandlerBuilder;
import com.github.stackovernorth.jda.commandhandler.listener.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

/**
 * A simple example on how to use this extension.
 * <p>
 * This class will simply echo the message a user types.
 * </p>
 */
public class EchoCommand implements CommandListener {

  EchoCommand(JDA jda) {
	// Now we will construct a new command handler using the builder with our newly created JDA object.
	// As of now, we do nothing but setting our global prefix to "!".
	CommandHandler commandHandler = new CommandHandlerBuilder(jda)
			.setPrefix("!").build();

	// Now we are able to add a command to our handler.
	commandHandler.addCommand(
			// Firstly, we initialize a new CommandBuilder object which takes two parameters.
			// The first parameter is the command name and the second parameter the listener class (which implements
			// CommandListener, as it is the case for this class.
			new CommandBuilder("echo", this)
					// Only being able to use "echo" is boring, so we add a custom alternate name (alias) for the command.
					.setAlias("parrot")
					// For further usage, we can add a description what this command does.
					.setDescription("This command simply echos your message. Just like a parrot.")
					// In this case, we do want that bots can use this command, so we will turn it on.
					// If you don't want bot's to use your command, simply do not add this line.
					.allowBotReply(true)
					// Additionally, we only want the command to be executed in our spam channel.
					// Note that this takes a Long parameter, simply add an capital L at the end of the channel id.
					.addAllowedChannel(12345678987456321L)
					// Lastly, only administrators should be able to execute the command.
					.addPermission(Permission.ADMINISTRATOR)
					// Finally, we can build our command.
					.build()
	);
  }

  /*
   * The main method of this class, will be executed upon starting the class.
   */
  public static void main(String[] args) throws LoginException {
	// Construct a new JDA builder with your token.
	JDA jda = new JDABuilder("YOUR-SECRET-TOKEN").build();

	// Now we construct a new instance of this class in order to create our command handler. We are only omitting the JDA
	// object.
	new EchoCommand(jda);
  }

  /*
   * Since we are using this class as command listener for our echo command, this method will be triggered once someone
   * executes the command. If and only if the member has the required permissions (administrator permission) and if the
   * message was sent from our spam channel (as defined above).
   */
  @Override
  public void onCommand(Member member, TextChannel textChannel, Message message, String[] args) {
	// Firstly, we need to check whether args where provided.
	// FYI: Args (alias arguments) are every other content after the prefix and command, e.g. !echo hi I'm part of args
	//		So, everything but "!echo" is included in args. If someone only types "!echo", the array is empty.
	if (args.length >= 1) {
	  // In this case, we are transforming the array into an String, that's why we need to remove the square brackets.
	  textChannel.sendMessage(Arrays.toString(args)
			  .replaceAll("\\[", "")
			  .replaceAll("]", ""))
			  .queue();
	} else {
	  // If nothing but the command was provided, let the user know.
	  textChannel.sendMessage("Cannot echo your message, if you don't tell me what to echo.").queue();
	}
  }
}