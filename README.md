# BookSearch

-I've developed this app using MVVM architecture and built the screens in Jetpack Compose. I've used a Nav graph to define how screens are supposed to transition.
- Performed Dependency Injection using Hilt+Dagger. The network module, repository module and resource module are all injected.
- The result screen is displaying the Book title and author in a lazy column.
- The business logic is being handled in the viewModel.
- DocsList is a local data class I use to identify what to return when a network operation happens.
- Added framework for unit tests too.
- All 3rd party libraries used are listed in the build.gradle file for the app.
