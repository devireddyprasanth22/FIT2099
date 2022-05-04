package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    //For Super Mushroom
    INCREASE_MAX_HP, // increase hp by 50
    EVOLVE_CHAR, // Character changes from m to M
    FREE_JUMP, // 100% success rate for jump
    //For Power Star
    INVINCIBLE, // Take 0 damage as all enemy attacks are useless
    PASS_HIGH_GROUND,// Player does not have to jump to higher level ground, high ground is converted to dirt from ground if actor is on it
    CONVERT_COINS, // For every destroyed ground, drop $5
    INSTANT_KILL, // A successful attack will instantly kill enemies

    IS_JUMPABLE,
    POWER_STAR,
    SUPER_MUSHROOM,
    CAN_JUMP,
    REMOVE, // Resettable status
    IS_RESETTABLE
}
