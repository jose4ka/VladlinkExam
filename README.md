# VladlinkExam
Данное приложение является выполненным ТЗ от компании Владлинк.
Ниже будут представлены скриншоты приложения, а так-же будет рассмотренно внутреннее строение приложения.

Скриншоты приложения
=
На данном экране мы вводим заранее известный номер телефона "+79444444444"(Исходя из ТЗ), на который должно прийти СМС с кодом.

Если ввести какой-то другой номер, запрос не пройдёт, и не откроется следующее окно.
![Экран приложения №1, ActivityLogin, FragmentPhoneEnter](https://github.com/loakdv/VladlinkExam/blob/master/readmeData/appScreens/photo_2020-07-16_10-36-08.jpg?raw=true)

Тут происходит иммитация того что нам пришёл СМС код, и мы его вводим.
Мы знаем что заведомо исправный код "1234" (Исходя из ТЗ).
Если ввести другой код, то запрос не пройдёт.
![Экран приложения №2, ActivityLogin, FragmentSMSCheck](https://github.com/loakdv/VladlinkExam/blob/master/readmeData/appScreens/photo_2020-07-16_10-36-11.jpg?raw=true)

После того как мы прошли процесс авторизации, мы можем выбрать интересующий нас абонентский счёт.
![Экран приложения №3, ActivitySession, FragmentAccountsList](https://github.com/loakdv/VladlinkExam/blob/master/readmeData/appScreens/photo_2020-07-16_10-36-13.jpg?raw=true)

На данном экране отображается вся полученная информация о выбранном счёте.
![Экран приложения №4, ActivitySession, FragmentAccountInfo](https://github.com/loakdv/VladlinkExam/blob/master/readmeData/appScreens/photo_2020-07-16_10-36-16.jpg?raw=true)

Разбор внутреннего строения проекта
=
Тут показана часть структуры проекта.
Активности, фрагменты, адаптеры и т.д. раскиданы по своим пакетам, что очень хорошо сказывается на удобстве просмотра проекта.
![Скриншот структуры №1](https://github.com/loakdv/VladlinkExam/blob/master/readmeData/sourceScreens/%D0%A1%D0%BA%D1%80%D0%B8%D0%BD%D1%88%D0%BE%D1%82%20%D1%81%D1%82%D1%80%D1%83%D0%BA%D1%82%D1%83%D1%80%D1%8B%201.png?raw=true)

Здесь есть замечание.
При получении ответа с сервера в виде "тела", то это "тело" обёрнуто в "data", поэтому на каждый класс запроса или ответа с сервера есть своя обертка "data".
Так-же, для удобства использования этих моделей в коде, в имени каждого класса есть приставка "M", что означает "Model"
![Скриншот структуры №2](https://github.com/loakdv/VladlinkExam/blob/master/readmeData/sourceScreens/%D0%A1%D0%BA%D1%80%D0%B8%D0%BD%D1%88%D0%BE%D1%82%20%D0%A1%D1%82%D1%80%D1%83%D0%BA%D1%82%D1%83%D1%80%D1%8B%202.png?raw=true)

Хочется отметить один немаловажный момент, относительно классов MAccount, MSingleAccount.
Основное отличие их в том, что поле "isSMS" имеет разный тип данных, при получении ответов с сервера.
В MAccount это int[], в MSingleAccount - String.
Поэтому, класс MAccount используется для получения СПИСКА счетов, а MSingleAccount используется для получения подробной информации о счёте.
![Замечание](https://github.com/loakdv/VladlinkExam/blob/master/readmeData/sourceScreens/%D0%97%D0%B0%D0%BC%D0%B5%D1%87%D0%B0%D0%BD%D0%B8%D0%B5.png)
