package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    POWER_STAR, // power star status
    SUPER_MUSHROOM, // super mushroom status
    REMOVE, // Remove status
    IS_RESETTABLE, // is resettable status
    ENEMY,
    TELEPORT,
    FIRE_ATTACK,
    TELEPORT_TO_LAVAZONE,
    TELEPORT_TO_MAINMAP,
    WRENCH,
    PIRANHA_PLANT,
    WALL, SPROUT, SAPLING, MATURE, FIRE, LAVA, //use this status to locate lava ground
    BOTTLE, WATERFOUNTAIN, HEALTHFOUNTAIN, POWERFOUNTAIN
}
