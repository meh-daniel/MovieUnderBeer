# MovieUnderBeer

<img src="https://github.com/meh-daniel/MovieUnderBeerLite/blob/dev/preview.jpg" width="1200" height="600">

____

## three weeks later...


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
+ coordinatorLayout
+ recyclerView
+ xml

### Архитектура:
+ app
  + App
  + Constants
+ di
  + modules
  + AppComponent
+ domain
  + entities - бизнес сущности и вспомогательные сущности
  + model - api retrofit, обработка ответа retrofit и repositories
+ presentation
  + adapter - реализованный listAdapter общий для все
  + mvp - moxy
  + navigation - cicerone
  + ui - fragments
  + single activity

____

## Анализ выполненной работы


### не реализованно:
+ снятие с выбранного жанра и сортировка фильмов 
+ нет тёмной темы


### Иная реализация:
+ single select нажатие по жанров 
    + при повороте экрана выделение с выбранного жанра пропадает (но выбранные фильмы в отсортированном списке по этому жанру остаются)

### Реализация остального функционала:
+ выполненно
____

## movie list screen
| | | |
|:-------------------------:|:-------------------------:|:-------------------------:|
|<img width="1604"  src="https://github.com/meh-daniel/MovieUnderBeerLite/blob/dev/movielist_1.jpg"> |  <img width="1604" src="https://github.com/meh-daniel/MovieUnderBeerLite/blob/dev/movielist_2.jpg">|<img width="1604" src="https://github.com/meh-daniel/MovieUnderBeerLite/blob/dev/movielist_3.jpg">|

### Use case: 
+ Просмотр вертикального/горизонтального ячеек списка
+ Сортировка ячеек фильмов по жанру
+ Переход с ячейки фильмов на экран с деталями о фильме
____

## movie details screen

| | | |
|:-------------------------:|:-------------------------:|:-------------------------:|
|<img width="1604"  src="https://github.com/meh-daniel/MovieUnderBeerLite/blob/dev/filmdetails_1.jpg"> |  <img width="1604" src="https://github.com/meh-daniel/MovieUnderBeerLite/blob/dev/filmdetails_2.jpg">|

### Use case: 
+ Просмотр вертикального/горизонтального деталей о фильме
+ Кнопка перехода назад
____

## error alert dialog screen
<img src="https://github.com/meh-daniel/MovieUnderBeerLite/blob/dev/error_alert_dialog.jpg" width="450" height="900">

### Use case: 
+ Просмотр вертикального/горизонтального информации о ошибке
+ Кнопка закрывающая окно


