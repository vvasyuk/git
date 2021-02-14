package general.abasics

object test {
  private List<String> plan = new ArrayList<>();
  public void replan(String newCity, String beforeCity) {
    int newCityIndex = plan.indexOf(beforeCity);
    plan.add(newCityIndex, newCity);
  }
  public void add(String city) {
    plan.add(city);
  }
  public List<String> getPlan() {
    return Collections.unmodifiableList(plan);
  }
}

