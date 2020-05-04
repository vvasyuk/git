guy_preferences = {
    'andrew': ['caroline', 'abigail', 'betty'],
    'bill': ['caroline', 'betty', 'abigail'],
    'chester': ['betty', 'caroline', 'abigail'],
}

gal_preferences = {
    'abigail': ['andrew', 'bill', 'chester'],
    'betty': ['bill', 'andrew', 'chester'],
    'caroline': ['bill', 'chester', 'andrew']
}
print(guy_preferences)
print(gal_preferences)
guy_preferences = {guy: list(reversed(pref)) for guy, pref in guy_preferences.items()}

for gal, pref in gal_preferences.items():
    gal_preferences.update({gal: {guy: i for i, guy in enumerate(pref)}})

print(guy_preferences)
print(gal_preferences)