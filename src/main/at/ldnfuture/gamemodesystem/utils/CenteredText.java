package at.ldnfuture.gamemodesystem.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 03.08.2021
 */
public class CenteredText {
    public CenteredText() {
    }

    public static void sendCenteredMessage(Player player, String message) {
        if (message == null || message.equals("")) {
            player.sendMessage("");
        }

        message = ChatColor.translateAlternateColorCodes('&', message);
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;
        char[] var5 = message.toCharArray();
        int toCompensate = var5.length;

        int spaceLength;
        for(spaceLength = 0; spaceLength < toCompensate; ++spaceLength) {
            char c = var5[spaceLength];
            if (c == 167) {
                previousCode = true;
            } else if (previousCode) {
                previousCode = false;
                if (c != 'l' && c != 'L') {
                    isBold = false;
                } else {
                    isBold = true;
                }
            } else {
                DefaultFont dFI = DefaultFont.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                ++messagePxSize;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        toCompensate = 154 - halvedMessageSize;
        spaceLength = DefaultFont.SPACE.getLength() + 1;
        int compensated = 0;

        StringBuilder sb;
        for(sb = new StringBuilder(); compensated < toCompensate; compensated += spaceLength) {
            sb.append(" ");
        }

        player.sendMessage(sb.toString() + message);
    }
}