Task Instructions

1️ Create a ViewModel

        •    Implement MainViewModel that provides a StateFlow<List>, where each Character contains: 

        •    data class Character(val id: Int, val name: String, val status: String, val species: String, val image: String)

        •    Fetch 10 random characters from the Rick and Morty API:
                API Endpoint: https://rickandmortyapi.com/api/character
        
        •    Store the list in StateFlow and update it when fetchCharacters() is called.

 
2️ Implement Dependency Injection with Hilt

        •    Set up Hilt for Dependency Injection.

        •    Create a CharacterRepository class that: 

        •    Fetches data from the API using Retrofit.

        •    Exposes a function to return characters as a Flow.

        •    Inject CharacterRepository into MainViewModel.

 
3️ Display Data in Jetpack Compose UI

        •    Modify MainActivity to observe the StateFlow from MainViewModel.

        •    Display the list of characters in a LazyColumn (RecyclerView alternative in Compose).

        •    Each list item should contain: 

        •    Character image (use Coil library for image loading)

        •    Character name (big font)

        •    Character status and species (smaller font, below the name)

        •    Loading State: Show a progress indicator while fetching characters.

 
4️ Add Refresh Button

        •    Include a button to fetch new characters when clicked.

 
Bonus (Optional Enhancements)

        🚀 Error Handling: Handle API failures gracefully (show error message).

        🚀 Character Details: Add a detail screen that shows when a character is clicked.

        🚀 Unit Tests: Add test cases for CharacterRepository and MainViewModel.

