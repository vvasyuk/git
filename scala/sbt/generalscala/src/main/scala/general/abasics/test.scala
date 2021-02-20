package general.abasics

object test {
  static List<String> recommendationFeed(List<Book> books) {
    List<String> result = new ArrayList<>();
    for (Book book : books)
      for (String author : book.authors)
        for (Movie movie : bookAdaptations(author)) {
          result.add(String.format("You may like %s, " +
            "because you liked %s’s %s", movie.title, author, book.title));
    }
    return result;
  }
}

