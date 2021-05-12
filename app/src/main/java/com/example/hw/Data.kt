package com.example.hw

import com.example.hw.movie.MovieItem

object Data {
    var favoriteMovies : MutableList<MovieItem> = mutableListOf()
    var movies : MutableList<MovieItem> = mutableListOf(
            MovieItem(
                    "Суд на Чикагской семеркой",
                    "Чикаго, 1968 год. Демонстрация против войны во Вьетнаме вылилась в стычки с полицией. Семь участников беспорядков предстают пред судом по обвинениям в заговоре против американского правительства.",
                    R.drawable.trial,false, false, R.drawable.ic_baseline_favorite_border_24),
            MovieItem(
                    "Довод",
                    "Протагонист пытается обезвредить террориста с помощью уникальной технологии. Блокбастер-пазл Кристофера Нолана.",
                    R.drawable.tenet, false, false, R.drawable.ic_baseline_favorite_border_24),
            MovieItem(
                    "Еще по одной",
                    "Друзья решают выпивать каждый день, чтобы стать счастливее. Философская алкодрама с Мадсом Миккельсеном.",
                    R.drawable.drunk, false, false,R.drawable.ic_baseline_favorite_border_24),
            MovieItem(
                    "1917",
                    "Военная драма Сэма Мендеса, снятая почти без монтажа. Три «Оскара», в том числе за работу оператора.",
                    R.drawable.war, false, false,R.drawable.ic_baseline_favorite_border_24),
            MovieItem(
                    "Неограненные алмазы",
                    "Криминальная драма с Адамом Сэндлером",
                    R.drawable.gems, false, false,R.drawable.ic_baseline_favorite_border_24),
            MovieItem(
                    "Маленькие женщины",
                    "История взросления четырех сестер с мощным кастом и эффектными костюмами. От автора «Леди Бёрд» Греты Гервиг",
                    R.drawable.littlewoman, false, false,R.drawable.ic_baseline_favorite_border_24),
            MovieItem(
                    "Маяк",
                    "Роберт Паттинсон и Уиллем Дефо самоизолировались на острове, пытаясь сохранить жизнь и здравый рассудок",
                    R.drawable.lighthouse, false, false,R.drawable.ic_baseline_favorite_border_24),
            MovieItem(
                    "Серебрянные коньки",
                    "История любви в сеттинге зимнего Санкт-Петербурга в 1899",
                    R.drawable.silver, false, false,R.drawable.ic_baseline_favorite_border_24),
            MovieItem(
                    "Человек-невидимка",
                    "Звезда «Рассказа служанки» Элизабет Мосс в новом остросоциальном хорроре автора «Апгрейда» Ли Уоннелла",
                    R.drawable.invisibleman, false, false,R.drawable.ic_baseline_favorite_border_24),
            MovieItem(
                    "Дело Ричарда Джуэлла",
                    "Вчера — герой, сегодня — террорист. Драма Клинта Иствуда о человеке против системы по мотивам реальных событий",
                    R.drawable.richard, false, false,R.drawable.ic_baseline_favorite_border_24),
            MovieItem(
                    "Джентельмены",
                    "Успешное возвращение Гая Ричи к корням — острая и живая криминальная комедия с блестящим актерским составом.",
                    R.drawable.gentelmen,false, false, R.drawable.ic_baseline_favorite_border_24),
            MovieItem(
                    "Манк",
                    "Нью-йоркский острослов Герман Манкевич вспоминает последние годы, проведённые в Голливуде, и переносит опыт личного общения с воротилами бизнеса в сценарий.",
                    R.drawable.mank, false, false, R.drawable.ic_baseline_favorite_border_24),
    )
}