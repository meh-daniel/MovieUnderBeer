# MovieUnderBeer

<img src="https://github.com/meh-daniel/MovieUnderBeerLite/blob/dev/preview.jpg" width="1200" height="600">

____

## three weeks later...

____

## movie list screen
| | | |
|:-------------------------:|:-------------------------:|:-------------------------:|
|<img width="1604"  src="https://github.com/meh-daniel/MovieUnderBeerLite/blob/dev/photoGitHubReadme/movielist_1.jpg"> |  <img width="1604" src="https://github.com/meh-daniel/MovieUnderBeerLite/blob/dev/photoGitHubReadme/movielist_2.jpg">|<img width="1604" src="https://github.com/meh-daniel/MovieUnderBeerLite/blob/dev/photoGitHubReadme/movielist_3.jpg">|

### Use case: 
+ Просмотр вертикального/горизонтального ячеек списка
+ Сортировка ячеек фильмов по жанру
+ Переход с ячейки фильмов на экран с деталями о фильме
____

## movie details screen

| | | |
|:-------------------------:|:-------------------------:|:-------------------------:|
|<img width="1604"  src="https://github.com/meh-daniel/MovieUnderBeerLite/blob/dev/filmdetails_1.jpg"> |  <img width="1604" src="https://github.com/meh-daniel/MovieUnderBeerLite/blob/dev/photoGitHubReadme/filmdetails_2.jpg">|

### Use case: 
+ Просмотр вертикального/горизонтального деталей о фильме
+ Кнопка перехода назад
____

## error alert dialog screen
<img src="https://github.com/meh-daniel/MovieUnderBeerLite/blob/dev/photoGitHubReadme/error_alert_dialog.jpg" width="450" height="900">

### Use case: 
+ Просмотр вертикального/горизонтального информации об ошибке
+ Кнопка закрывающая окно

____


### Использованные технологии: 
+ kotlin
+ moxy
+ cicerone
+ dagger2
+ retrofit
+ croutines
+ gson

### Дизайн сделан на основе: 
+ material design
+ xml

### Архитектура:
+ app
  + App
  + Constants - содержащий api url и base url images 
+ di
  + modules
  + AppComponent
+ domain
  + entities - бизнес сущности и вспомогательные сущности для listAdapter
  + model - api retrofit, кастомный CallAdapterFactory и repositories
+ presentation
  + adapter - реализованный listAdapter общий для все
  + mvp - реализованный через moxy
  + navigation - навигация реализованная через cicerone
  + ui - fragments и составляющие адаптеры к фрагментам
  + single activity

____

## Анализ выполненной работы

### ошибки по ui:
+  долго открывается экран с детальной информации о фильме.


### ошибки по коду:
+   Класс MovieDetailsFragment метод loadFilm, класс GenreGroupViewHolder метод onBind методы можно разбить еще на несколько методов - повысит читаемость кода.
+   Ручное обращение к методам модуля вручную, не использовал компоненты для этого
+   В пресентере лучше заводить общий scope на время жизни Presenter, чтобы при уничтожении Presenter'а отменять все запущенные операции.
+   В пресенторах использовал строки которые нужно вынести в строковые ресурсы


### не реализованно:
+ снятие с выбранного жанра для возвращения к сортировках по всем фильмам
+ тёмная тема
+ анимация перехода на новый экран
+ возможность прогрузить данные в случае изначального включения приложения без интернета 
+ toolbar по material design


### Иная реализация:
+ single select нажатие по жанрам
    + при повороте экрана пропадает выделение с выбранного перед этим жанра(но выбранные фильмы в отсортированном списке по этому жанру остаются)


### Категория- не баг, а фича:
+ доступно открытие нескольких подряд окон с фильмами которые пользователь подряд все просматривает


### Реализация заданого по заданию функционала:
+ выполненно

### Реализация дополнительного функционала:
+ приложение обрабатывает отсутствие интернета
+ есть обработка api ответа и сообщения в случае получения неправильного ответа с сервера


