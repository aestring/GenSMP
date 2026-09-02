package com.gensmp.util;

/**
 * Central message constants
 */
public class Messages {
    public static final String PREFIX = "§6[GenSMP] §r";

    // Gen messages
    public static final String GEN_OBTAINED = PREFIX + "§aYou obtained the §6%s Gen§a!";
    public static final String GEN_UNLOCKED = PREFIX + "§aYour §6%s Gen§a has unlocked!";
    public static final String GEN_UNLOCKING = PREFIX + "§eYour §6%s Gen§e will unlock in §610 minutes§e.";
    public static final String GEN_ALREADY_HAVE_TWO = PREFIX + "§cYou already have 2 active Gens. Withdraw one first!";
    public static final String GEN_WITHDRAWN = PREFIX + "§aYou withdrew your §6%s Gen§a!";
    public static final String GEN_WITHDRAWN_EMPTY = PREFIX + "§cYou don't have any Gens to withdraw!";
    public static final String GEN_FIRST_TIME = PREFIX + "§aWelcome! You received the §6%s Gen§a as your starter Gen!";

    // Ability messages
    public static final String ABILITY_COOLDOWN = PREFIX + "§cThis ability is on cooldown for §e%d seconds§c.";
    public static final String ABILITY_LOCKED = PREFIX + "§cYour Gen is still unlocking. Please wait.";
    public static final String ABILITY_USED = PREFIX + "§aAbility used!";
    public static final String DNA_ABILITY_USED = PREFIX + "§aYour DNA ability has been activated!";
    public static final String DNA_COOLDOWN = PREFIX + "§cYour DNA ability is on cooldown for §e%d seconds§c.";
    public static final String DNA_NEED_TWO_GENS = PREFIX + "§cYou need exactly 2 active Gens to use your DNA ability.";

    // Admin messages
    public static final String REROLL_SUCCESS = PREFIX + "§aRerolled %s's starter Gen to §6%s§a.";
    public static final String GIVE_SUCCESS = PREFIX + "§aGave §6%s Gen§a to %s.";
    public static final String GIVE_INVALID_GEN = PREFIX + "§cInvalid Gen type: %s";

    // Error messages
    public static final String INVALID_COMMAND = PREFIX + "§cInvalid command. Use /gensmp help for help.";
    public static final String NO_PERMISSION = PREFIX + "§cYou don't have permission to use this command.";
    public static final String PLAYER_NOT_FOUND = PREFIX + "§cPlayer not found: %s";
}
