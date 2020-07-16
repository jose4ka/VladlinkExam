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

После того как мы прошли процесс авторизации, мы можем выбрать интересующий нас абонентский счёт
![Экран приложения №3, ActivitySession, FragmentAccountsList](https://github.com/loakdv/VladlinkExam/blob/master/readmeData/appScreens/photo_2020-07-16_10-36-13.jpg?raw=true)

На данном экране отображается вся полученная информация о выбранном счёте
![Экран приложения №4, ActivitySession, FragmentAccountInfo](https://github.com/loakdv/VladlinkExam/blob/master/readmeData/appScreens/photo_2020-07-16_10-36-16.jpg?raw=true)

