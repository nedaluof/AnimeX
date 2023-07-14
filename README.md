## 󠀢AnimeX
AnimeX ⛩️🌸 Simple app that demonstrates various Android development best practices
  


## ⛩️ Tech stack

- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous tasks.
- Jetpack - [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
  - Lifecycle: Observe Android lifecycles and handle UI states based on the lifecycle changes.
  - ViewModel: Manages UI-related data.
  - DataBinding: support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - Room: used to construct reliable caching for offline support.
  - [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) helps you load and display pages of data from a larger dataset from local storage or over the network.
  - [Hilt](https://dagger.dev/hilt/): for DI (dependency injection) .  
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): REST APIs.
- [Moshi](https://github.com/square/moshi/): A modern JSON library for Kotlin and Java.
- [Coil](https://github.com/coil-kt/coil): Loading images from network.
- [Timber](https://github.com/JakeWharton/timber): A logger with a small API footprint.
- [Material Components for Android](https://github.com/material-components/material-components-android).
- Architecture
  - MVVM Clean Architecture (View - ViewModel - Model) - Domain layer - Data layer with Repository Pattern
<p align="center">
    <img src="https://github.com/nedaluof/Quotes/blob/master/screen_shots/mad_arch_overview.png?raw=true" width="350">
</p>




### License

```
Copyright 2023 Nedal Hasan ABDALLAH (NedaluOf)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an 
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
either express or implied. See the License for the specific 
language governing permissions and limitations under the License.

```
