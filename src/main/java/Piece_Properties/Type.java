package main.java.Piece_Properties;

public enum Type {
    GENERAL, //  Move and capture one point orthogonally and may not leave the palace.
    ADVISOR, // Move and capture one point diagonally and may not leave the palace.
    ELEPHANT, // Move and capture exactly two points diagonally and can not jump over intervening pieces
    HORSE, // Moves and captures one point orthogonally and then one point diagonally away from its former position and can be blocked
    CHARIOT, //Move and captures any distance orthogonally
    CANNON, // Move like chariots, any distance orthogonally without jumping, but can only capture by jumping a single piece of either colour along the path of attack
    SOLDIER, // Move and capture by advancing one point. Once they have crossed the river, they may also move and capture one point horizontally
    UNLOCKED // Soldier after they crossed river
}
