#language: ru

  Функционал: Клик на плитку курса
    Структура сценария: Клик по плитке раннегопозднего курса на главной странице
      Пусть Открыть браузер "chrome"
      Если Открыть главную страницу
      Если Кликнуть на <start_date> плитку курса
      Тогда откроется страница курса

      Примеры:
      |start_date|
      |раннюю|
      |позднюю|

    Структура сценария: Клик по плитке курса на главной странице по названию
      Пусть Открыть браузер "chrome"
      Если Открыть главную страницу
      Если Кликнуть на плитку курса с названием <course_name>
      Тогда откроется страница курса с названием <header>

      Примеры:
      |course_name|header|
      |Бизнес-анализ и системный|Бизнес-анализ и системный|
      |Специализация С#|C# Developer|
      |Специализация сетевой инженер|Network Engineer|


    Структура сценария: Клик на плитку дорого\дешевого курса
      Пусть Открыть браузер "chrome"
      Если Открыть главную страницу
      Если Кликнуть по курсу с самой <course_cost> ценой
      Тогда откроется страница курса

      Примеры:
      |course_cost|
      |дорогой|
      |дешевой|