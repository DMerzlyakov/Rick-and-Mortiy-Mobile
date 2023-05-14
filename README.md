# Rick And Morty - Android

Мобильное приложение под Android. Привязанно к api: https://rickandmortyapi.com/.

Основным языком выступает Kotlin, часть функционала реализована на Java. Приложение реализовано по принципу Single Activity.


|Архитектурные подходы      |Асинхронное выполнение операций|   UI часть      |Работа с локальными и удаленными данными|
|----------------			|-------------------------------|-----------------|-------------------------------------------|
|MVVM  						|Coroutines                		|RecyclerView  	  |			Paging 3 		                |
|Single Activity			|RxJava 2       				|Glide            |			Retrofit 2, Gson		        |
|Clean Architecture 		|								| 				  |  Flow / LiveData                        |
