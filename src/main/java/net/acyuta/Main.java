package net.acyuta;

import net.acyuta.utils.C;
import net.acyuta.vk.Vk;
import net.acyuta.vk.VkLogic;
import org.apache.commons.cli.*;

/**
 * Created by acyuta on 05.12.14.
 */
public class Main {

    private static Options options = new Options();
    private static Vk vk = null;
    private static VkLogic logic = null;

    public static void main(String[] args) throws ParseException {
        init();

        CommandLineParser parser = new GnuParser();
        CommandLine line = parser.parse(options, args);

        if (!vk.checkAccess() || args.length == 0)
            usage();

        if (line.hasOption("s"))
            logic.dailyInfo();
    }

    private static void init() {
        try {
            vk = Vk.init();
            logic = new VkLogic(vk);
        } catch (Exception e) {
            C.die(e.getMessage());
        }

        OptionGroup messages = new OptionGroup();
        messages.addOption(OptionBuilder
                        .withLongOpt("read-all")
                        .withDescription("Прочитать все сообщения")
                        .create()
        );

        options.addOption(OptionBuilder
                        .withLongOpt("messages")
                        .hasOptionalArgs(1)
                        .create('m')
        );
        options.addOptionGroup(messages);
        options.addOption("u", "usage", false, "Как использовать");
        options.addOption("s", "usage", false, "Статистика");
    }

    private static void usage() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("cvk.sh", options, false);
        C.die();
    }
}
