Task Instructions
1️ Create a ViewModel
•    Implement MainViewModel that provides a StateFlow<List>, where each Quote contains: 
•    data class Quote(val content: String, val author: String)
•    Fetch 7 random quotes from the Quotable API:
        API Endpoint: https://api.quotable.io/quotes?limit=7
•    Store the list in StateFlow and update it when fetchQuotes() is called.
 
2️ Implement Dependency Injection with Hilt
•    Set up Hilt for Dependency Injection.
•    Create a QuoteRepository class that: 
•    Fetches data from the API using Retrofit.
•    Exposes a function to return quotes as a Flow.
•    Inject QuoteRepository into MainViewModel.
 
3️ Display Data in Jetpack Compose UI
•    Modify MainActivity to observe the StateFlow from MainViewModel.
•    Display the list of 7 quotes in a LazyColumn (RecyclerView alternative in Compose).
•    Each list item should contain: 
•    Quote text (big font).
•    Author name (smaller font, below the quote).
•    Loading State: Show a progress indicator while fetching quotes.
 
4️ Add Refresh Button
•    Include a button to fetch new quotes when clicked.
 
Bonus (Optional Enhancements)
🚀 Error Handling: Handle API failures gracefully (show error message).
🚀 Unit Tests: Add test cases for QuoteRepository and MainViewModel.
