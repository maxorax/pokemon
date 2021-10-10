### Шаблон для практической работы по теме HTTP

Вы пишете приложение, которое симулирует бойцовский клуб покемонов. Его функционал:
1. Вычитывать показатели покемонов из внешнего REST-сервиса
1. Инициировать бой
1. Объявлять победителя

> *Это важно*. Убедитесь, что вы знаете что такое REST-сервис, JSON, как происходит маппинг из JSON => Java object.
> 

В этом задании мы будем использовать публичный [PokeAPI](https://pokeapi.co/). Это обыкновенный REST-сервис, который хранит
в себе покемонов и их различные атрибуты. Его мы будем использовать в качестве источника наших данных. 

Приведем пример алгоритма боя. У каждого покемона есть такие поля, как `id`, `hp`, `attack` и `defense`.
* Сначала по имени покемона мы формируем запрос к PokeApi и создаём объект `Pokemon`
* Бой начинает покемон с наибольшим `id`. Он атакует своего противника на величину поля `attack` и уменьшает число `hp`. 
При уменьшении `hp`, это число уменьшается в зависимости от параметра `defense` у противника по формуле:
`(входящий урон = attack * defense/100)`. Например: если `pikachu` принял удар силой 100, а у него значение `defense` равное 40,
то он получит лишь 60 урона.
* Покемон, число `hp` которого стало равным или меньшим 0, проигрывает
* При победе бойцовский клуб оглашает победителя, скачивая его изображение в папку проекта (Картинка покемона берется 
из объекта sprites с ключом `front_default`)

> *Это важно*. PokeApi требует, чтобы при любых запросах к его апи присутствовал заголовок `User-Agent`, в противном случае,
будет возвращён код ошибки `403` https://github.com/PokeAPI/pokeapi/issues/135
>

1. Создайте ветку feature и продолжите разработку в ней.
1. Добавьте зависимость `com.fasterxml.jackson.core.jackson-databind` - библиотека для маппинга JSON-файлов на объекты
1. Доработайте сущность `Pokemon`, для возможности маппинга JSON-документа. Если это необходимо, добавьте аннотации, конструктор
или дополнительные методы. Названия полей изменять **нельзя**
1. Имплементируйте класс `ObjectMapperFactory` - он обязан возвращать объект, с помощью которого мы будем совершать
маппинг
1. Имплементируйте `PokemonFethingService` и `PokemonFightingClubService`.
1. В методе main напишите демо, которое демонстрирует простой бой между покемонами `Pikachu` и `Slowpoke`.
1. *Имплементируйте юнит-тесты `PokemonFethingService` и `PokemonFightingClubService`. Для заглушки сервиса Poke-api
используйте [Wiremock](http://wiremock.org/docs/stubbing/). Постарайтесь добиться максимального коверейджа
1. Запушьте все изменения на гитхаб и создайте pull-request в ветку master.
