package gg.steve.mc.saphirus.sf.framework.cmd;

import gg.steve.mc.saphirus.sf.framework.cmd.misc.HelpSubCmd;
import gg.steve.mc.saphirus.sf.framework.cmd.misc.ReloadSubCmd;

public enum SubCommandType {
    HELP_CMD(new HelpSubCmd()),
    RELOAD_CMD(new ReloadSubCmd());

    private SubCommand sub;

    SubCommandType(SubCommand sub) {
        this.sub = sub;
    }

    public SubCommand getSub() {
        return sub;
    }
}
