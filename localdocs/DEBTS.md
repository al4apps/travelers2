# Technical Debts

## 1. Подобрать финальные иконки для опций поездки
- Что улучшить: заменить временные иконки `ic_sdcard` (багаж) и `ic_folder` (посылки) на продуктовые иконки из финального набора.
- Где в коде: `composeApp/src/commonMain/kotlin/com/al4apps/travelers2/features/trip_details/map/TripDetailsUiAssembler.kt`

## 2. Доработать визуальный стиль карточки мест
- Что улучшить: добавить настоящую пунктирную обводку для свободных мест (сейчас используется приближенный стиль сплошной тонкой обводкой).
- Где в коде: `composeApp/src/commonMain/kotlin/com/al4apps/travelers2/design_system/trip_details/TripDetailsSeatsCard.kt`

## 3. Подключить реальные данные поездки
- Что улучшить: заменить sample state в `DefaultTripDetailsComponent` на данные из domain/data слоя после появления API/репозитория поездок.
- Где в коде: `composeApp/src/commonMain/kotlin/com/al4apps/travelers2/features/trip_details/DefaultTripDetailsComponent.kt`

## 4. Локализация и plural-формы для "мест"
- Что улучшить: вынести plural-логику для остатка мест в корректные локализованные plural-ресурсы.
- Где в коде: 
  - `composeApp/src/commonMain/composeResources/values/strings.xml`
  - `composeApp/src/commonMain/kotlin/com/al4apps/travelers2/features/trip_details/map/TripDetailsUiAssembler.kt`
