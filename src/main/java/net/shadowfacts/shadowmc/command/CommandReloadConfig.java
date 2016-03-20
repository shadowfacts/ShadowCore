package net.shadowfacts.shadowmc.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.text.TextComponentString;
import net.shadowfacts.shadowmc.config.ConfigManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Command to reload all or one of the configs registered with the ConfigManager
 * @author shadowfacts
 */
public class CommandReloadConfig implements SubCommand {

	public static CommandReloadConfig instance = new CommandReloadConfig();

	@Override
	public String getCommandName() {
		return "reloadConfig";
	}

	@Override
	public void handleCommand(ICommandSender sender, String[] args) throws CommandException {

		if (args.length <= 1 || args.length >= 3) {
			throw new WrongUsageException("Incorrect amount of arguments. Type /shadow help reloadConfig for more information.");
		}

		if (args[1].equals("all")) {
			ConfigManager.instance.loadAll();
			sender.addChatMessage(new TextComponentString("Configs reloaded successfully, the game may need to be restarted for some changes to take effect."));
		} else if (ConfigManager.instance.isConfigLoaded(args[1])) {
			ConfigManager.instance.load(args[1]);
			sender.addChatMessage(new TextComponentString("Config reloaded successfully, the game may need to be restarted for some changes to take effect."));
		} else {
			sender.addChatMessage(new TextComponentString("That was not a valid config, use /shadow help reloadConfig for more information."));
		}

	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
		List<String> list = new ArrayList<>(ConfigManager.instance.getLoadedConfigs());
		list.add("all");
		return list;
	}

	@Override
	public void handleHelpRequest(ICommandSender sender, String[] args) {
		sender.addChatMessage(new TextComponentString("Reloads one or all of the configs registered with the ShadowCore config manager. Use tab completion to view a list of registered configs."));
	}

}
