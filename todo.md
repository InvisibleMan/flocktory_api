## Разработать веб-сервис поиска по заданным ключевым словам через внешний веб-сервис.

### Задача

Необходимо разработать законченное веб-приложение, реализующее следующую функцию:

- Обслуживать HTTP запросы по URL "/search". В параметрах запроса передается параметр "query", содержащий ключевое слово для поиска. Параметров может быть несколько, в этом случае мы работаем с несколькими ключевыми словами. Пример "http://localhost:8080/search?query=lis&query=clojure". 
- Сервис должен обраться к API поиска Bing (https://www.bing.com/search?q=scala&format=rss&count=10) по HTTP, получить ответ через RSS. В случае, если ключевых слов переданно больше одного, запросы должны выполняться параллельно (по одному HTTP запросу на ключевое слово). Должно быть ограничение на максимальное количество одновременных HTTP-соединений, это значение нельзя превышать. Если ключевых слов больше, нужно организовать очередь обработки так, чтобы более указанного количество соединений не открывалось. По каждому слову ищем только первые 10 записей.
- Из каждого результата извлекаем основную ссылку (поле link). Из ссылки берем hostname, из которого берется только домен второго уровня (т.е. из ссылки http://lenta.ru/photo/2012/11/28/lis/ оставляем только "lenta.ru").

- В результате работы веб-сервиса должна быть возвращена статистика по доменам второго уровня - сколько раз в сумме использовался домен по всем ключевым словам. В случае, если по разным ключевым словам было найдены полностью идентичные ссылки, хост должен учитываться только один раз.
- Результат должен быть предствлен в формате JSON. Выдача ответа с человеко-читаемым форматированием (pretty print) будет рассматриваться как плюс. 


Пример ответа:
```json
{ 
  "lenta.ru" : 5, 
  "livejournal.com" : 10, 
  "vk.com" : 20 
}
```

### Использование сторонних компонентов

 * Можно использовать любые opensource компоненты, доступные на maven central или в репозитарии clojars. 
 * Можно использовать любое средство разработки веб-приложений.
 * Приветствуются короткие решения, использующие сторонние компоненты.

### Ожидаемый результат

Необходимо представить исходные тексты приложения. Приложение:

- Должно компилироваться и работать.
- Должно собираться однострочной командой при помощи maven, lein или другого средства сборки которое легко развернуть. 
- Должно запускаться одной командой. Например, в виде runnable jar, допускается запуск через "lein run" или аналогичное средство.
