class History {
  private var entries: List[String] = List()

  def addEntry(entry: String): Unit = {
    entries = entry :: entries
  }

  def getEntries: List[String] = entries

  def clear(): Unit = {
    entries = List()
  }
}
