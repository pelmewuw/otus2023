package data;

public enum MenuItemData {
  CHAT_ITEM("Chat"),
  EXERCISE_ITEM("Exercise"),
  GRAMMAR_ITEM("Grammar"),
  STATS_ITEM("Stats");

  private String name;

  MenuItemData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
