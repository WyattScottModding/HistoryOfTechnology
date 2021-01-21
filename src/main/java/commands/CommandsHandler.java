package commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import main.History;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class CommandsHandler {
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralCommandNode<CommandSource> historyCommands = dispatcher.register(
				Commands.literal(History.modid)
					//.then(Command.register(dispatcher))
				);
		
		//Here you can add other names that will autofill in the full name
		dispatcher.register(Commands.literal("his").redirect(historyCommands));
	}
}