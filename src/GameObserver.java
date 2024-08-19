public interface GameObserver {
    void onCardPlayed(Player player, Card card);
    void onPlayerTurn(Player player, Card topCard);
    void onGameOver(Player winner);
    void onCardDrawn(Player player);
}
