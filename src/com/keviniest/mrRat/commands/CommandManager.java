package com.keviniest.mrRat.commands;

import com.keviniest.mrRat.MrRat;
import com.keviniest.mrRat.commands.impl.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    public List<Command> commands = new ArrayList<>();
    public static GuildMessageReceivedEvent event;
    public static final int EMBED_COLOR = 0x644E3F;
    public static long cheeseAte = 0L;

    public CommandManager() {
        commands.add(new RSGCommand());
        commands.add(new HelpCommand());
        commands.add(new Gayr8Command());
        commands.add(new InfoCommand());
        commands.add(new EatCheeseCommand());
        commands.add(new PrefixCommand());
        commands.add(new SetPrefixCommand());
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String message = e.getMessage().getContentRaw();
        event = e;

        if(!message.startsWith(String.valueOf(MrRat.prefix))) {
            return;
        }

        message = message.substring(MrRat.prefix.length());

        boolean commandFound = false;

        if(message.split("\\s+")[0].length() > 0) {
            String commandName = message.split("\\s+")[0];

            for(Command c : commands) {
                if(c.name.equalsIgnoreCase(commandName)) {
                    c.onCommand(Arrays.copyOfRange(message.split("\\s+"), 1, message.split("\\s+").length), message);
                    commandFound = true;
                    break;
                }
            }

        }
        if(!commandFound) {
            e.getChannel().sendMessage("Command not found; `~help` for help").queue();
        }
    }
}
